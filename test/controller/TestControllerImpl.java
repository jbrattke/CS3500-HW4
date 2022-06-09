package controller;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import controller.ImageControllerImpl;
import view.ImageView;
import view.ImageViewImpl;
import view.MockView;

import static model.util.ImageUtil.comparePPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the controller implementation, uses a corrupted appendable and mock views and models
 * as well.
 */
public class TestControllerImpl {

  // tests error if the appendable is invalid.
  @Test (expected = IllegalStateException.class)
  public void testBadAppendable() {
    Appendable log = new CorruptAppendable();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("exit");
    ImageControllerImpl controller = new ImageControllerImpl(in,view);
    controller.run();
  }

  @Test
  public void testQuitRightAway() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("exit");
    ImageControllerImpl controller = new ImageControllerImpl(in,view);
    controller.run();

    assertEquals("Welcome to the image program!\n", log.toString());
  }

  // tests quiting as first action.
  @Test
  public void loadAndSaveTest() {
    StringBuilder log = new StringBuilder();
    ImageView view = new ImageViewImpl(log);

    StringReader in = new StringReader("load img/koala.ppm koala\n"
        + "save test.ppm koala\n"
        + "exit");
    ImageControllerImpl controller = new ImageControllerImpl(in,view);
    controller.run();

    assertTrue(comparePPM("img/koala.ppm", "test.ppm"));

    //deleting test files
    File file = new File("test.ppm");
    file.delete();
  }

  // tests the messages sent to the view with a mock
  @Test
  public void testToView() {

    String inputString = "help";

    StringReader in = new StringReader(inputString);

    OutputStream out = new ByteArrayOutputStream();

    StringBuilder log = new StringBuilder();

    ImageView view = new MockView(log);

    ImageControllerImpl controller = new ImageControllerImpl(in,view);

    controller.run();

    assertEquals("Message sent to the view is: Welcome to the image program!\n" +
            "\n" +
            "Message sent to the view is: Help!\n" +
            "\n", log.toString());
  }

  /**
   * A bad Appendable, used for testing.
   */
  private static class CorruptAppendable implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException();
    }
  }
}