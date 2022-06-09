package filters;

import model.ImageModelRGB;
import model.filters.ValueFilter;
import model.util.ImageUtil;

/**
 * This class is used for testing the value greyscale filter.
 */
public class ValueFilterTest extends FilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new ValueFilter();
  }

  @Override
  protected int[] applyFilter(int[] pixel) {
    int max = Math.max(pixel[0], Math.max(pixel[1], pixel[2]));
    return new int[]{max, max, max};
  }
}
