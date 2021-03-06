package qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Created by oahnus on 2017/4/17
 * 1:03.
 */
public class QrCode {
    public static void main(String[] args){
        createQRCodeToPath("http://blog.oahnus.top", "src/main/resources/qrcode/code.png");
//        readQRCodeFromImage();
    }

    public static void readQRCodeFromImage() throws NotFoundException, IOException {
        MultiFormatReader reader = new MultiFormatReader();

        BufferedImage image = ImageIO.read(new File("src/main/resources/qrcode/code.png"));

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        //指定参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");

        Result result = reader.decode(binaryBitmap,hints);

System.out.println(result);
System.out.println("编码格式:"+result.getBarcodeFormat());
    }

    public static void createQRCodeToPath(String content, String targetPath) {
        int width = 400;
        int height = 400;
        String format = "png";

        //定义二维码参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        //容错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //边框
        hints.put(EncodeHintType.MARGIN,2);

        //生成二维码
        int oncolor = 0xFF000000;
        int offcolor = 0xFFFFFFFF;
        MatrixToImageConfig config = new MatrixToImageConfig(oncolor,offcolor);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);
            Path path = new File(targetPath).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
