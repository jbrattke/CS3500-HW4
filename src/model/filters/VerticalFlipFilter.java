package model.filters;

import model.ImageModel;
import model.Pixel;

public class VerticalFlipFilter extends FlipFilterModel {
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
    int mid = image.getHeight() / 2;
    int dif = y - mid;
    int yVal = image.getHeight() / 2;
    if (dif < 0) {
      yVal = mid + Math.abs(dif) - 1;
    } else if (dif > 0) {
      yVal = mid - dif;
    }
    return image.getPixels()[x][yVal];
  }
}
