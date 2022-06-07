package model.filters;

import model.Pixel;
import model.PixelRGB;

public class BrightnessFilter extends FilterModel{
  private final int value;

  /**
   * Constructor for BrightnessFilter
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
  protected Pixel applyFilter(Pixel pixel) {
    return new PixelRGB(pixel.getRed() + value, pixel.getGreen() + value, pixel.getBlue() + value);
  }
}
