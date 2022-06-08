package model.filters;

import model.ImageModelRGB;
import model.util.ImageUtil;

public class BrightnessFilterTest extends FilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new BrightnessFilter(10);
  }

  @Override
  protected int[] applyFilter(int[] pixel) {
    int red = Math.min(255, pixel[0] + 10);
    int green = Math.min(255, pixel[1] + 10);
    int blue = Math.min(255, pixel[2] + 10);
    return new int[]{red, green, blue};
  }
}