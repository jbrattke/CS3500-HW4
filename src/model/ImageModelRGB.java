package model;

/**
 * Represents an image model, implements methods from the ImageModel interface.
 */
public class ImageModelRGB implements ImageModel {
  private final Pixel[][] pixels;
  private final int width;
  private final int height;

  /**
   * Constructs an image model.
   *
   * @param pixels the pixels of the image
   */
  public ImageModelRGB(Pixel[][] pixels) {
    this.pixels = pixels;
    this.width = pixels.length;
    this.height = pixels[0].length;
  }

  /**
   * Returns the width of the image.
   *
   * @return the width of the image
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the image.
   *
   * @return the height of the image
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * Returns the pixel at the specified coordinates.
   *
   * @param x
   * @param y
   * @return the pixel at the specified coordinates
   */
  @Override
  public Pixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  /**
   * Returns the image as a 2D array of pixels.
   *
   * @return the image as a 2D array of pixels
   */
  @Override
  public Pixel[][] getPixels() {
    return pixels;
  }
}
