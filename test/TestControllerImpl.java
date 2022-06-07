import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import controller.ImageControllerImpl;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the controller implementation
 */
public class TestControllerImpl {

  @Test
  public void testQuitRightAway() {

    String inputString = "exit";

    StringReader in = new StringReader(inputString);

    OutputStream out = new ByteArrayOutputStream();

    StringBuilder log = new StringBuilder();

    ImageView view = new ImageViewImpl(log);

    ImageControllerImpl controller = new ImageControllerImpl(in,view);

    controller.run();

    assertEquals("Welcome to the image program!\n", log.toString());
  }

}