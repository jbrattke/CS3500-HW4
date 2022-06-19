package view.GUI;

import java.awt.*;

import javax.swing.*;

import model.ImageCache;
import model.ImageModel;

public class HistogramPanel extends JPanel {
  private ImageCache cache;
  private final int channel;

  public HistogramPanel(ImageCache cache, int channel) {
    this.cache = cache;
    this.channel = channel;
    this.setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (channel == 0) {
      g.setColor(Color.RED);
    } else if (channel == 1) {
      g.setColor(new Color(0, 150, 0));
    } else if (channel == 2) {
      g.setColor(Color.BLUE);
    } else {
      g.setColor(Color.BLACK);
    }

    ImageModel image = cache.getImage(cache.getActiveImage());
    int colorValCount = image.getPixel(0, 0).getMaxColorVal();

    int[] pixelCount = new int[colorValCount];
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        if (channel == 0) {
          pixelCount[image.getPixel(i, j).getRed()]++;
        } else if (channel == 1) {
          pixelCount[image.getPixel(i, j).getGreen()]++;
        } else if (channel == 2) {
          pixelCount[image.getPixel(i, j).getBlue()]++;
        } else {
          int average = (image.getPixel(i, j).getRed() + image.getPixel(i, j).getGreen()
                  + image.getPixel(i, j).getBlue()) / 3;
          pixelCount[average]++;
        }
      }
    }

    int max = 0;
    for (int i = 0; i < colorValCount; i++) {
      if (pixelCount[i] > max) {
        max = pixelCount[i];
      }
    }

    double factor = max / 384.0;

    setPreferredSize(new Dimension(colorValCount * 5 + 20, (int) (max / factor) + 20));


    for (int i = 0; i < colorValCount; i++) {
      g.drawRect(i * 5 + 10, 15, 3, (int) (pixelCount[i] / factor));
      g.fillRect(i * 5 + 10, 15, 3, (int) (pixelCount[i] / factor));
    }
  }
}
