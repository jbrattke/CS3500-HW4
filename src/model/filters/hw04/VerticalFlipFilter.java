package model.filters.hw04;

/**
 * Used to flip an image vertically. Needs the opposite pixel from the same column
 * in order to flip.
 */
public class VerticalFlipFilter extends FlipFilterModel {
  /**
   * Constructor for VerticalFlipFilter. Assigns super class variable 'axis' to 1.
   */
  public VerticalFlipFilter() {
    this.axis = 1;
  }
}
