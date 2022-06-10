package model;

/**
 * Represents a pixel in an image.
 */
public interface Pixel {

  /**
   * Gets the red component of the pixel.
   *
   * @return the red component of the pixel
   */
  int getRed();

  /**
   * Gets the green component of the pixel.
   *
   * @return the green component of the pixel
   */
  int getGreen();

  /**
   * Gets the blue component of the pixel.
   *
   * @return the blue component of the pixel
   */
  int getBlue();

  /**
   * Gets all color channels from pixel.
   *
   * @return the color channels of pixel as an array
   */
  int[] getAllChannels();

  /**
   * Gets max color value for pixel.
   *
   * @return the color channels of pixel as an array
   */
  int getMaxColorVal();
}
