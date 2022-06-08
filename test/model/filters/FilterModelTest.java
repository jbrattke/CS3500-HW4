package model.filters;

import org.junit.Before;
import org.junit.Test;

import model.ImageModel;
import model.Pixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This abstract class is used for testing all filters that extend FilterModel.
 */
public abstract class FilterModelTest {
  ImageModel image;
  Filter filter;

  @Before
  public abstract void setUp();

  @Test
  public void filterApplyTest() {
    ImageModel filteredImage = filter.apply(image);

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        assertArrayEquals(applyFilter(image.getPixel(i,j).getAllChannels()),
                filteredImage.getPixel(i,j).getAllChannels());
      }
    }
  }

  protected abstract int[] applyFilter(int[] pixel);
}
