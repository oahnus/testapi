package draw;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.acos;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;

/**
 * Created by oahnus on 2017/4/17
 * 0:47.
 */
public class ImageA extends PictureBoxFrame {
    private int RD(int i, int j) {
        return (int) (Math.pow(cos(atan2(j - 512, i - 512) / 2),2)* 255);
    }

    private int BL(int i, int j) {
        return (int) (Math.pow(cos(atan2(j - 512, i - 512) / 2 - 2 * acos(-1) / 3),2) * 255);
    }

    private int GR(int i, int j) {
        return (int) (Math.pow(cos(atan2(j - 512, i - 512) / 2 + 2 * acos(-1) / 3),2) * 255);
    }

    BufferedImage drawImage() {
        BufferedImage image = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                Color color = new Color(RD(i, j)&255,GR(i, j)&255,BL(i, j)&255);
                image.setRGB(i, j, color.getRGB());
            }
        }
        return image;
    }

    public static void main(String[] args) {
        new ImageA().launch();
    }
}
