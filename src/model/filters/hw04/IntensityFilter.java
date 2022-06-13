package model.filters.hw04;

import model.Pixel;
import model.PixelRGB;

/**
 * Represents the intensity filter. Used to change the values of an image's pixels to the
 * average of them.
 */
public class IntensityFilter extends FilterModel {
  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  @Override
  protected Pixel applyFilterToPixel(Pixel pixel) {
    int channelAvg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    return new PixelRGB(channelAvg, channelAvg, channelAvg, pixel.getMaxColorVal());
  }
}
