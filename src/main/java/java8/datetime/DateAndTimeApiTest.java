package java8.datetime;

import java.math.BigInteger;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

/**
 * Created by oahnus on 2017/4/23
 * 11:35.
 */
public class DateAndTimeApiTest {
    public static void main(String... args) throws InterruptedException {
        Instant start = Instant.now();
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE)).limit(100000000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("耗时 ==> " + duration.toDays() + "天");
        System.out.println("耗时 ==> " + duration.toHours() + "小时");
        System.out.println("耗时 ==> " + duration.toMinutes() + "分钟");
        System.out.println("耗时 ==> " + duration.getSeconds() + "秒");
        System.out.println("耗时 ==> " + duration.toMillis() + "毫秒");

        Instant now = Instant.now();
        System.out.println(now);
        Instant plus = now.plusSeconds(12);
        System.out.println(plus);
        Instant minus = now.minusSeconds(10);
        System.out.println(minus);

        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate birthday = LocalDate.of(1995, 5, 19);
        System.out.println(birthday);
        // 返回两个时间之间的间隔
        System.out.println(LocalDate.of(1994,4,17).until(birthday));

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        String formatDate = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zonedDateTime);
        System.out.println(formatDate);
        formatDate = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ZonedDateTime.of(1995, 5, 19, 12, 25, 58, 0, ZoneId.of("Asia/Shanghai")));
        System.out.println(formatDate);
    }
}
