import java.io.InputStreamReader;

import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageCache;
import model.ImageCacheModel;
import view.ImageView;
import view.ImageViewImpl;

/**
 * A class used to run the image program in the command line.
 */
public class RunProgram {

  /**
   * The main method, runs the image program.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ImageView view = new ImageViewImpl(System.out);
    ImageCache cache = new ImageCacheModel();
    ImageController program = new ImageControllerImpl(cache, view,
            new InputStreamReader(System.in));
    program.run();
  }
}
