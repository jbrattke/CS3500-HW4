package model.filters;

import model.ImageModelRGB;
import model.util.ImageUtil;

/**
 * This class is used for testing the luma greyscale filter.
 */
public class LumaFilterTest extends FilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new LumaFilter();
  }

  @Override
  protected int[] applyFilter(int[] pixel) {
    int lumaVal = (int) (0.2126 * pixel[0] + 0.7152 * pixel[1] + 0.0722 * pixel[2]);
    return new int[]{lumaVal, lumaVal, lumaVal };
  }
}
