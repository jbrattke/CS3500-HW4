package view;

import java.io.IOException;
import java.util.Formatter;

/**
 * Implementation of the image processing program. Contains an output destination and is capable
 * of sending a message out of that ouput.
 */
public class ImageViewImpl implements ImageView {

  private Appendable dest;

  public ImageViewImpl(Appendable dest) {
    if (dest == null) {
      throw new IllegalArgumentException("Invalid output destination.");
    } else {
      this.dest = dest;
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    Formatter f = new Formatter(this.dest.append(""));
    f.format(message);
  }
}
