package model.util;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import model.Pixel;
import model.PixelRGB;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method 
 *  as required.
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
    }
    catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File "+filename+ " not found!");
    }

    if (!filename.split("\\.")[1].equals("ppm")) {
      throw new IllegalArgumentException("File is not a PPM file!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0)!='#') {
            builder.append(s+System.lineSeparator());
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
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

    Pixel[][] pixels = new Pixel[width][height];
    for (int i=0;i<height;i++) {
        for (int j=0;j<width;j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            pixels[j][i] = new PixelRGB(r,g,b, maxValue);
        }
    }

    return pixels;
  }
}

