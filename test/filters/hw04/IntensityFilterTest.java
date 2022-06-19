package filters.hw04;

import model.ImageModelRGB;
import model.filters.hw04.IntensityFilter;
import model.ImageUtil;

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
