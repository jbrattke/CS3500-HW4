package model.filters;

import model.Pixel;
import model.PixelRGB;

public class IntensityFilter extends FilterModel {
  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  @Override
  protected Pixel applyFilter(Pixel pixel) {
    int channelAvg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    return new PixelRGB(channelAvg, channelAvg, channelAvg);
  }
}
