package view.GUI;

import java.awt.*;

import javax.swing.*;

import model.ImageCache;
import model.ImageModel;

public class HistogramPanel extends JPanel {
  private ImageCache cache;

  public HistogramPanel(ImageCache cache) {
    this.cache = cache;
    this.setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    ImageModel image = cache.getImage(cache.getActiveImage());
    int colorValCount = image.getPixel(0, 0).getMaxColorVal();

    int[] pixelCount = new int[colorValCount];
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        pixelCount[image.getPixel(i, j).getRed()]++;
//        pixelCount[image.getPixel(i, j).getGreen()]++;
//        pixelCount[image.getPixel(i, j).getBlue()]++;
      }
    }

    int max = 0;
    for (int i = 0; i < colorValCount; i++) {
      if (pixelCount[i] > max) {
        max = pixelCount[i];
      }
    }

    setPreferredSize(new Dimension(colorValCount * 5, max));

    for (int i = 0; i < colorValCount; i++) {
      g.drawRect(i * 5, 0, 5, pixelCount[i]);
    }
  }
}
