package filters;

import model.ImageModelRGB;
import model.ImageUtil;
import model.filters.SharpenFilter;

/**
 * This class is used for testing the kernel sharpen filter.
 */
public class SharpenFilterTest extends KernelFilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("res/capybara.ppm"));
    filter = new SharpenFilter();
    kernel = new double[][]{
            { -0.125, -0.125, -0.125, -0.125, -0.125},
            { -0.125, 0.25, 0.25, 0.25, -0.125},
            { -0.125, 0.25, 1, 0.25, -0.125},
            { -0.125, 0.25, 0.25, 0.25, -0.125},
            { -0.125, -0.125, -0.125, -0.125, -0.125},
    };
  }
}
