package view.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.ImageCache;
import view.ImageView;

public class LayerPanel extends JPanel implements ActionListener {
  private ImageCache cache;
  private final int maxLayerCount = 32;
  private JButton[] layerLabels = new JButton[maxLayerCount];
  private final JFrame view;

  public LayerPanel(ImageCache cache, JFrame view) {
    this.cache = cache;
    this.view = view;
    this.setBackground(Color.WHITE);

    setLayout(new GridLayout(maxLayerCount, 0));
    for (int i = 0; i < maxLayerCount; i++) {
      layerLabels[i] = new JButton();
      layerLabels[i].setActionCommand(i + "");
      layerLabels[i].addActionListener(this);
      add(layerLabels[i]);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    String layerNames[] = cache.getImageNames();
    for (int i = 0; i < Math.min(maxLayerCount, cache.getSize()); i++) {
      String layerName = layerNames[i];
      if (layerNames[i].equals(cache.getActiveImage())) {
        layerName += " ** ";
      }
      layerLabels[i].setText(layerName);
    }
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    int layerIndex = Integer.parseInt(arg0.getActionCommand());
    String[] layerNames = cache.getImageNames();

    if (layerIndex < layerNames.length) {
      cache.setActiveImage(layerNames[layerIndex]);
      view.repaint();
    }
  }
}
