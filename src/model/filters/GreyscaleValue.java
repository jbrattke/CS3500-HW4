package model.filters;

import model.ImageModel;
import model.ImageModelRGB;
import model.Pixel;
import model.PixelRGB;

public class GreyscaleValue extends GreyscaleFilter {

  /**
   * Applies the filter to the given image.
   *
   * @param image The image to apply the filter to.
   * @return The filtered image.
   */
  @Override
  public ImageModel apply(ImageModel image) {
    Pixel[][] srcPixels = image.getPixels();
    Pixel[][] newPixels = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int channelValue = Math.max(Math.max(srcPixels[i][j].getRed(), srcPixels[i][j].getGreen()),
                srcPixels[i][j].getBlue());
        newPixels[i][j] = new PixelRGB(channelValue, channelValue, channelValue);
      }
    }
    return new ImageModelRGB(newPixels);
  }

  /**
   * Applies the filter to the given pixel.
   *
   * @param pixel The pixel to apply the filter to.
   * @return The filtered pixel.
   */
  @Override
  protected Pixel applyFilter(Pixel pixel) {
    int maxVal = Math.max(Math.max(pixel.getRed(), pixel.getGreen()), pixel.getBlue());
    return new PixelRGB(maxVal, maxVal, maxVal);
  }
}
