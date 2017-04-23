package java8.extra;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by oahnus on 2017/4/23
 * 13:25.
 */
public class ExtraFeatureTest {
    public static void main(String... args) throws IOException {
        try(Stream<String> lines = Files.lines(Paths.get("src/main/resources/java8/file.txt"))) {
            lines.filter(line -> line.contains("friday")).findFirst().ifPresent(System.out::println);
        }

        // Files.list 不包含子目录
        try(Stream<Path> entries = Files.list(Paths.get("src/main/java/nio"));
            DirectoryStream stream = Files.newDirectoryStream(Paths.get("src/main/java"))) {
            entries.forEach(entity -> System.out.println(entity.getFileName()));
        }

        // Files.walk 深度优先遍历，包含所有子目录
        try(Stream<Path> entries = Files.walk(Paths.get("src/main/java"))){
            entries.forEach(entity -> System.out.println(entity.getFileName()));
        }


    }
}
