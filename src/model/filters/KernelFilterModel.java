package model.filters;

import model.ImageModel;
import model.ImageModelRGB;
import model.Pixel;
import model.PixelRGB;

/**
 * Represents an abstract filter model which applies a kernel to each pixel in an image.
 */
public abstract class KernelFilterModel implements Filter {
  private final double[][] kernel;

  /**
   * Kernel filter model constructor.
   *
   * @param kernel The kernel to apply to each pixel.
   * The kernel must be a square matrix with an odd number of rows and columns.
   */
  public KernelFilterModel(double[][] kernel) {
    this.kernel = kernel;
  }

  /**
   * Applies the filter to the given image.
   *
   * @param image The image to apply the filter to.
   * @return The filtered image.
   */
  @Override
  public ImageModel apply(ImageModel image) {
    Pixel[][] newPixels = new Pixel[image.getWidth()][image.getHeight()];
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        newPixels[i][j] = applyKernel(image, i ,j);
      }
    }
    return new ImageModelRGB(newPixels);
  }

  private Pixel applyKernel(ImageModel image, int x, int y) {
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
