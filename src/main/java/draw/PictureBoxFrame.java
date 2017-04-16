package draw;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by oahnus on 2017/4/17
 * 0:52.
 */
class PictureBoxFrame extends JFrame {
    JLabel pictureBox = new JLabel();

    void setting() {
        setSize(512, 512);
        setLocation(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add(pictureBox);

        setVisible(true);
    }

    void launch() {
        setting();

        Image image = drawImage();
        image = image.getScaledInstance(512,512,Image.SCALE_DEFAULT);
        pictureBox.setIcon(new ImageIcon(image));
    }

    BufferedImage drawImage() {
        return new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
    }
}
