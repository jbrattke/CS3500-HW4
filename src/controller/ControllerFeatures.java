package controller;

/**
 * This interface represents features of the controller.
 */
public interface ControllerFeatures {

  /**
   * This method will process input as a string and enact the appropriate action on the model.
   * @param input The input to be processed.
   */
  void processInput(String input);

  /**
   * Sends given text to the view where it displays the message to the output destination.
   * @param text message to be displayed
   * @throws IllegalStateException if rendermessage method throws an io exception
   */
  void renderViewMessage(String text) throws IllegalStateException;
}
