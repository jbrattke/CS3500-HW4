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
  protected int axis; // 0 for x, 1 for y

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
  protected Pixel findOpposite(ImageModel image, int x, int y) {
    int mid = axis == 0 ? image.getWidth() / 2 : image.getHeight() / 2;
    int dif = axis == 0 ? x - mid : y - mid;
    int newVal = mid;

    if (dif < 0) {
      newVal = mid + Math.abs(dif) - 1;
    } else if (dif > 0) {
      newVal = mid - dif;
    }

    if (axis == 0) {
      return image.getPixels()[newVal][y];
    } else {
      return image.getPixels()[x][newVal];
    }
  }
}
