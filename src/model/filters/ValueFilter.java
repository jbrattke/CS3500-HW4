package model.filters;

import model.Pixel;
import model.PixelRGB;

public class ValueFilter extends FilterModel {
  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  @Override
  protected Pixel applyFilter(Pixel pixel) {
    int maxVal = Math.max(Math.max(pixel.getRed(), pixel.getGreen()), pixel.getBlue());
    return new PixelRGB(maxVal, maxVal, maxVal, pixel.getMaxColorVal());
  }
}
