package model.filters.hw04;

import model.ImageModel;
import model.ImageModelRGB;
import model.Pixel;
import model.filters.Filter;

/**
 * Abstract class that is used for pixel value manipulation filters to unify them
 * and give them the similar apply method.
 */
public abstract class FilterModel implements Filter {
  /**
   * Applies the filter to the given image.
   *
   * @param image The image to apply the filter to.
   * @return The filtered image.
   */
  @Override
  public ImageModel apply(ImageModel image) {
    Pixel[][] srcPixels = image.getPixels();
    Pixel[][] newPixels = new Pixel[image.getWidth()][image.getHeight()];
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        newPixels[i][j] = applyFilterToPixel(srcPixels[i][j]);
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
  protected abstract Pixel applyFilterToPixel(Pixel pixel);
}
