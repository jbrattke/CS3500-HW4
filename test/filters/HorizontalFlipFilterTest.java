package filters;

import model.ImageModelRGB;
import model.filters.HorizontalFlipFilter;
import model.util.ImageUtil;

/**
 * This class is used for testing the vertical flip filter.
 */
public class HorizontalFlipFilterTest extends FlipFilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new HorizontalFlipFilter();
  }

  @Override
  protected int[] findOpposite(int x, int y) {
    int mid = image.getWidth() / 2;
    int dif = x - mid;
    int xVal = image.getWidth() / 2;
    if (dif < 0) {
      xVal = mid + Math.abs(dif) - 1;
    } else if (dif > 0) {
      xVal = mid - dif;
    }
    return image.getPixel(xVal, y).getAllChannels();
  }
}
