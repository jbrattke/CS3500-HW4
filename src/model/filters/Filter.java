package model.filters;

import model.ImageModel;

/**
 * This class represents a filter.
 * It's only method is apply(), which will apply the filter to the given image.
 */
public interface Filter {
  /**
   * Applies the filter to the given image.
   * @param image The image to apply the filter to.
   * @return The filtered image.
   */
  public ImageModel apply(ImageModel image);
}
