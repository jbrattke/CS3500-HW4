package model.filters;

import model.Pixel;
import model.PixelRGB;

/**
 * Filter that makes all of a pixel's values brighter by a given value.
 */
public class BrightnessFilter extends FilterModel {
  private final int value;

  /**
   * Constructor for BrightnessFilter.
   * @param value the value to be added to the brightness
   */
  public BrightnessFilter(int value) {
    this.value = value;
  }

  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  @Override
  protected Pixel applyFilterToPixel(Pixel pixel) {
    int red = Math.min(pixel.getRed() + value, 255);
    int green = Math.min(pixel.getGreen() + value, 255);
    int blue = Math.min(pixel.getBlue() + value, 255);
    return new PixelRGB(red, green, blue, pixel.getMaxColorVal());
  }
}
