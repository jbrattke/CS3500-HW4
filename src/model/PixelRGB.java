package model;

/**
 * Implementation of a pizel, which contains its red, green, and blue values.
 * It also has a maximum value.
 */
public class PixelRGB implements Pixel {
  private final int red;
  private final int green;
  private final int blue;
  private final int maxVal;

  /**
   * Creates a new pixel with the given color channels.
   * @param red pixel's red value
   * @param green pixel's green value
   * @param blue pixel's blue value
   * @throws IllegalArgumentException if RBG values are not between 0 and 255
   */
  public PixelRGB(int red, int green, int blue, int maxVal) throws IllegalArgumentException {
    if (red < 0 || red > maxVal || green < 0 || green > maxVal || blue < 0 || blue > maxVal) {
      throw new IllegalArgumentException("Pixel RGB values must be between 0 and 255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.maxVal = maxVal;
  }

  /**
   * Gets the red component of the pixel.
   *
   * @return the red component of the pixel
   */
  @Override
  public int getRed() {
    return red;
  }

  /**
   * Gets the green component of the pixel.
   *
   * @return the green component of the pixel
   */
  @Override
  public int getGreen() {
    return green;
  }

  /**
   * Gets the blue component of the pixel.
   *
   * @return the blue component of the pixel
   */
  @Override
  public int getBlue() {
    return blue;
  }

  /**
   * Gets all color channels from pixel.
   *
   * @return the color channels of pixel as an array
   */
  @Override
  public int[] getAllChannels() {
    return new int[] {red, green, blue};
  }

  /**
   * Gets max color value for pixel.
   *
   * @return the color channels of pixel as an array
   */
  @Override
  public int getMaxColorVal() {
    return maxVal;
  }
}
