package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This interface represents a cache of images, which can be used to cache images and retrieve them.
 */
public class ImageCacheModel implements ImageCache {
  private final Map<String, ImageModel> images = new HashMap<String, ImageModel>();
  private String activeImage;

  /**
   * This method adds an image with given name to the store,
   * will replace image if name already exists.
   *
   * @param name  The name of the image.
   * @param image The image to be added.
   */
  @Override
  public void addImage(String name, ImageModel image) {
    if (images.containsKey(name)) {
      images.replace(name, image);
    } else {
      images.put(name, image);
    }
  }

  /**
   * This method retrieves an image from the store.
   *
   * @param name The name of the image.
   * @return The image.
   */
  @Override
  public ImageModel getImage(String name) {
    return images.get(name);
  }

  /**
   * This method checks if the given name is in the cache.
   *
   * @param name The name of the image.
   * @return True if the cache contains an image with the given name, false otherwise.
   */
  @Override
  public boolean hasImage(String name) {
    return images.containsKey(name);
  }

  /**
   * Returns the size of the cache, aka, number of images in the cache.
   *
   * @return The size of the cache(number of images).
   */
  @Override
  public int getSize() {
    return images.size();
  }

  /**
   * This method returns all Image names in the cache.
   *
   * @return String array of all Image names.
   */
  @Override
  public String[] getImageNames() {
    return images.keySet().toArray(new String[images.size()]);
  }

  /**
   * This method returns 'active' image name in the cache, used for GUI.
   *
   * @return active image name
   */
  @Override
  public String getActiveImage() {
    return activeImage;
  }

  /**
   * This method sets 'active' image name in the cache, used for GUI.
   *
   * @param name active image name
   */
  @Override
  public void setActiveImage(String name) {
    if (images.containsKey(name)) {
      activeImage = name;
    }
  }
}
