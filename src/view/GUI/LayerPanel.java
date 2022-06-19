package view.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.ImageCache;

public class LayerPanel extends JPanel implements ActionListener {
  private ImageCache cache;
  private final Map<String, JButton> layersMap = new HashMap<String, JButton>();
  private final JFrame view;
  private JPanel layerPanel;

  public LayerPanel(ImageCache cache, JFrame view) {
    this.cache = cache;
    this.view = view;
    this.setBackground(Color.WHITE);

    setLayout(new BorderLayout());

    JButton duplicate = new JButton("Duplicate");
    duplicate.setActionCommand("duplicate");
    duplicate.addActionListener(this);
    add(duplicate, BorderLayout.PAGE_END);

    layerPanel = new JPanel();
    layerPanel.setLayout(new GridLayout(512, 1));
    JScrollPane scrollPanel = new JScrollPane(layerPanel);
    scrollPanel.setPreferredSize(new Dimension(200, 700));
    scrollPanel.setBorder(BorderFactory.createTitledBorder("Image Layers"));
    add(scrollPanel, BorderLayout.CENTER);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    String layerNames[] = cache.getImageNames();

    for (int i = 0; i < cache.getSize(); i++) {
      String layerName = layerNames[i];

      if (!layersMap.containsKey(layerName)) {
        JButton button = new JButton();
        button.setActionCommand(layerName);
        button.addActionListener(this);
        layersMap.put(layerName, button);
        layerPanel.add(layersMap.get(layerName));
      }

      if (layerNames[i].equals(cache.getActiveImage())) {
        layerName += " ** ";
      }

      layersMap.get(layerNames[i]).setText(layerName);
    }
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    String command = arg0.getActionCommand();
    if (command.equals("duplicate") && cache.getActiveImage() != null) {
      String name = JOptionPane.showInputDialog( "New Layer Name:" );
      if (name == null || layersMap.containsKey(name)) {
        JOptionPane.showMessageDialog(this, "Layer name invalid");
      } else if (name != null) {
        cache.addImage(name, cache.getImage(cache.getActiveImage()));
        view.repaint();
      }
    } else {
      cache.setActiveImage(arg0.getActionCommand());
      view.repaint();
    }
  }
}
