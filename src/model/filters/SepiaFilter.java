package model.filters;

/**
 * Represents the sepia filter which applies an algorithm to each pixel's RGB values.
 */
public class SepiaFilter extends ColorTransformFilterModel {
  /**
   * Constructor for Sepia filter. Creates a sepia matrix and passes it to the superclass.
   */
  public SepiaFilter() {
    super(new double[][]{
            { 0.393, 0.769, 0.189 },
            { 0.349, 0.686, 0.168 },
            { 0.272, 0.534, 0.131 }
    });
  }
}
