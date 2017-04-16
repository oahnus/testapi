package draw;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by oahnus on 2017/4/17
 * 0:50.
 */
public class MandelbrotImage extends PictureBoxFrame {

    private int RD(int i, int j) {
        float x = 0,y = 0;
        int k;
        for(k=0;k++<256;){
            float a = (float) (x*x - y* y+(i-768.0)/512);
            y = (float) (2*x*y+(j-512.0)/512);
            x = a;
            if(x*x+y*y>4){
                break;
            }
        }
        return (int) (Math.log(k)*47);
    }

    private int BL(int i, int j) {
        float x = 0,y = 0;
        int k;
        for(k=0;k++<256;){
            float a = (float) (x*x - y* y+(i-768.0)/512);
            y = (float) (2*x*y+(j-512.0)/512);
            x = a;
            if(x*x+y*y>4){
                break;
            }
        }
        return (int) (Math.log(k)*47);
    }

    private int GR(int i, int j) {
        float x = 0,y = 0;
        int k;
        for(k=0;k++<256;){
            float a = (float) (x*x - y* y+(i-768.0)/512);
            y = (float) (2*x*y+(j-512.0)/512);
            x = a;
            if(x*x+y*y>4){
                break;
            }
        }
        return (int) (128 - Math.log(k)*23);
    }

    BufferedImage drawImage() {
        BufferedImage image = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                Color color = new Color(RD(i,j)&255,GR(i,j)&255,BL(i,j)&255);
                image.setRGB(i,j,color.getRGB());
            }
        }
        return image;
    }

    public static void main(String[] args) {
        new MandelbrotImage().launch();
    }
}
