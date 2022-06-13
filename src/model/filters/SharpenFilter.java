package model.filters;

/**
 * Represents the sharpen filter. Uses 5x5 kernel to sharpen image.
 */
public class SharpenFilter extends KernelFilterModel {
  public SharpenFilter() {
    super(new double[][]{
            { -0.125, -0.125, -0.125, -0.125, -0.125},
            { -0.125, 0.25, 0.25, 0.25, -0.125},
            { -0.125, 0.25, 1, 0.25, -0.125},
            { -0.125, 0.25, 0.25, 0.25, -0.125},
            { -0.125, -0.125, -0.125, -0.125, -0.125},
    });
  }
}
