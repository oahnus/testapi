package lambda;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by oahnus on 2017/4/22
 * 14:11.
 */
public class StreamTest {
    public static void main(String... args) throws IOException {
        String contents = new String(Files.readAllBytes(
                        Paths.get("src/main/java/mongodb/MongoDBTest.java")), StandardCharsets.UTF_8);

        // 获取文中中所有字符数
        System.out.println(Stream.of(contents).mapToInt(String::length).sum());
        System.out.println(contents.length());

        // 从文本中获取单词
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        // 获取单词长度大于5的单词个数
        long count = words.stream().filter(word -> word.length() > 12).count();

        // 无限流
        Stream<String> echos = Stream.generate(() -> "echo");
        Stream<Double> randoms = Stream.generate(Math::random);
        // 产生100个随机数
        Stream<Double> oneHundredRandoms = Stream.generate(Math::random).limit(100);
        // 0,1,2,3,4,5 ...
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));

        // 将小写word转换为大写
        Stream<String> upperCaseWords = Stream.of(contents).map(String::toUpperCase);

        // 提取子流
        Stream.of(contents).limit(100);
        Stream.of(contents).skip(100);

        // 去重 1,2,3,4
        Stream<String> uniqueWords = Stream.of("1", "2", "3", "4", "4").distinct();

        Optional<String> largest = Stream.of(contents.split("[\\P{L}]+")).max(String::compareToIgnoreCase);
        largest.ifPresent(s -> System.out.println("largest:" + s));

        boolean m = Stream.of(contents.split("[\\P{L}]+")).parallel()
                .anyMatch(word -> word.startsWith("i"));
        System.out.println(m);

        System.out.println(Optional.empty());
        System.out.println(Optional.of(2).get());

        // 聚合操作
        int sum = Stream.of(1,2,3,4,5).reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println(Stream.of("h", "e", "l", "l", "o").collect(Collectors.joining()));
        System.out.println(Stream.of("h", "e", "l", "l", "o").collect(Collectors.joining(",")));

        IntSummaryStatistics summary = Stream.of(contents.split("[\\P{L}]+")).collect(Collectors.summarizingInt(String::length));
        System.out.println("avg:" + summary.getAverage());
        System.out.println("max:" + summary.getMax());
        System.out.println("sum:" + summary.getSum());
    }
}
