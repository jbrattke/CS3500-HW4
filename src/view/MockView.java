package view;

import java.io.IOException;
import java.util.Formatter;

/**
 * Fake view class to test that the controller is sending the right information to the view.
 */
public class MockView implements ImageView {

  private Appendable out;

  public MockView(Appendable out) {
    this.out = out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    Formatter f = new Formatter(this.out.append(""));
    f.format("Message sent to the view is: " + message + "\n");
  }
}
