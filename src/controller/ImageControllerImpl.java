package controller;

import java.util.Scanner;

import model.ImageModel;
import model.util.ImageUtil;
import model.Pixel;
import model.ImageModelRGB;

import static model.util.ConvertImage.convertImageToFile;

public class ImageControllerImpl implements ImageController {
  private Readable in;

  /**
   * Constructor for ImageControllerImpl.
   * @param r the Readable object
   */
  public ImageControllerImpl(Readable r) {
    in = r;
  }

  /**
   * This method runs the program. ??
   */
  @Override
  public void run() {
    Scanner inScan = new Scanner(in);

    if (inScan.hasNextLine()) {
      String line = inScan.nextLine();
      Pixel[][] pixel = ImageUtil.readPPM(line);
      ImageModel image = new ImageModelRGB(pixel);

      String filename = inScan.nextLine();
      convertImageToFile(image, filename);
    }
  }
}
