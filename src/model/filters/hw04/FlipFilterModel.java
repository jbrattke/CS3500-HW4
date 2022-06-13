package model.filters.hw04;

import model.ImageModel;
import model.ImageModelRGB;
import model.Pixel;
import model.filters.Filter;

/**
 * Abstract class for filters that flip an image to unify them, seperating them from
 * pixel value manipulating filters and give them their similar apply method.
 */
public abstract class FlipFilterModel implements Filter {
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
        newPixels[i][j] = findOpposite(image, i ,j);
      }
    }
    return new ImageModelRGB(newPixels);
  }

  /**
   * Applies the filter to the given pixel.
   *
   * @param image the given image that the filter is being applied too
   * @param x the x coord of pixel
   * @param y the y coord of pixel
   * @return The filtered pixel.
   */
  protected abstract Pixel findOpposite(ImageModel image, int x, int y);
}
