package model.filters;

import model.ImageModelRGB;
import model.util.ImageUtil;

/**
 * This class is used for testing the intensity greyscale filter.
 */
public class IntensityFilterTest extends FilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new IntensityFilter();
  }

  @Override
  protected int[] applyFilter(int[] pixel) {
    int avg = (pixel[0] + pixel[1] + pixel[2]) / 3;
    return new int[]{avg, avg, avg};
  }
}
