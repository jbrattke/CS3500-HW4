package filters;

import model.ImageModelRGB;
import model.filters.RGBFilter;
import model.ImageUtil;

/**
 * This class is used for testing the blue greyscale filter.
 */
public class BlueFilterTest extends FilterModelTest {

  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new RGBFilter(2);
  }

  @Override
  protected int[] applyFilter(int[] pixel) {
    return new int[]{pixel[2], pixel[2], pixel[2]};
  }
}
