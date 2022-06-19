package filters;

import model.ImageModelRGB;
import model.ImageUtil;
import model.Pixel;
import model.PixelRGB;
import model.filters.SepiaFilter;

/**
 * This class is used for testing the sepia filter.
 */
public class SepiaFilterTest extends ColorTransformFilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    filter = new SepiaFilter();
  }

  @Override
  protected Pixel applyMatrix(Pixel pixel) {
    double red = pixel.getRed() * 0.393 + pixel.getGreen() * 0.769 + pixel.getBlue() * 0.189;
    double green = pixel.getRed() * 0.349 + pixel.getGreen() * 0.686 + pixel.getBlue() * 0.168;
    double blue = pixel.getRed() * 0.272 + pixel.getGreen() * 0.534 + pixel.getBlue() * 0.131;
    red = Math.min(Math.max(red, 0), 255);
    green = Math.min(Math.max(green, 0), 255);
    blue = Math.min(Math.max(blue, 0), 255);
    return new PixelRGB((int) red, (int) green, (int) blue, pixel.getMaxColorVal());
  }
}
