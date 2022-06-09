package image;

import org.junit.Test;

import model.Pixel;
import model.PixelRGB;

import static org.junit.Assert.assertEquals;

/**
 * This class is used for testing the PixelRGB class.
 */
public class PixelRGBTest {

  @Test
  public void constructorTest() {
    Pixel pixel = new PixelRGB(0, 1, 2, 255);
    assertEquals(pixel.getRed(), 0);
    assertEquals(pixel.getGreen(), 1);
    assertEquals(pixel.getBlue(), 2);
    assertEquals(pixel.getAllChannels()[0], 0);
    assertEquals(pixel.getAllChannels()[1], 1);
    assertEquals(pixel.getAllChannels()[2], 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorTestException1() {
    Pixel pixel = new PixelRGB(-1, 0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorTestException2() {
    Pixel pixel = new PixelRGB(256, 0, 0, 255);
  }
}
