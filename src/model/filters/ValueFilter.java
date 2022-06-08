package model.filters;

import model.Pixel;
import model.PixelRGB;

/**
 * Represents the value filter which changes an image's pixel values to the highest RGB value
 * in each pixel.
 */
public class ValueFilter extends FilterModel {
  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  @Override
  protected Pixel applyFilterToPixel(Pixel pixel) {
    int maxVal = Math.max(Math.max(pixel.getRed(), pixel.getGreen()), pixel.getBlue());
    return new PixelRGB(maxVal, maxVal, maxVal, pixel.getMaxColorVal());
  }
}
