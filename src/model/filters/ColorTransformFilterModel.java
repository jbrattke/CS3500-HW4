package model.filters;

import model.ImageModel;
import model.ImageModelRGB;
import model.Pixel;
import model.PixelRGB;

/**
 * Represents an abstract filter model which applies a matrix transformation to each pixel.
 */
public abstract class ColorTransformFilterModel implements Filter {
  private final double[][] matrix;

  /**
   * Color transform filter model constructor.
   *
   * @param matrix The matrix to apply to each pixel. The kernel must be a
   *               square matrix with an odd number of rows and columns.
   */
  public ColorTransformFilterModel(double[][] matrix) {
    this.matrix = matrix;
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
        newPixels[i][j] = applyMatrix(image.getPixel(i, j));
      }
    }
    return new ImageModelRGB(newPixels);
  }

  private Pixel applyMatrix(Pixel pixel) {
    double red = 0;
    double green = 0;
    double blue = 0;

    red += pixel.getRed() * matrix[0][0]
            + pixel.getGreen() * matrix[0][1] + pixel.getBlue() * matrix[0][2];
    green += pixel.getRed() * matrix[1][0]
            + pixel.getGreen() * matrix[1][1] + pixel.getBlue() * matrix[1][2];
    blue += pixel.getRed() * matrix[2][0]
            + pixel.getGreen() * matrix[2][1] + pixel.getBlue() * matrix[2][2];

    red = Math.min(Math.max(red, 0), 255);
    green = Math.min(Math.max(green, 0), 255);
    blue = Math.min(Math.max(blue, 0), 255);

    return new PixelRGB((int) red, (int) green, (int) blue, pixel.getMaxColorVal());
  }
}
