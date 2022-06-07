package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.filters.BrightnessFilter;
import model.filters.Filter;
import model.ImageModel;
import model.filters.HorizontalFlipFilter;
import model.filters.IntensityFilter;
import model.filters.LumaFilter;
import model.filters.RGBFilter;
import model.filters.ValueFilter;
import model.filters.VerticalFlipFilter;
import model.util.ImageUtil;
import model.Pixel;
import model.ImageModelRGB;
import view.ImageView;

import static model.util.ConvertImage.convertImageToFile;

public class ImageControllerImpl implements ImageController {
  private final Readable in;
  private final ImageView view;
  private Map<String, ImageModel> images;

  /**
   * Constructor for ImageControllerImpl.
   * @param r the Readable object
   */
  public ImageControllerImpl(Readable r, ImageView view) {
    images = new HashMap<String, ImageModel>();
    in = r;
    this.view = view;
  }

  /**
   * This method runs the program. ??
   * @throws IllegalStateException if appendable cannot be printed to
   */
  @Override
  public void run() {
    Scanner inScan = new Scanner(in);
    boolean exit = false;

    renderViewMessage("Welcome to the image program!\n");

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
              renderViewMessage("Error: " + e.getMessage());
            }
            break;

          case "save":
            if (images.containsKey(inputArray[2])) {
              convertImageToFile(images.get(inputArray[2]), inputArray[1]);
            } else {
              renderViewMessage("Image with name does not exist!");
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
            if (images.containsKey(inputArray[1])) {
              Filter horizontalFlipFilter = new HorizontalFlipFilter();
              ImageModel filteredImage = horizontalFlipFilter.apply(images.get(inputArray[1]));
              images.put(inputArray[2], filteredImage);
            }
            break;

          case "vertical-flip":
            if (images.containsKey(inputArray[1])) {
              Filter verticalFlipFilter = new VerticalFlipFilter();
              ImageModel filteredImage = verticalFlipFilter.apply(images.get(inputArray[1]));
              images.put(inputArray[2], filteredImage);
            }
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
            renderViewMessage("Invalid input");
        }
      }
    }
  }

  private void renderViewMessage(String text) {
    try {
      view.renderMessage(text);
    } catch (Exception e) {
      throw new IllegalStateException("Cannot print to view appendable!");
    }
  }
}
