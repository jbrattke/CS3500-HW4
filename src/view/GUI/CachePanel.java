package view.GUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import model.ImageCache;
import model.ImageModel;
import model.ImageModelRGB;
import model.ImageUtil;
import model.Pixel;

public class CachePanel extends JPanel {
  private ImageCache cache;

  public CachePanel(ImageCache cache) {
    this.cache = cache;
    this.setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // DRAWING IMAGE
    if (cache.getActiveImage() != null) {
      Image image = getImage(cache.getActiveImage());
      this.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
      g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
    }
  }

  private BufferedImage getImage(String name) {
    ImageModel image = cache.getImage(name);
    BufferedImage newImage =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        Pixel pixel = image.getPixel(x, y);
        int rgb = pixel.getRed();
        rgb = (rgb << 8) + pixel.getGreen();
        rgb = (rgb << 8) + pixel.getBlue();
        newImage.setRGB(x, y, rgb);
      }
    }
    return newImage;
  }
}
