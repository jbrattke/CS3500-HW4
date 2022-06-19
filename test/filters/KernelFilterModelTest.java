package filters;

import org.junit.Before;
import org.junit.Test;

import model.ImageModel;
import model.Pixel;
import model.PixelRGB;
import model.filters.Filter;

import static org.junit.Assert.assertArrayEquals;

/**
 * This abstract class is used for testing all filters that extend KernelFilterModel.
 */
public abstract class KernelFilterModelTest {
  ImageModel image;
  Filter filter;
  double[][] kernel;

  @Before
  public abstract void setUp();

  @Test
  public void filterApplyTest() {
    ImageModel filteredImage = filter.apply(image);

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        assertArrayEquals(applyKernel(i,j).getAllChannels(),
                filteredImage.getPixel(i,j).getAllChannels());
      }
    }
  }

  protected Pixel applyKernel(int x, int y) {
    Pixel pixel = image.getPixel(x, y);
    double red = 0;
    double green = 0;
    double blue = 0;

    int mid = kernel.length / 2;

    for (int i = mid * -1; i < mid + 1; i++) {
      for (int j = mid * -1; j < mid + 1; j++) {
        if (x + i >= 0 && x + i < image.getWidth() && y + j >= 0 && y + j < image.getHeight()) {
          PixelRGB pixelRGB = (PixelRGB) image.getPixel(x + i, y + j);
          red += pixelRGB.getRed() * kernel[i + mid][j + mid];
          green += pixelRGB.getGreen() * kernel[i + mid][j + mid];
          blue += pixelRGB.getBlue() * kernel[i + mid][j + mid];
        }
      }
    }

    red = Math.min(Math.max(red, 0), 255);
    green = Math.min(Math.max(green, 0), 255);
    blue = Math.min(Math.max(blue, 0), 255);

    return new PixelRGB((int) red,(int) green,(int) blue, pixel.getMaxColorVal());
  }
}
