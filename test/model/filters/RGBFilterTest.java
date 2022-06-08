package model.filters;

import org.junit.Before;

import model.ImageModel;
import model.ImageModelRGB;
import model.Pixel;
import model.util.ImageUtil;

public class RGBFilterTest extends FilterModelTest {
  @Before
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new RGBFilter(0);
  }
}
