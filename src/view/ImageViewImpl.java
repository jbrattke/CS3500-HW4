package view;

import java.io.IOException;
import java.util.Formatter;

/**
 * Implementation of the image processing program. Contains an output destination and is capable
 * of sending a message out of that ouput.
 */
public class ImageViewImpl implements ImageView {
  private Appendable dest;

  /**
   * Constructor for ImageViewImpl.
   * @param dest the appendable object the view will use
   */
  public ImageViewImpl(Appendable dest) {
    if (dest == null) {
      throw new IllegalArgumentException("Invalid output destination.");
    } else {
      this.dest = dest;
    }
  }

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    Formatter f = new Formatter(this.dest.append(""));
    f.format(message);
  }
}
