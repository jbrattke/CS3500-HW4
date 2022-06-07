package model;

public class PixelRGB implements Pixel{
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Creates a new pixel with the given color channels.
   * @param red
   * @param green
   * @param blue
   * @throws IllegalArgumentException if RBG values are not between 0 and 255
   */
  public PixelRGB(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Pixel RGB values must be between 0 and 255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
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
}
