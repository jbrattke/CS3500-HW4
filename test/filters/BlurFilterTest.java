package filters;

import model.ImageModelRGB;
import model.ImageUtil;
import model.filters.BlurFilter;

/**
 * This class is used for testing the kernel blur filter.
 */
public class BlurFilterTest extends KernelFilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    filter = new BlurFilter();
    kernel = new double[][]{
            { 0.0625, 0.125, 0.0625 },
            { 0.125, 0.25, 0.125 },
            { 0.0625, 0.125, 0.0625 }
    };
  }
}
