package controller;

import java.util.Scanner;
import model.ImageCache;
import model.ImageCacheModel;
import view.ImageView;


/**
 * Controller implementation to take input from the user and manage the model and view
 * accordingly.
 */
public class ImageControllerImpl implements ImageController {
  private Readable in;
  private final ImageView view;
  private ControllerFeatures features;
  private boolean exit = false; //state tracker for the program

  /**
   * Constructor for ImageControllerImpl.
   * @param r the Readable object
   */
  public ImageControllerImpl(ImageCache images, ImageView view, Readable r) {
    this.in = r;
    this.view = view;
    this.features = new ControllerFeaturesImpl(images, this.view);
  }

  /**
   * This method runs the program. Takes input from readable and outputs to view.
   * @throws IllegalStateException if appendable cannot be printed to
   * @throws IllegalArgumentException if there is no input from controller readable
   */
  @Override
  public void run() throws IllegalStateException, IllegalArgumentException {
    Scanner inScan = new Scanner(in);

    features.renderViewMessage("Welcome to the image program!\nEnter 'help' for a list of commands.\n");

    while (!exit) {
      if (inScan.hasNextLine()) {
        String input = inScan.nextLine();

        if (input.split(" ")[0].equalsIgnoreCase("q")) {
          exit = true;
          break;
        }

        try { //will catch error's from input that processInput() does not handle
          features.processInput(input);
        } catch (Exception e) {
          features.renderViewMessage("Error: " + e.getMessage());
        }

      } else {
        throw new IllegalArgumentException("No input!");
      }
    }
  }
}
