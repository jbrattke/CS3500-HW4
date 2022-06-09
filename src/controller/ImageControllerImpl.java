package controller;

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
import static model.util.ConvertImage.convertImageToPPM;

/**
 * Controller implementation to take input from the user and manage the model and view
 * accordingly.
 */
public class ImageControllerImpl implements ImageController {
  private final Readable in;
  private final ImageView view;
  private Map<String, ImageModel> images;
  private boolean exit = false; //state tracker for the program

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
   * @throws IllegalArgumentException if there is no input from controller readable
   */
  @Override
  public void run() throws IllegalStateException, IllegalArgumentException {
    Scanner inScan = new Scanner(in);

    renderViewMessage("Welcome to the image program!\n");

    while (!exit) {
      if (inScan.hasNextLine()) {
        String input = inScan.nextLine();
        String[] inputArray = input.split(" ");

        try { //will catch error's from input that processInput() does not handle
          processInput(inputArray);
        } catch (Exception e) {
          renderViewMessage("Error: " + e.getMessage());
        }

      } else {
        throw new IllegalArgumentException("No input!");
      }
    }
  }

  /**
   * This method process's the input given in the form of a string array.
   */
  private void processInput(String[] inputArray) {
    switch (inputArray[0]) {
      case "load":
        Pixel[][] pixel = ImageUtil.readPPM(inputArray[1]);
        if (images.containsKey(inputArray[1])) {
          images.replace(inputArray[1], new ImageModelRGB(pixel));
        } else {
          images.put(inputArray[2], new ImageModelRGB(pixel));
        }
        renderViewMessage("Loaded " + inputArray[1] + " successfully!\n");
        break;

      case "save":
        if (images.containsKey(inputArray[2])) {
          if (inputArray[1].split("\\.").length > 1
                  && inputArray[1].split("\\.")[1].equals("ppm")) {
            convertImageToPPM(images.get(inputArray[2]), inputArray[1]);
          } else {
            convertImageToFile(images.get(inputArray[2]), inputArray[1]);
          }
          renderViewMessage("Saved " + inputArray[1] + " successfully!\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "red-component":
        if (images.containsKey(inputArray[1])) {
          Filter redFilter = new RGBFilter(0);
          ImageModel filteredImage = redFilter.apply(images.get(inputArray[1]));
          images.put(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "green-component":
        if (images.containsKey(inputArray[1])) {
          Filter greenFilter = new RGBFilter(1);
          ImageModel filteredImage = greenFilter.apply(images.get(inputArray[1]));
          images.put(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "blue-component":
        if (images.containsKey(inputArray[1])) {
          Filter blueFilter = new RGBFilter(2);
          ImageModel filteredImage = blueFilter.apply(images.get(inputArray[1]));
          images.put(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "value-component":
        if (images.containsKey(inputArray[1])) {
          Filter valueFilter = new ValueFilter();
          ImageModel filteredImage = valueFilter.apply(images.get(inputArray[1]));
          images.put(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "luma-component":
        if (images.containsKey(inputArray[1])) {
          Filter lumaFilter = new LumaFilter();
          ImageModel filteredImage = lumaFilter.apply(images.get(inputArray[1]));
          images.put(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "intensity-component":
        if (images.containsKey(inputArray[1])) {
          Filter intensityFilter = new IntensityFilter();
          ImageModel filteredImage = intensityFilter.apply(images.get(inputArray[1]));
          images.put(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "horizontal-flip":
        if (images.containsKey(inputArray[1])) {
          Filter horizontalFlipFilter = new HorizontalFlipFilter();
          ImageModel filteredImage = horizontalFlipFilter.apply(images.get(inputArray[1]));
          images.put(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "vertical-flip":
        if (images.containsKey(inputArray[1])) {
          Filter verticalFlipFilter = new VerticalFlipFilter();
          ImageModel filteredImage = verticalFlipFilter.apply(images.get(inputArray[1]));
          images.put(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "brighten":
        if (images.containsKey(inputArray[2])) {
          Filter brightnessFilter = new BrightnessFilter(Integer.parseInt(inputArray[1]));
          ImageModel filteredImage = brightnessFilter.apply(images.get(inputArray[2]));
          images.put(inputArray[3], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[3] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "darken":
        if (images.containsKey(inputArray[2])) {
          Filter brightnessFilter = new BrightnessFilter(Integer.parseInt(inputArray[1]) * -1);
          ImageModel filteredImage = brightnessFilter.apply(images.get(inputArray[2]));
          images.put(inputArray[3], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[3] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "help":
        printHelpMessage();
        break;

      case "q":
        exit = true;
        break;

      default:
        renderViewMessage("Invalid input\n");
    }
  }

  /**
   * Sends message to the view to display the help message.
   */
  private void printHelpMessage() {
    renderViewMessage("Help!\n");
  }

  /**
   * Sends given text to the view where it displays the message to the output destination.
   * @param text message to be displayed
   * @throws IllegalStateException if rendermessage method throws an io exception
   */
  private void renderViewMessage(String text) throws IllegalStateException {
    try {
      view.renderMessage(text);
    } catch (Exception e) {
      throw new IllegalStateException("Cannot print to view appendable!");
    }
  }
}
