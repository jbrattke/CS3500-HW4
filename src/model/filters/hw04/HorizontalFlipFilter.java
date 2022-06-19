package model.filters.hw04;

/**
 * Used to flip an image horizontally. Needs the opposite pixel from the same row in order to flip.
 */
public class HorizontalFlipFilter extends FlipFilterModel {

  /**
   * Constructor for HorizontalFlipFilter. Assigns super class variable 'axis' to 0.
   */
  public HorizontalFlipFilter() {
    this.axis = 0;
  }
}
