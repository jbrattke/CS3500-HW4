package filters;

import model.ImageModelRGB;
import model.filters.RGBFilter;
import model.util.ImageUtil;

/**
 * This class is used for testing the green greyscale filter.
 */
public class GreenFilterTest extends FilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new RGBFilter(1);
  }

  @Override
  protected int[] applyFilter(int[] pixel) {
    return new int[]{pixel[1], pixel[1], pixel[1]};
  }
}
