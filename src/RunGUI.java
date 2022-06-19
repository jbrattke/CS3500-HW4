import model.ImageCacheModel;
import view.GUI.ImageViewGUI;
import view.ImageView;

/**
 * This class is used to run the image program using a GUI.
 */
public class RunGUI {
  /**
   * A sample main method.
   *
   * @param args the program arguments
   */
  public static void main(String[] args) {
    ImageCacheModel model = new ImageCacheModel();
    ImageView view = new ImageViewGUI(model);
  }
}
