package encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by oahnus on 2017/4/15
 * 21:30.
 */
public class EncryptAndDecrypt {

    private static List<File> fileList = new ArrayList<>();

    private static List<File> scan(File dir) {
//        Stream.of(dir.listFiles()).forEach(file -> {
//            if (file.isDirectory()) {
//                scan(file);
//            } else {
//                fileList.add(file);
//            }
//        });
        /**
         * java8
         */
        if (!dir.isDirectory())
            return Collections.singletonList(dir);

        Arrays.asList(dir.listFiles())
                .parallelStream()
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toList())
                .forEach(file -> fileList.add(file));

        Arrays.asList(dir.listFiles())
                .parallelStream()
                .filter(File::isDirectory)
                .forEach(EncryptAndDecrypt::scan);
        return fileList;
    }


    public static void decrypt(String path) {
        Path filePath = Paths.get(path);
        fileList = new ArrayList<>();

        List<File> files = scan(filePath.toFile());
        files.forEach(file -> {
            try {

                byte[] bytes = Files.readAllBytes(file.toPath());
                for (int i=0;i<bytes.length;i++) {
                    bytes[i] = (byte) (bytes[i]^0xED);
                }

                String currentPath = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
                String filename = file.getName().substring(0, file.getName().lastIndexOf("."));

                System.out.println("Decrypt File " + file.getName() + "===>" + filename);

                try (FileOutputStream out = new FileOutputStream(new File(currentPath, filename))) {
                    out.write(bytes);
                }
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void encrypt(String path) {
        Path filePath = Paths.get(path);
        fileList = new ArrayList<>();

        List<File> files = scan(filePath.toFile());
        files.forEach(file -> {
            try {
                byte[] bytes = Files.readAllBytes(file.toPath());
                for (int i=0;i<bytes.length;i++) {
                    bytes[i] = (byte) (bytes[i]^0xED);
                }

                String currentPath = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
                String filename = file.getName() + ".enc";

                System.out.println("Encrypt File " + file.getName() + "===>" + filename);

                try (FileOutputStream out = new FileOutputStream(new File(currentPath, filename))) {
                    out.write(bytes);
                }
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String... args) {
        encrypt("src/main/resources");
        decrypt("src/main/resources");
    }
}
