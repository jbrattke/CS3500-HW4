package model;

/**
 * This interface represents a cache of images, which can be used to cache images and retrieve them.
 */
public interface ImageCache {
  /**
   * This method adds an image with given name to the cache,
   * will replace image if name already exists.
   *
   * @param name  The name of the image.
   * @param image The image to be added.
   */
  void addImage(String name, ImageModel image);

  /**
   * This method retrieves an image from the cache.
   *
   * @param name The name of the image.
   * @return The image.
   */
  ImageModel getImage(String name);

  /**
   * This method checks if the given name is in the cache.
   *
   * @param name The name of the image.
   * @return True if the cache contains an image with the given name, false otherwise.
   */
  boolean hasImage(String name);

  /**
   * Returns the size of the cache, aka, number of images in the cache.
   *
   * @return The size of the cache(number of images).
   */
  int getSize();
}
