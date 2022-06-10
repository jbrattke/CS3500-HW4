package controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import model.ImageCache;
import model.ImageCacheModel;
import model.ImageModel;
import model.ImageModelRGB;
import model.filters.BrightnessFilter;
import model.filters.HorizontalFlipFilter;
import model.filters.IntensityFilter;
import model.filters.LumaFilter;
import model.filters.RGBFilter;
import model.filters.ValueFilter;
import model.filters.VerticalFlipFilter;
import view.ImageView;
import view.ImageViewImpl;
import view.MockView;

import static model.ImageUtil.comparePPM;
import static model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the controller implementation, uses a corrupted appendable and mock views and models
 * as well.
 */
public class TestControllerImpl {

  // tests error if the appendable is invalid.
  @Test(expected = IllegalStateException.class)
  public void testBadAppendable() {
    Appendable log = new CorruptAppendable();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoInput() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();
  }

  // tests quiting as first action.
  @Test
  public void testQuitRightAway() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    assertEquals("Welcome to the image program!\nEnter 'help' for a list of commands.\n",
            log.toString());
  }

  @Test
  public void loadAndSavePPMTest() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "save test.ppm capybara\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    assertTrue(comparePPM("res/capybara.ppm", "test.ppm"));

    //deleting test files
    File file = new File("test.ppm");
    file.delete();
  }

  @Test
  public void testGreyscaleFiltersPPM() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    String[] filenames = {"red.ppm", "luma.ppm"};
    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "red-component capybara red\n"
            + "luma-component capybara luma\n"
            + "save red.ppm red\n"
            + "save luma.ppm luma\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    //comparing to the expected files
    for (int i = 0; i < filenames.length; i++) {
      assertTrue(comparePPM("res/" + filenames[i], filenames[i]));

      //deleting test files
      File file = new File(filenames[i]);
      file.delete();
    }
  }

  @Test
  public void testBrightenFiltersPPM() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "brighten 50 capybara bright\n"
            + "save bright.ppm bright\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    assertTrue(comparePPM("res/bright.ppm", "bright.ppm"));
    File file = new File("bright.ppm");
    file.delete();
  }

  @Test
  public void testFlipFiltersPPM() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "horizontal-flip capybara horizontal\n"
            + "save horizontal.ppm horizontal\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    assertTrue(comparePPM("res/horizontal.ppm", "horizontal.ppm"));
    File file = new File("horizontal.ppm");
    file.delete();
  }

  @Test
  public void testRGBGreyscaleFiltersThroughCache() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "red-component capybara red\n"
            + "green-component capybara green\n"
            + "blue-component capybara blue\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    ImageModel image = new ImageModelRGB(readPPM("res/capybara.ppm"));
    ImageModel redImage = new RGBFilter(0).apply(image);
    ImageModel greenImage = new RGBFilter(1).apply(image);
    ImageModel blueImage = new RGBFilter(2).apply(image);

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        assertEquals(cache.getImage("red").getPixel(i, j).getRed(),
                redImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("red").getPixel(i, j).getGreen(),
                redImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("red").getPixel(i, j).getBlue(),
                redImage.getPixel(i, j).getBlue());
        assertEquals(cache.getImage("green").getPixel(i, j).getRed(),
                greenImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("green").getPixel(i, j).getGreen(),
                greenImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("green").getPixel(i, j).getBlue(),
                greenImage.getPixel(i, j).getBlue());
        assertEquals(cache.getImage("blue").getPixel(i, j).getRed(),
                blueImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("blue").getPixel(i, j).getGreen(),
                blueImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("blue").getPixel(i, j).getBlue(),
                blueImage.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testOtherGreyscaleFiltersThroughCache() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "value-component capybara value\n"
            + "intensity-component capybara intensity\n"
            + "luma-component capybara luma\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    ImageModel image = new ImageModelRGB(readPPM("res/capybara.ppm"));
    ImageModel valueImage = new ValueFilter().apply(image);
    ImageModel intensityImage = new IntensityFilter().apply(image);
    ImageModel lumaImage = new LumaFilter().apply(image);

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        assertEquals(cache.getImage("value").getPixel(i, j).getRed(),
                valueImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("value").getPixel(i, j).getGreen(),
                valueImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("value").getPixel(i, j).getBlue(),
                valueImage.getPixel(i, j).getBlue());
        assertEquals(cache.getImage("intensity").getPixel(i, j).getRed(),
                intensityImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("intensity").getPixel(i, j).getGreen(),
                intensityImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("intensity").getPixel(i, j).getBlue(),
                intensityImage.getPixel(i, j).getBlue());
        assertEquals(cache.getImage("luma").getPixel(i, j).getRed(),
                lumaImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("luma").getPixel(i, j).getGreen(),
                lumaImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("luma").getPixel(i, j).getBlue(),
                lumaImage.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testFlipFiltersThroughCache() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "horizontal-flip capybara horizontal\n"
            + "vertical-flip capybara vertical\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    ImageModel image = new ImageModelRGB(readPPM("res/capybara.ppm"));
    ImageModel verticalImage = new VerticalFlipFilter().apply(image);
    ImageModel horizontalImage = new HorizontalFlipFilter().apply(image);

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        assertEquals(cache.getImage("vertical").getPixel(i, j).getRed(),
                verticalImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("vertical").getPixel(i, j).getGreen(),
                verticalImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("vertical").getPixel(i, j).getBlue(),
                verticalImage.getPixel(i, j).getBlue());
        assertEquals(cache.getImage("horizontal").getPixel(i, j).getRed(),
                horizontalImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("horizontal").getPixel(i, j).getGreen(),
                horizontalImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("horizontal").getPixel(i, j).getBlue(),
                horizontalImage.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testBrightnessFiltersThroughCache() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "brighten 50 capybara bright\n"
            + "darken 50 capybara dark\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    ImageModel image = new ImageModelRGB(readPPM("res/capybara.ppm"));
    ImageModel brightImage = new BrightnessFilter(50).apply(image);
    ImageModel darkImage = new BrightnessFilter(-50).apply(image);

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        assertEquals(cache.getImage("bright").getPixel(i, j).getRed(),
                brightImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("bright").getPixel(i, j).getGreen(),
                brightImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("bright").getPixel(i, j).getBlue(),
                brightImage.getPixel(i, j).getBlue());
        assertEquals(cache.getImage("dark").getPixel(i, j).getRed(),
                darkImage.getPixel(i, j).getRed());
        assertEquals(cache.getImage("dark").getPixel(i, j).getGreen(),
                darkImage.getPixel(i, j).getGreen());
        assertEquals(cache.getImage("dark").getPixel(i, j).getBlue(),
                darkImage.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testHelp() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("help\n" + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    assertEquals("Welcome to the image program!\n" +
            "Enter 'help' for a list of commands.\n" +
            "List of commands: \n" +
            "load <image file> <image name> - Loads an image from a file\n" +
            "save <image file> <image name> - Saves an image to a file\n" +
            "red-component <image name> <new image name> - Creates a new image with the " +
            "red component of the original image\n" +
            "green-component <image name> <new image name> - Creates a new image with the " +
            "green component of the original image\n" +
            "blue-component <image name> <new image name> - Creates a new image with the " +
            "blue component of the original image\n" +
            "value-component <image name> <new image name> - Creates a new image with the " +
            "value component of the original image\n" +
            "luma-component <image name> <new image name> - Creates a new image with the " +
            "luma component of the original image\n" +
            "intensity-component <image name> <new image name> - Creates a new image with the " +
            "intensity component of the original image\n" +
            "horizontal-flip <image name> <new image name> - Creates a new image with the " +
            "horizontal flip of the original image\n" +
            "vertical-flip <image name> <new image name> - Creates a new image with the " +
            "vertical flip of the original image\n" +
            "brighten <amount> <image name> <new image name> - Creates a new image with the " +
            "brightness increased by the amount\n" +
            "darken <amount> <image name> <new image name> - Creates a new image with the " +
            "brightness decreased by the amount\n" +
            "help - Prints this message\n" +
            "q - Exits the program\n", log.toString());
  }

  @Test
  public void testSaveImgFile() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load res/capybara.ppm capybara\n"
            + "save capybara.png capybara\n"
            + "save capybara.jpeg capybara\n"
            + "q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    File file = new File("capybara.png");
    assertTrue(file.exists());
    file.delete();

    File file2 = new File("capybara.jpeg");
    assertTrue(file2.exists());
    file2.delete();
  }

  // tests the messages sent to the view with a mock
  @Test
  public void testToView() {
    StringBuilder log = new StringBuilder();
    ImageView view = new MockView(log);

    StringReader in = new StringReader("q");
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();

    assertEquals("Message sent to the view is: Welcome to the image program!\n" +
            "Enter 'help' for a list of commands.\n" +
            "\n", log.toString());
  }

  /**
   * A bad Appendable, used for testing.
   */
  private static class CorruptAppendable implements Appendable {
    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException();
    }
  }
}