package image;

import org.junit.Test;

import java.io.File;

import model.ImageModel;
import model.ImageModelRGB;
import model.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * This class is used for testing all Utils in the ImageUtil class.
 */
public class ImageUtilTest {

  @Test
  public void testReadPPM() {
    ImageModel image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    assertEquals(image.getWidth(), 550);
    assertEquals(image.getHeight(), 367);
    assertEquals(image.getPixel(0, 0).getRed(), 28);
    assertEquals(image.getPixel(0, 0).getGreen(), 28);
    assertEquals(image.getPixel(0, 0).getBlue(), 26);
    assertEquals(image.getPixel(0,0).getMaxColorVal(), 255);
  }

  @Test
  public void testComparePPM() {
    assertEquals(ImageUtil.comparePPM("res/capybara.ppm", "res/capybara.ppm"), true);
    assertEquals(ImageUtil.comparePPM("res/capybara.ppm", "res/red.ppm"), false);
  }

  @Test
  public void testConvertImageToPPM() {
    ImageModel image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    ImageUtil.convertImageToPPM(image, "test.ppm");
    assertEquals(ImageUtil.comparePPM("test.ppm", "res/capybara.ppm"), true);
    File file = new File("test.ppm");
    file.delete();
  }

  @Test
  public void testConvertImageToFile() {
    ImageModel image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    ImageUtil.convertImageToFile(image, "test.jpeg");
    File file = new File("test.jpeg");
    assertEquals(file.exists(), true);
    file.delete();
  }

  @Test
  public void testConvertImageToFile2() {
    ImageModel image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    ImageUtil.convertImageToFile(image, "test.png");
    File file = new File("test.png");
    assertEquals(file.exists(), true);
    file.delete();
  }

  @Test
  public void testConvertImageToFile3() {
    ImageModel image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    ImageUtil.convertImageToFile(image, "test.bmp");
    File file = new File("test.bmp");
    assertEquals(file.exists(), true);
    file.delete();
  }

  @Test
  public void testConvertFileToImage() {
    ImageModel image = ImageUtil.convertFileToImage("res/capybara.png");
    assertEquals(image.getWidth(), 550);
    assertEquals(image.getHeight(), 367);
    assertEquals(image.getPixel(0, 0).getRed(), 28);
    assertEquals(image.getPixel(0, 0).getGreen(), 28);
    assertEquals(image.getPixel(0, 0).getBlue(), 26);
    assertEquals(image.getPixel(0, 0).getMaxColorVal(), 255);

    ImageModel image2 = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        assertEquals(image.getPixel(i, j).getRed(), image2.getPixel(i, j).getRed());
        assertEquals(image.getPixel(i, j).getGreen(), image2.getPixel(i, j).getGreen());
        assertEquals(image.getPixel(i, j).getBlue(), image2.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testFindScript() {
    String script = ImageUtil.findScript("res/script.txt");
    assertEquals(script, "load res/capybara.ppm capy\n" +
            "red-component capy red\n" +
            "green-component capy green\n" +
            "blue-component capy blue\n" +
            "luma-component capy luma\n" +
            "intensity-component capy intensity\n" +
            "value-component capy value\n" +
            "horizontal-flip capy horizontal\n" +
            "vertical-flip capy vertical\n" +
            "brighten 50 capy bright\n" +
            "darken 50 capy dark\n" +
            "sepia capy sepia\n" +
            "greyscale capy greyscale\n" +
            "blur capy blur\n" +
            "sharpen capy sharpen\n" +
            "save res/red.png red\n" +
            "save res/green.png green\n" +
            "save res/blue.png blue\n" +
            "save res/luma.png luma\n" +
            "save res/intensity.png intensity\n" +
            "save res/value.png value\n" +
            "save res/horizontal.png horizontal\n" +
            "save res/vertical.png vertical\n" +
            "save res/bright.png bright\n" +
            "save res/dark.png dark\n" +
            "save res/sepia.png sepia\n" +
            "save res/greyscale.png greyscale\n" +
            "save res/blur.png blur\n" +
            "save res/sharpen.png sharpen\n" +
            "q\n");
  }
}
