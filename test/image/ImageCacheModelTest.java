package image;

import org.junit.Test;

import model.ImageCacheModel;
import model.ImageModelRGB;
import model.Pixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This class is used for testing the image cache model.
 */
public class ImageCacheModelTest {

  @Test
  public void testConstructor() {
    ImageCacheModel image = new ImageCacheModel();
    assertEquals(image.getSize(), 0);
  }

  @Test
  public void testAddAndGet() {
    ImageCacheModel image = new ImageCacheModel();
    Pixel[][] pixels = new Pixel[1][1];
    image.addImage("test", new ImageModelRGB(pixels));

    assertEquals(image.getSize(), 1);
    assertArrayEquals(image.getImage("test").getPixels(),
            new ImageModelRGB(pixels).getPixels());

    Pixel[][] pixels2 = new Pixel[2][2];
    image.addImage("test", new ImageModelRGB(pixels2));
    assertArrayEquals(image.getImage("test").getPixels(),
            new ImageModelRGB(pixels2).getPixels());
  }

  @Test
  public void testSize() {
    ImageCacheModel image = new ImageCacheModel();
    assertEquals(image.getSize(), 0);

    Pixel[][] pixels = new Pixel[1][1];
    image.addImage("test", new ImageModelRGB(pixels));
    assertEquals(image.getSize(), 1);

    image.addImage("test2", new ImageModelRGB(pixels));
    assertEquals(image.getSize(), 2);
  }

  @Test
  public void testHas() {
    ImageCacheModel image = new ImageCacheModel();
    assertEquals(image.hasImage("test"), false);

    Pixel[][] pixels = new Pixel[1][1];
    image.addImage("test", new ImageModelRGB(pixels));
    assertEquals(image.hasImage("test"), true);
  }
}
