package controller;

import java.util.Arrays;
import java.util.Scanner;

import model.ImageCache;
import model.filters.BlurFilter;
import model.filters.GreyscaleFilter;
import model.filters.SepiaFilter;
import model.filters.SharpenFilter;
import model.filters.hw04.BrightnessFilter;
import model.filters.Filter;
import model.ImageModel;
import model.filters.hw04.HorizontalFlipFilter;
import model.filters.hw04.IntensityFilter;
import model.filters.hw04.LumaFilter;
import model.filters.hw04.RGBFilter;
import model.filters.hw04.ValueFilter;
import model.filters.hw04.VerticalFlipFilter;
import model.ImageUtil;
import model.Pixel;
import model.ImageModelRGB;
import view.ImageView;

import static model.ImageUtil.convertFileToImage;
import static model.ImageUtil.convertImageToFile;
import static model.ImageUtil.convertImageToPPM;

/**
 * Controller implementation to take input from the user and manage the model and view
 * accordingly.
 */
public class ImageControllerImpl implements ImageController {
  private final Readable in;
  private final ImageView view;
  private ImageCache images;
  private boolean exit = false; //state tracker for the program

  /**
   * Constructor for ImageControllerImpl.
   * @param r the Readable object
   */
  public ImageControllerImpl(ImageCache images, ImageView view, Readable r) {
    this.images = images;
    this.in = r;
    this.view = view;
  }

  /**
   * This method runs the program. Takes input from readable and outputs to view.
   * @throws IllegalStateException if appendable cannot be printed to
   * @throws IllegalArgumentException if there is no input from controller readable
   */
  @Override
  public void run() throws IllegalStateException, IllegalArgumentException {
    Scanner inScan = new Scanner(in);

    renderViewMessage("Welcome to the image program!\nEnter 'help' for a list of commands.\n");

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
        if (inputArray[1].split("\\.")[1].equals("ppm")) {
          Pixel[][] pixel = ImageUtil.readPPM(inputArray[1]);
          images.addImage(inputArray[2], new ImageModelRGB(pixel));
        } else {
          ImageModel image = convertFileToImage(inputArray[1]);
          images.addImage(inputArray[2], image);
        }

        renderViewMessage("Loaded " + inputArray[1] + " successfully!\n");
        break;

      case "save":
        String[] validExtensions = {"ppm", "png", "jpg", "jpeg"};
        if (images.hasImage(inputArray[2])) {
          if (!Arrays.asList(validExtensions).contains(inputArray[1].split("\\.")[1])) {
            renderViewMessage("Error: Invalid file extension!\n");
            break;
          }

          if (inputArray[1].split("\\.").length > 1
                  && inputArray[1].split("\\.")[1].equals("ppm")) {
            convertImageToPPM(images.getImage(inputArray[2]), inputArray[1]);
          } else {
            convertImageToFile(images.getImage(inputArray[2]), inputArray[1]);
          }
          renderViewMessage("Saved " + inputArray[1] + " successfully!\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "red-component":
        if (images.hasImage(inputArray[1])) {
          Filter redFilter = new RGBFilter(0);
          ImageModel filteredImage = redFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "green-component":
        if (images.hasImage(inputArray[1])) {
          Filter greenFilter = new RGBFilter(1);
          ImageModel filteredImage = greenFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "blue-component":
        if (images.hasImage(inputArray[1])) {
          Filter blueFilter = new RGBFilter(2);
          ImageModel filteredImage = blueFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "value-component":
        if (images.hasImage(inputArray[1])) {
          Filter valueFilter = new ValueFilter();
          ImageModel filteredImage = valueFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "luma-component":
        if (images.hasImage(inputArray[1])) {
          Filter lumaFilter = new LumaFilter();
          ImageModel filteredImage = lumaFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "intensity-component":
        if (images.hasImage(inputArray[1])) {
          Filter intensityFilter = new IntensityFilter();
          ImageModel filteredImage = intensityFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "horizontal-flip":
        if (images.hasImage(inputArray[1])) {
          Filter horizontalFlipFilter = new HorizontalFlipFilter();
          ImageModel filteredImage = horizontalFlipFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "vertical-flip":
        if (images.hasImage(inputArray[1])) {
          Filter verticalFlipFilter = new VerticalFlipFilter();
          ImageModel filteredImage = verticalFlipFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "brighten":
        if (images.hasImage(inputArray[2])) {
          Filter brightnessFilter = new BrightnessFilter(Integer.parseInt(inputArray[1]));
          ImageModel filteredImage = brightnessFilter.apply(images.getImage(inputArray[2]));
          images.addImage(inputArray[3], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[3] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "darken":
        if (images.hasImage(inputArray[2])) {
          Filter brightnessFilter = new BrightnessFilter(Integer.parseInt(inputArray[1]) * -1);
          ImageModel filteredImage = brightnessFilter.apply(images.getImage(inputArray[2]));
          images.addImage(inputArray[3], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[3] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "blur":
        if (images.hasImage(inputArray[1])) {
          Filter blurFilter = new BlurFilter();
          ImageModel filteredImage = blurFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "sharpen":
        if (images.hasImage(inputArray[1])) {
          Filter sharpenFilter = new SharpenFilter();
          ImageModel filteredImage = sharpenFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "sepia":
        if (images.hasImage(inputArray[1])) {
          Filter sepiaFilter = new SepiaFilter();
          ImageModel filteredImage = sepiaFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "greyscale":
        if (images.hasImage(inputArray[1])) {
          Filter greyscaleFilter = new GreyscaleFilter();
          ImageModel filteredImage = greyscaleFilter.apply(images.getImage(inputArray[1]));
          images.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "-file":
        if (inputArray.length > 1) {
          ImageUtil.runScript(inputArray[1]);
          renderViewMessage("Script run successfully!\n");
        } else {
          renderViewMessage("No script file specified!\n");
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
    renderViewMessage("List of commands: " + "\n" +
            "load <image file> <image name> - Loads an image from a file" + "\n" +
            "save <image file> <image name> - Saves an image to a file" + "\n" +
            "red-component <image name> <new image name> - Creates a new image with the " +
            "red component of the original image" + "\n" +
            "green-component <image name> <new image name> - Creates a new image with the " +
            "green component of the original image" + "\n" +
            "blue-component <image name> <new image name> - Creates a new image with the " +
            "blue component of the original image" + "\n" +
            "value-component <image name> <new image name> - Creates a new image with the " +
            "value component of the original image" + "\n" +
            "luma-component <image name> <new image name> - Creates a new image with the " +
            "luma component of the original image" + "\n" +
            "intensity-component <image name> <new image name> - Creates a new image with " +
            "the intensity component of the original image" + "\n" +
            "horizontal-flip <image name> <new image name> - Creates a new image with the " +
            "horizontal flip of the original image" + "\n" +
            "vertical-flip <image name> <new image name> - Creates a new image with the " +
            "vertical flip of the original image" + "\n" +
            "brighten <amount> <image name> <new image name> - Creates a new image with " +
            "the brightness increased by the amount" + "\n" +
            "darken <amount> <image name> <new image name> - Creates a new image with the " +
            "brightness decreased by the amount" + "\n" +
            "help - Prints this message" + "\n" +
            "q - Exits the program" + "\n");
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
