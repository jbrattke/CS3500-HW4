package view.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import controller.ControllerFeatures;
import controller.ControllerFeaturesImpl;
import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageCache;
import model.ImageModel;
import model.ImageModelRGB;
import model.ImageUtil;
import model.filters.Filter;
import model.filters.GreyscaleFilter;
import view.ImageView;

public class ImageViewGUI extends JFrame implements ImageView, ActionListener {
  ImageCache cache;
  private JLabel messageLabel;
  private ControllerFeatures controller;

  public ImageViewGUI(ImageCache cache) {
    super("Image View");
    this.setPreferredSize(new Dimension(1024, 768));
    this.setLayout(new BorderLayout());
    this.cache = cache;
    this.controller = new ControllerFeaturesImpl(cache, this);

    //MAIN PANEL
    CachePanel cachePanel = new CachePanel(this.cache);
    JScrollPane scrollPane = new JScrollPane(cachePanel);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Scrollable Image Area"));
    scrollPane.setPreferredSize(new Dimension(824, 768));
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
    this.add(scrollPane, BorderLayout.CENTER);

    //HELP/TEXT PANEL
    this.messageLabel = new JLabel();
    JPanel helpPanel = new JPanel();
    helpPanel.setLayout(new GridLayout(0, 1));
    helpPanel.add(messageLabel);
    this.add(helpPanel, BorderLayout.PAGE_END);

    try {
      renderMessage("Welcome to the image viewer!");
    } catch (IOException e) {
      e.printStackTrace();
    }

    //LAYER PANEL
    JPanel layers = new LayerPanel(this.cache, this);
    this.add(layers, BorderLayout.EAST);

    //OPERATIONS PANEL
    JPanel commandsPanel = new JPanel();
    commandsPanel.setBorder(BorderFactory.createTitledBorder("Commands"));
    String[] commands = {"Load", "Save", "Script", "Histogram", "Sepia", "Greyscale", "Blur", "Sharpen",
            "Flip", "Brighten", "Darken", "Quit"};
    commandsPanel.setLayout(new GridLayout(1, commands.length));
    for (int i = 0; i < commands.length; i++) {
      JButton commandButton = new JButton(commands[i]);
      commandButton.setActionCommand(commands[i]);
      commandButton.addActionListener(this);
      commandsPanel.add(commandButton);
    }
    this.add(commandsPanel, BorderLayout.PAGE_START);

    pack();
    setVisible(true);
  }

  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated and therefore it must be redrawn.
   */
  void refresh() {
    this.repaint();
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.messageLabel.setText(message);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    switch (arg0.getActionCommand()) {
      case "Quit":
        System.exit(0);
        break;

      case "Load":
        JFileChooser jfc = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG,JPEG,BMP,PPM", "jpg", "png", "jpeg", "bmp", "ppm");
        jfc.setFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = jfc.getSelectedFile();

          try {
            controller.processInput("load " + selectedFile.getAbsolutePath() + " " + selectedFile.getName());
            cache.setActiveImage(selectedFile.getName());
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        break;

      case "Save":
        if (cache.getActiveImage() != null) {
          final JFileChooser fchooser = new JFileChooser(".");
          FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                  "PNG,JPEG,BMP,PPM", "jpg", "png", "jpeg", "bmp", "ppm");
          fchooser.setFileFilter(filter2);
          int retvalue = fchooser.showSaveDialog(this);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            controller.processInput("save " + f.getAbsolutePath() + " " + cache.getActiveImage());
          }
        } else {
          this.controller.renderViewMessage("No image loaded!");
        }
        break;

      case "Script":
        try {
          final JFileChooser fchooser = new JFileChooser(".");
          int retvalue = fchooser.showOpenDialog(this);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            controller.processInput("-file " + f.getAbsolutePath());
          }
        } catch (Exception e) {
          controller.renderViewMessage("Error loading script!");
        }

        break;

      case "Histogram":
        if (cache.getActiveImage() != null) {
          createHistogram();
        }
        break;

      case "Flip":
        Object[] options = {"Horizontal", "Vertical"};
        int n = JOptionPane.showOptionDialog(this,
                "Clarify flip direction",
                "Flip Command",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if (n == 0) {
          controller.processInput("horizontal-flip " + cache.getActiveImage()
                  + " " + cache.getActiveImage());
        } else if (n == 1) {
          controller.processInput("vertical-flip " + cache.getActiveImage()
                  + " " + cache.getActiveImage());
        }
        break;

      case "Blur":
        controller.processInput("blur " + cache.getActiveImage() + " " + cache.getActiveImage());
        break;

      case "Sharpen":
        controller.processInput("sharpen " + cache.getActiveImage() + " " + cache.getActiveImage());
        break;

      case "Brighten", "Darken":
        String name = JOptionPane.showInputDialog( "Enter increment:" );
        try {
          int increment = Integer.parseInt(name);
          String command = arg0.getActionCommand().toLowerCase();
          controller.processInput(command + " " + increment + " "
                  + cache.getActiveImage() + " " + cache.getActiveImage());
        } catch (Exception e) {
          this.controller.renderViewMessage("Invalid increment!");
        }
        break;

      case "Sepia", "Greyscale":
        controller.processInput(arg0.getActionCommand().toLowerCase() + " "
                + cache.getActiveImage() + " " + cache.getActiveImage());
        break;
    }
    refresh();
  }

  private void createHistogram() {
    //setting up new window for histogram view
    JFrame histogramFrame = new JFrame("Histogram View");
    histogramFrame.setPreferredSize(new Dimension(1024, 768));
    histogramFrame.setLayout(new BorderLayout());

    //creating and adding histograms to container panel
    JPanel container = new JPanel();
    container.setLayout(new GridLayout(4, 1));

    JPanel redHistogram = new HistogramPanel(cache, 0);
    redHistogram.setBorder(BorderFactory.createTitledBorder("Red"));

    JPanel greenHistogram = new HistogramPanel(cache, 1);
    greenHistogram.setBorder(BorderFactory.createTitledBorder("Green"));

    JPanel blueHistogram = new HistogramPanel(cache, 2);
    blueHistogram.setBorder(BorderFactory.createTitledBorder("Blue"));

    JPanel intensityHistogram = new HistogramPanel(cache, 3);
    intensityHistogram.setBorder(BorderFactory.createTitledBorder("Intensity"));

    container.add(redHistogram);
    container.add(greenHistogram);
    container.add(blueHistogram);
    container.add(intensityHistogram);

    //SCROLL PANEL FOR HISTOGRAMS
    JScrollPane scrollPanel = new JScrollPane(container);
    scrollPanel.setPreferredSize(new Dimension(1024, 768));

    //CLOSE BUTTON
    JButton closeButton = new JButton("Close Histogram View");
    closeButton.addActionListener(arg0 -> histogramFrame.dispose());

    //adding histogram elements to frame
    histogramFrame.add(scrollPanel, BorderLayout.CENTER);
    histogramFrame.add(closeButton, BorderLayout.PAGE_END);
    histogramFrame.pack();
    histogramFrame.setVisible(true);
  }
}
