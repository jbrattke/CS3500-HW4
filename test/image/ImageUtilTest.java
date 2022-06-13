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
}
