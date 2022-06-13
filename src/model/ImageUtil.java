package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controller.ImageControllerImpl;
import view.ImageView;
import view.ImageViewImpl;

/**
 * This class contains utility methods to read and write images.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException if the file is not found.
   */
  public static Pixel[][] readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixel[][] pixels = new Pixel[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[j][i] = new PixelRGB(r, g, b, maxValue);
      }
    }

    return pixels;
  }

  /**
   * Converts given ppm to ImageModel, and compares models.
   *
   * @param filename1 first file
   * @param filename2 second file
   * @return true if models are equal, false otherwise
   */
  public static boolean comparePPM(String filename1, String filename2) {
    Pixel[][] pixels1;
    Pixel[][] pixels2;
    try {
      pixels1 = readPPM(filename1);
      pixels2 = readPPM(filename2);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return false;
    }

    if (pixels1.length != pixels2.length) {
      System.out.println("Widths are different");
      return false;
    }

    if (pixels1[0].length != pixels2[0].length) {
      System.out.println("Heights are different");
      return false;
    }

    for (int i = 0; i < pixels1.length; i++) {
      for (int j = 0; j < pixels1[0].length; j++) {
        if (pixels1[i][j].getRed() != pixels2[i][j].getRed()
                || pixels1[i][j].getGreen() != pixels2[i][j].getGreen()
                || pixels1[i][j].getBlue() != pixels2[i][j].getBlue()) {
          System.out.println("Pixels are different");
          return false;
        }
      }
    }

    return true;
  }

  /**
   * This method converts an ImageModel to a ppm file.
   *
   * @param image    The ImageModel to convert.
   * @param filename The file to convert to.
   * @throws IllegalArgumentException if image cannot be converted to ppm with given arguments
   */
  public static void convertImageToPPM(ImageModel image, String filename)
          throws IllegalArgumentException {
    try {
      File newFile = new File(filename);
      FileWriter fw = new FileWriter(newFile);

      fw.write("P3\n");
      fw.write(image.getWidth() + " " + image.getHeight() + "\n");
      fw.write(image.getPixel(0, 0).getMaxColorVal() + "\n");

      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          Pixel pixel = image.getPixel(j, i);
          fw.write(pixel.getRed() + "\n");
          fw.write(pixel.getGreen() + "\n");
          fw.write(pixel.getBlue() + "\n");
        }
      }
      fw.close();
    } catch (Exception e) {
      throw new IllegalArgumentException("Converting Image to PPM Error: " + e.getMessage());
    }
  }

  /**
   * Converts given image to file(must be png/jpg), puts file in working directory.
   *
   * @param image    image to be converted
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
          Pixel pixel = image.getPixel(x, y);
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

  /**
   * This method is used to convert a file to an ImageModel.
   *
   * @param fileName path of file to be converted(must be png/jpeg)
   * @return the converted ImageModel on success
   * @throws IllegalArgumentException if the file is not found/cannot be converted
   */
  public static ImageModel convertFileToImage(String fileName) {
    BufferedImage imageFile = null;
    try {
      imageFile = ImageIO.read(new File(fileName));
    } catch (Exception e) {
      throw new IllegalArgumentException("File not found!");
    }
    Pixel[][] pixels = new Pixel[imageFile.getWidth()][imageFile.getHeight()];
    for (int x = 0; x < imageFile.getWidth(); x++) {
      for (int y = 0; y < imageFile.getHeight(); y++) {
        Color c = new Color(imageFile.getRGB(x, y));
        pixels[x][y] = new PixelRGB(c.getRed(), c.getGreen(), c.getBlue(), 255);
      }
    }
    return new ImageModelRGB(pixels);
  }

  /**
   * This method is used to run a script, the script must follow the controller format.
   * @param filename path of script
   */
  public static void runScript(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    String script = "";
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        script += s + "\n";
      }
    }

    //RUNNING SCRIPT
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);
    StringReader in = new StringReader(script);
    ImageCache cache = new ImageCacheModel();
    ImageControllerImpl controller = new ImageControllerImpl(cache, view, in);
    controller.run();
  }
}

