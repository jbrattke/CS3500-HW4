package filters;

import model.ImageModelRGB;
import model.ImageUtil;
import model.Pixel;
import model.PixelRGB;
import model.filters.GreyscaleFilter;

/**
 * This class is used for testing the color transformation greyscale filter.
 */
public class GreyscaleFilterTest extends ColorTransformFilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    filter = new GreyscaleFilter();
  }

  @Override
  protected Pixel applyMatrix(Pixel pixel) {
    double red = pixel.getRed() * 0.2126 + pixel.getGreen() * 0.7152 + pixel.getBlue() * 0.0722;
    double green = pixel.getRed() * 0.2126 + pixel.getGreen() * 0.7152 + pixel.getBlue() * 0.0722;
    double blue = pixel.getRed() * 0.2126 + pixel.getGreen() * 0.7152 + pixel.getBlue() * 0.0722;
    red = Math.min(Math.max(red, 0), 255);
    green = Math.min(Math.max(green, 0), 255);
    blue = Math.min(Math.max(blue, 0), 255);
    return new PixelRGB((int) red, (int) green, (int) blue, pixel.getMaxColorVal());
  }
}
