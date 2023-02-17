import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class ImageFileChooser {
    private JFrame frame;
    private JPanel imagePanel;
    private JLabel sizeLabel;

    public void createGUI() {
        // Create a frame and set its properties
        frame = new JFrame("Image File Chooser");
        frame.setSize(1500, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel for the image and add it to the frame
        imagePanel = new JPanel();
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);

        // Create a button to open the file chooser dialog
        JButton openButton = new JButton("Open Image");
        openButton.addActionListener(e -> {
            // Create a file chooser
            JFileChooser fileChooser = new JFileChooser();

            // Show the file chooser dialog and get the user's response
            int result = fileChooser.showOpenDialog(frame);

            // Check if the user clicked the "Open" button
            if (result == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();

                try {
                    // Load the selected file as an image
                    BufferedImage image = ImageIO.read(selectedFile);

                    // Create an image icon from the image and display it on the panel
                    ImageIcon icon = new ImageIcon(image);
                    JLabel imageLabel = new JLabel(icon);
                    imagePanel.removeAll();
                    imagePanel.add(imageLabel);
                    imagePanel.revalidate();
                    imagePanel.repaint();

                    // Get the image width and height and display them in a label
                    int width = image.getWidth();
                    int height = image.getHeight();
                    sizeLabel.setText("Width: " + width + ", Height: " + height);
                } catch (IOException ex) {
                    // Handle the exception
                    ex.printStackTrace();
                }
            }
        });

        // Add the button to the frame
        frame.getContentPane().add(openButton, BorderLayout.NORTH);

        // Create a label to display the image size and add it to the frame
        sizeLabel = new JLabel();
        frame.getContentPane().add(sizeLabel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }
}