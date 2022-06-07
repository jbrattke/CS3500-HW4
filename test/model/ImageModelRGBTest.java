package model;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This class is used for testing the ImageModelRGB class.
 */
public class ImageModelRGBTest {

  @Test
  public void constructorTest() {
    //creating a board first
    Pixel[][] board = new Pixel[100][100];
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        Pixel pixel = new PixelRGB(i, j, i);
        board[i][j] = pixel;
      }
    }

    //testing ImageModelRGB constructor
    ImageModel image = new ImageModelRGB(board);
    assertEquals(image.getWidth(), 100);
    assertEquals(image.getHeight(), 100);
    assertArrayEquals(image.getPixels(), board);
    assertEquals(image.getPixel(0, 0), board[0][0]);
    assertEquals(image.getPixel(99, 99), board[99][99]);
  }

  @Test
  public void constructorTest2() {
    //creating a board first
    Pixel[][] board = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Pixel pixel = new PixelRGB(i, j, i);
        board[i][j] = pixel;
      }
    }

    //testing ImageModelRGB constructor
    ImageModel image = new ImageModelRGB(board);
    assertEquals(image.getWidth(), 3);
    assertEquals(image.getHeight(), 3);
    assertArrayEquals(image.getPixels(), board);
    assertEquals(image.getPixel(0, 0), board[0][0]);
    assertEquals(image.getPixel(2, 2), board[2][2]);
  }
}
