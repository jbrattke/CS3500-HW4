package model.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import model.ImageModel;
import model.Pixel;

public class ConvertImage {

  /**
   * Converts given image to file, puts file in working directory.
   * @param image image to be converted
   * @param filename requested filename(type must be included)
   * @throws IllegalArgumentException if image cannot be converted
   */
  public static void convertImageToFile(ImageModel image, String filename)
          throws IllegalArgumentException {
    try {
      BufferedImage newImage =
              new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < image.getWidth(); x++) {
        for (int y = 0; y < image.getHeight(); y++) {
          Pixel pixel = image.getPixel(x,y);
          int rgb = pixel.getRed();
          rgb = (rgb << 8) + pixel.getGreen();
          rgb = (rgb << 8) + pixel.getBlue();
          newImage.setRGB(y, x, rgb);
        }
      }

      ImageIO.write(newImage, filename.split("\\.")[1], new File(filename));
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be converted: " + e.getMessage());
    }
  }

}
