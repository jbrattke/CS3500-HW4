package model.filters;

/**
 * Represents the blur filter. Uses gaussian blur to blur the image.
 */
public class BlurFilter extends KernelFilterModel {
  /**
   * Blur filter constructor. Creates a gaussian kernel and passes it to the superclass.
   */
  public BlurFilter() {
    super(new double[][]{
            { 0.0625, 0.125, 0.0625 },
            { 0.125, 0.25, 0.125 },
            { 0.0625, 0.125, 0.0625 }
    });
  }
}
