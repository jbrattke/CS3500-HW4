package model.filters;

/**
 * Represents the greyscale filter which applies a luma algorithm to each pixel's RGB values.
 */
public class GreyscaleFilter extends ColorTransformFilterModel {
  /**
   * Constructor for Greyscale filter. Creates a luma matrix and passes it to the superclass.
   */
  public GreyscaleFilter() {
    super(new double[][]{
            { 0.2126, 0.7152, 0.0722 },
            { 0.2126, 0.7152, 0.0722 },
            { 0.2126, 0.7152, 0.0722 }
    });
  }
}
