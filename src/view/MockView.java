package view;

import java.io.IOException;
import java.util.Formatter;

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
