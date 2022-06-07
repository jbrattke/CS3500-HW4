import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;

/**
 * A class used to run the image program in the command line.
 */
public class RunProgram {
  public static void main(String[] args) {
    ImageController program = new ImageControllerImpl(new InputStreamReader(System.in));
    program.run();
  }
}
