package model;

/**
 * This class represents a filter that greyscales an image by
 * setting all channels to the red channel value.
 */
public class RedFilter implements Filter{
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
        int red = srcPixels[i][j].getRed();
        newPixels[i][j] = new PixelRGB(red, red, red);
      }
    }
    return new ImageModelRGB(newPixels);
  }
}
