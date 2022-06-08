package model.filters;

import model.Pixel;
import model.PixelRGB;

/**
 * This class represents a filter that greyscales an image by
 * setting all channels to the red channel value.
 */
public class RGBFilter extends FilterModel {
  private final int channel;

  /**
   * Constructs a new RGB greyscale filter.
   * @param channel the pixel RGB channel to use for greyscaling
   */
  public RGBFilter(int channel) {
    this.channel = channel;
  }

  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  @Override
  protected Pixel applyFilterToPixel(Pixel pixel) {
    int channelValue = getPixelChannel(pixel);
    return new PixelRGB(channelValue, channelValue, channelValue, pixel.getMaxColorVal());
  }

  private int getPixelChannel(Pixel pixel) {
    if (channel == 0) {
      return pixel.getRed();
    } else if (channel == 1) {
      return pixel.getGreen();
    } else {
      return pixel.getBlue();
    }
  }
}
