package filters.hw04;

import model.ImageModelRGB;
import model.filters.hw04.VerticalFlipFilter;
import model.ImageUtil;

/**
 * This class is used for testing the vertical flip filter.
 */
public class VerticalFlipFilterTest extends FlipFilterModelTest {
  @Override
  public void setUp() {
    image = new ImageModelRGB(ImageUtil.readPPM("img/Koala.ppm"));
    filter = new VerticalFlipFilter();
  }

  @Override
  protected int[] findOpposite(int x, int y) {
    int mid = image.getHeight() / 2;
    int dif = y - mid;
    int yVal = image.getHeight() / 2;
    if (dif < 0) {
      yVal = mid + Math.abs(dif) - 1;
    } else if (dif > 0) {
      yVal = mid - dif;
    }
    return image.getPixel(x, yVal).getAllChannels();
  }
}
