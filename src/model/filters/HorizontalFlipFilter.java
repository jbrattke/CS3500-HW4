package model.filters;

import model.ImageModel;
import model.Pixel;

public class HorizontalFlipFilter extends FlipFilterModel {
  /**
   * Applies the filter to the given pixel.
   *
   * @param image the given image that the filter is being applied too
   * @param x     the x coord of pixel
   * @param y     the y coord of pixel
   * @return The filtered pixel.
   */
  @Override
  protected Pixel findOpposite(ImageModel image, int x, int y) {
    int mid = image.getWidth() / 2;
    int dif = x - mid;
    int xVal = image.getWidth() / 2;
    if (dif < 0) {
      xVal = mid + Math.abs(dif) - 1;
    } else if (dif > 0) {
      xVal = mid - dif;
    }
    return image.getPixels()[xVal][y];
  }
}
