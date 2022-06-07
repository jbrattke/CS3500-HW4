package model.filters;

import model.ImageModel;
import model.ImageModelRGB;
import model.Pixel;
import model.PixelRGB;

public abstract class GreyscaleFilter implements Filter {
  /**
   * Applies the filter to the given image.
   *
   * @param image The image to apply the filter to.
   * @return The filtered image.
   */
  @Override
  public ImageModel apply(ImageModel image) {
    Pixel[][] srcPixels = image.getPixels();
    Pixel[][] newPixels = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        newPixels[i][j] = applyFilter(srcPixels[i][j]);
      }
    }
    return new ImageModelRGB(newPixels);
  }

  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  protected abstract Pixel applyFilter(Pixel pixel);
}
