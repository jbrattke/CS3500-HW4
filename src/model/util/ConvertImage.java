package model.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;

import model.ImageModel;
import model.Pixel;

/**
 * This class is used to convert an ImageModel to a file.
 */
public class ConvertImage {

  /**
   * This method converts an ImageModel to a ppm file.
   * @param image The ImageModel to convert.
   * @param filename The file to convert to.
   * @throws IllegalArgumentException if image cannot be converted to ppm with given arguments
   */
  public static void convertImageToPPM(ImageModel image, String filename)
          throws IllegalArgumentException {
    try {
      File newFile = new File(filename);

      if (newFile.createNewFile()) {
        FileWriter fw = new FileWriter(newFile);
        fw.write("P3\n");
        fw.write(image.getWidth() + " " + image.getHeight() + "\n");

        for (int i = 0; i < image.getWidth(); i++) {
          for (int j = 0; j < image.getHeight(); j++) {
            Pixel pixel = image.getPixel(i, j);
            fw.write(pixel.getRed() + "\n");
            fw.write(pixel.getGreen() + "\n");
            fw.write(pixel.getBlue() + "\n");
          }
        }
      } else {
        throw new IllegalArgumentException("File already exists.");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Converting Image to PPM Error: " + e.getMessage());
    }
  }

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
          newImage.setRGB(x, y, rgb);
        }
      }

      ImageIO.write(newImage, filename.split("\\.")[1], new File(filename));
    } catch (Exception e) {
      throw new IllegalArgumentException("Image cannot be converted: " + e.getMessage());
    }
  }

}
