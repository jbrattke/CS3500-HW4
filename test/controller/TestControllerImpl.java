package controller;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import controller.ImageControllerImpl;
import view.ImageView;
import view.ImageViewImpl;
import view.MockView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the controller implementation, uses a corrupted appendable and mock views and models
 * as well.
 */
public class TestControllerImpl {

  // tests error if the appendable is invalid.
  @Test (expected = IllegalStateException.class)
  public void testBadAppendable() {

    String inputString = "exit";

    StringReader in = new StringReader(inputString);

    // OutputStream out = new ByteArrayOutputStream();

    Appendable log = new CorruptAppendable();

    ImageView view = new ImageViewImpl(log);

    ImageControllerImpl controller = new ImageControllerImpl(in,view);

    controller.run();

    assertEquals("Welcome to the image program!\n", log.toString());
  }

  // tests quiting as first action.
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