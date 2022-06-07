package model;

/**
 * Represents an image and its associated methods, immutable.
 */
public interface ImageModel {

  /**
   * Returns the width of the image.
   * @return the width of the image
   */
  int getWidth();

  /**
   * Returns the height of the image.
   * @return the height of the image
   */
  int getHeight();

  /**
   * Returns the pixel at the specified coordinates.
   * @return the pixel at the specified coordinates
   */
  Pixel getPixel(int x, int y);

  /**
   * Returns the image as a 2D array of pixels.
   * @return the image as a 2D array of pixels
   */
  Pixel[][] getPixels();
}
