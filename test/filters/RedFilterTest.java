package filters;


import model.ImageModelRGB;
import model.filters.RGBFilter;
import model.ImageUtil;

/**
 * This class is used for testing the red greyscale filter.
 */
public class RedFilterTest extends FilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new RGBFilter(0);
  }

  @Override
  protected int[] applyFilter(int[] pixel) {
    return new int[]{pixel[0], pixel[0], pixel[0]};
  }
}
