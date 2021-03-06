package model.filters.hw04;

import model.Pixel;
import model.PixelRGB;

/**
 * Represents the luma filter which applies an algorithm to each pixel's RGB values.
 */
public class LumaFilter extends FilterModel {
  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  @Override
  protected Pixel applyFilterToPixel(Pixel pixel) {
    double lumaValue = pixel.getRed() * 0.2126
            + pixel.getGreen() * 0.7152
            + pixel.getBlue() * 0.0722;
    return new PixelRGB((int) lumaValue, (int) lumaValue, (int) lumaValue, pixel.getMaxColorVal());
  }
}
