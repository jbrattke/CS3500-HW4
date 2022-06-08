package view;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is used for testing the ImageViewImpl class.
 */
public class ImageViewImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void imageViewImplConstructorExceptionTest() {
    ImageViewImpl view = new ImageViewImpl(null);
  }

  @Test
  public void renderMessageTest() {
    Appendable dest = new StringBuilder();
    ImageViewImpl view = new ImageViewImpl(dest);
    try {
      view.renderMessage("Hello World!");
    } catch (Exception e) {
      assert(false);
    }
    assertEquals("Hello World!", dest.toString());
  }
}
