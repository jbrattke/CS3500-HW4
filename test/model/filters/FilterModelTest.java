package model.filters;

import org.junit.Test;

import model.ImageModel;

public abstract class FilterModelTest {
  ImageModel image;
  Filter filter;

  @Test
  public void filterApplyTest() {
    filter.apply(image);

    for (int i = 0; i < image.getWidth(); i++) {

    }
  }
}
