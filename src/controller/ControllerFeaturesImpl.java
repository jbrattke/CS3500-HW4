package controller;

import java.util.Arrays;

import model.ImageCache;
import model.ImageCacheModel;
import model.ImageModel;
import model.ImageModelRGB;
import model.ImageUtil;
import model.Pixel;
import model.filters.BlurFilter;
import model.filters.Filter;
import model.filters.GreyscaleFilter;
import model.filters.SepiaFilter;
import model.filters.SharpenFilter;
import model.filters.hw04.BrightnessFilter;
import model.filters.hw04.HorizontalFlipFilter;
import model.filters.hw04.IntensityFilter;
import model.filters.hw04.LumaFilter;
import model.filters.hw04.RGBFilter;
import model.filters.hw04.ValueFilter;
import model.filters.hw04.VerticalFlipFilter;
import view.ImageView;

import static model.ImageUtil.convertFileToImage;
import static model.ImageUtil.convertImageToFile;
import static model.ImageUtil.convertImageToPPM;

/**
 * This class represents features of the controller. Implements ControllerFeatures interface.
 */
public class ControllerFeaturesImpl implements ControllerFeatures {
  private ImageCache cache;
  private ImageView view;

  /**
   * Constructor for ControllerFeaturesImpl.
   * @param view The view to be used.
   */
  public ControllerFeaturesImpl(ImageCache cache, ImageView view) {
    this.cache = cache;
    this.view = view;
  }

  /**
   * This method will process input as a string and enact the appropriate action on the model.
   *
   * @param input The input to be processed.
   */
  @Override
  public void processInput(String input) {
    String[] inputArray = input.split(" ");
    if (inputArray.length < 1) {
      return;
    }

    switch (inputArray[0]) {
      case "load":
        if (inputArray[1].split("\\.")[1].equals("ppm")) {
          Pixel[][] pixel = ImageUtil.readPPM(inputArray[1]);
          cache.addImage(inputArray[2], new ImageModelRGB(pixel));
        } else {
          ImageModel image = convertFileToImage(inputArray[1]);
          cache.addImage(inputArray[2], image);
        }

        renderViewMessage("Loaded " + inputArray[1] + " successfully!\n");
        break;

      case "save":
        String[] validExtensions = {"ppm", "png", "jpg", "jpeg", "bmp"};
        if (cache.hasImage(inputArray[2])) {
          if (!Arrays.asList(validExtensions).contains(inputArray[1].split("\\.")[1])) {
            renderViewMessage("Error: Invalid file extension!\n");
            break;
          }

          if (inputArray[1].split("\\.").length > 1
                  && inputArray[1].split("\\.")[1].equals("ppm")) {
            convertImageToPPM(cache.getImage(inputArray[2]), inputArray[1]);
          } else {
            convertImageToFile(cache.getImage(inputArray[2]), inputArray[1]);
          }
          renderViewMessage("Saved " + inputArray[1] + " successfully!\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "red-component":
        if (cache.hasImage(inputArray[1])) {
          Filter redFilter = new RGBFilter(0);
          ImageModel filteredImage = redFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "green-component":
        if (cache.hasImage(inputArray[1])) {
          Filter greenFilter = new RGBFilter(1);
          ImageModel filteredImage = greenFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "blue-component":
        if (cache.hasImage(inputArray[1])) {
          Filter blueFilter = new RGBFilter(2);
          ImageModel filteredImage = blueFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "value-component":
        if (cache.hasImage(inputArray[1])) {
          Filter valueFilter = new ValueFilter();
          ImageModel filteredImage = valueFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "luma-component":
        if (cache.hasImage(inputArray[1])) {
          Filter lumaFilter = new LumaFilter();
          ImageModel filteredImage = lumaFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "intensity-component":
        if (cache.hasImage(inputArray[1])) {
          Filter intensityFilter = new IntensityFilter();
          ImageModel filteredImage = intensityFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "horizontal-flip":
        if (cache.hasImage(inputArray[1])) {
          Filter horizontalFlipFilter = new HorizontalFlipFilter();
          ImageModel filteredImage = horizontalFlipFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "vertical-flip":
        if (cache.hasImage(inputArray[1])) {
          Filter verticalFlipFilter = new VerticalFlipFilter();
          ImageModel filteredImage = verticalFlipFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "brighten":
        if (cache.hasImage(inputArray[2])) {
          Filter brightnessFilter = new BrightnessFilter(Integer.parseInt(inputArray[1]));
          ImageModel filteredImage = brightnessFilter.apply(cache.getImage(inputArray[2]));
          cache.addImage(inputArray[3], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[3] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "darken":
        if (cache.hasImage(inputArray[2])) {
          Filter brightnessFilter = new BrightnessFilter(Integer.parseInt(inputArray[1]) * -1);
          ImageModel filteredImage = brightnessFilter.apply(cache.getImage(inputArray[2]));
          cache.addImage(inputArray[3], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[3] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "blur":
        if (cache.hasImage(inputArray[1])) {
          Filter blurFilter = new BlurFilter();
          ImageModel filteredImage = blurFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "sharpen":
        if (cache.hasImage(inputArray[1])) {
          Filter sharpenFilter = new SharpenFilter();
          ImageModel filteredImage = sharpenFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "sepia":
        if (cache.hasImage(inputArray[1])) {
          Filter sepiaFilter = new SepiaFilter();
          ImageModel filteredImage = sepiaFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "greyscale":
        if (cache.hasImage(inputArray[1])) {
          Filter greyscaleFilter = new GreyscaleFilter();
          ImageModel filteredImage = greyscaleFilter.apply(cache.getImage(inputArray[1]));
          cache.addImage(inputArray[2], filteredImage);
          renderViewMessage("Filter applied to: " + inputArray[2] + "\n");
        } else {
          renderViewMessage("Image with name does not exist!\n");
        }
        break;

      case "-file":
        if (inputArray.length > 1) {
          String script = ImageUtil.findScript(inputArray[1]);
          String[] scriptArray = script.split("\n");
          for (String line : scriptArray) {
            if (line.length() > 0) {
              String[] lineArray = line.split(" ");
              if (lineArray.length > 0) {
                processInput(line);
              }
            }
          }
          renderViewMessage("Script run!\n");
        } else {
          renderViewMessage("No script file specified!\n");
        }
        break;

      case "help":
        printHelpMessage();
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
            "-file <file path> - Runs a script file" + "\n" +
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
            "greyscale <image name> <new image name> - Creates a new image with the " +
            "greyscaled luma component of the original image" + "\n" +
            "sepia <image name> <new image name> - Creates a new image with the " +
            "sepia effect applied from the original" + "\n" +
            "blur <image name> <new image name> - Creates a new image that is " +
            "blurred from the original" + "\n" +
            "sharpen <image name> <new image name> - Creates a new image that is " +
            "sharpened from the original" + "\n" +
            "help - Prints this message" + "\n" +
            "q - Exits the program" + "\n");
  }

  /**
   * Sends given text to the view where it displays the message to the output destination.
   * @param text message to be displayed
   * @throws IllegalStateException if rendermessage method throws an io exception
   */
  @Override
  public void renderViewMessage(String text) throws IllegalStateException {
    try {
      view.renderMessage(text);
    } catch (Exception e) {
      throw new IllegalStateException("Cannot print to view appendable!");
    }
  }
}
