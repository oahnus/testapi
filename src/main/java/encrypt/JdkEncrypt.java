package encrypt;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.stream.Stream;

/**
 * Created by oahnus on 2017/4/16
 * 21:51.
 */
public class JdkEncrypt {

    public static String getMD5(String clearText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = messageDigest.digest(clearText.getBytes("utf-8"));
        return toCipherText(bytes);
    }

    public static String getSHA1(String clearText) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] bytes = digest.digest(clearText.getBytes());
        return toCipherText(bytes);
    }

    public static String getDES(String clearText) throws InvalidKeyException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException, NoSuchPaddingException {
        DESKeySpec dks = new DESKeySpec(clearText.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        //key的长度不能够小于8位字节
        Key secretKey = keyFactory.generateSecret(dks);
//        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("qiyewan".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] bytes = cipher.doFinal(clearText.getBytes());
//        return toCipherText(bytes).toUpperCase();
        return toCipherText(bytes);
    }

    private static String toCipherText(byte[] bytes) {
        StringBuilder cipherText = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            int val = bytes[i] & 0xff;
            if(val<16){
                cipherText.append("0");
            }
            cipherText.append(Integer.toHexString(val));
        }
        return cipherText.toString();
    }

    public static void main(String... args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String s = getMD5("'{\"name\":\"test1\",\"card_no\":\"110222198912111234\"}'");
        System.out.println(s);

        String s1 = new String(Base64.getEncoder().encode(s.getBytes()));

        System.out.println(new String(Base64.getEncoder().encode(s1.getBytes())));
    }
}
