package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.filters.BrightnessFilter;
import model.filters.Filter;
import model.ImageModel;
import model.filters.IntensityFilter;
import model.filters.LumaFilter;
import model.filters.RGBFilter;
import model.filters.ValueFilter;
import model.util.ImageUtil;
import model.Pixel;
import model.ImageModelRGB;

import static model.util.ConvertImage.convertImageToFile;

public class ImageControllerImpl implements ImageController {
  private final Readable in;
  private Map<String, ImageModel> images;

  /**
   * Constructor for ImageControllerImpl.
   * @param r the Readable object
   */
  public ImageControllerImpl(Readable r) {
    images = new HashMap<String, ImageModel>();
    in = r;
  }

  /**
   * This method runs the program. ??
   */
  @Override
  public void run() {
    Scanner inScan = new Scanner(in);
    boolean exit = false;

    System.out.println("Welcome to the Image program");

    while (!exit) {
      if (inScan.hasNextLine()) {
        String input = inScan.nextLine();
        String[] inputArray = input.split(" ");

        switch (inputArray[0]) {
          case "load":
            try {
              Pixel[][] pixel = ImageUtil.readPPM(inputArray[1]);
              if (images.containsKey(inputArray[1])) {
                images.replace(inputArray[1], new ImageModelRGB(pixel));
              } else {
                images.put(inputArray[2], new ImageModelRGB(pixel));
              }
            } catch (Exception e) {
              System.out.println("Error: " + e.getMessage());
            }
            break;

          case "save":
            if (images.containsKey(inputArray[2])) {
              convertImageToFile(images.get(inputArray[2]), inputArray[1]);
            } else {
              System.out.println("Image with name does not exist!");
            }
            break;

          case "red-component":
            if (images.containsKey(inputArray[1])) {
              Filter redFilter = new RGBFilter(0);
              ImageModel filteredImage = redFilter.apply(images.get(inputArray[1]));
              images.put(inputArray[2], filteredImage);
            }
            break;

          case "green-component":
            if (images.containsKey(inputArray[1])) {
              Filter greenFilter = new RGBFilter(1);
              ImageModel filteredImage = greenFilter.apply(images.get(inputArray[1]));
              images.put(inputArray[2], filteredImage);
            }
            break;

          case "blue-component":
            if (images.containsKey(inputArray[1])) {
              Filter blueFilter = new RGBFilter(2);
              ImageModel filteredImage = blueFilter.apply(images.get(inputArray[1]));
              images.put(inputArray[2], filteredImage);
            }
            break;

          case "value-component":
            if (images.containsKey(inputArray[1])) {
              Filter valueFilter = new ValueFilter();
              ImageModel filteredImage = valueFilter.apply(images.get(inputArray[1]));
              images.put(inputArray[2], filteredImage);
            }
            break;

          case "luma-component":
            if (images.containsKey(inputArray[1])) {
              Filter lumaFilter = new LumaFilter();
              ImageModel filteredImage = lumaFilter.apply(images.get(inputArray[1]));
              images.put(inputArray[2], filteredImage);
            }
            break;

          case "intensity-component":
            if (images.containsKey(inputArray[1])) {
              Filter intensityFilter = new IntensityFilter();
              ImageModel filteredImage = intensityFilter.apply(images.get(inputArray[1]));
              images.put(inputArray[2], filteredImage);
            }
            break;

          case "horizontal-flip":
            break;

          case "vertical-flip":
            break;

          case "brighten":
            if (images.containsKey(inputArray[2])) {
              Filter brightnessFilter = new BrightnessFilter(Integer.parseInt(inputArray[1]));
              ImageModel filteredImage = brightnessFilter.apply(images.get(inputArray[2]));
              images.put(inputArray[3], filteredImage);
            }
            break;

          case "exit":
            exit = true;
            break;
          default:
            System.out.println("Invalid input");
        }
      }
    }
  }
}
