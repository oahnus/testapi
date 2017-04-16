package draw;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.pow;

/**
 * Created by oahnus on 2017/4/17
 * 1:00.
 */
public class ImageB extends PictureBoxFrame {
    private int RD(int i, int j) {
        double a=0,b=0,c,d,n=0;
        while((c=a*a)+(d=b*b)<4&&n++<880)
        {b=2*a*b+j*8e-9-.645411;a=c-d+i*8e-9+.356888;}
        return (int) (255*pow((n-80)/800,3.));
    }

    private int BL(int i, int j) {
        double a=0,b=0,c,d,n=0;
        while((c=a*a)+(d=b*b)<4&&n++<880)
        {b=2*a*b+j*8e-9-.645411;a=c-d+i*8e-9+.356888;}
        return (int) (255*pow((n-80)/800,.7));
    }

    private int GR(int i, int j) {
        double a=0,b=0,c,d,n=0;
        while((c=a*a)+(d=b*b)<4&&n++<880)
        {b=2*a*b+j*8e-9-.645411;a=c-d+i*8e-9+.356888;}
        return (int) (255*pow((n-80)/800,.5));
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

    public static void main(String... args) {
        new ImageB().launch();
    }
}
