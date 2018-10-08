package top.dzygod.jdk8.practice.chaptertwelve;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/5 17:03
 * @Description: 第十二章 新的日期和时间API
 *  为什么在Java 8中需要引入新的日期和时间库
 *  同时为人和机器表示日期和时间
 *  定义时间的度量
 *  操纵、格式化以及解析日期
 *  处理不同的时区和历法
 */
public class Index {

    /**
     * java.time 包中提供了很多新的类可以帮你解决问题
     * LocalDate 、 LocalTime 、 Instant 、 Duration 以及 Period
     */
    public static void main(String[] args) {
//        localDateAndLocalTime();
//        instantDurationAndPeriod();
//        fomartDate();
//        zones();


    }


    /**
     * 处理不同的时区和历法
     * 在 ZoneRules 这个类中包含了40个这样的实例
     * 可以简单地通过调用 ZoneId 的 getRules() 得到指定时区的规则
     * 地区ID都为“{区域}/{城市}”的格式，
     * 这些地区集合的设定都由英特网编号分配机构（IANA）的时区数据库提供
     *
     *  ZoneOffset 类，
     *  它是 ZoneId 的一个子类，表示的是当前时间和伦敦格林尼治子午线时间的差异
     *  使用这种方式定义的 ZoneOffset并未考虑任何日光时的影响，所以在大多数情况下，不推荐使用。
     *
     *  ISO-8601日历系统是世界文明日历系统的事实标准
     *  Java 8中另外还提供了4种其他的日历系统
     *   ThaiBuddhistDate 、MinguoDate 、 JapaneseDate 以及 HijrahDate
     *   所有这些类以及 LocalDate 都实现了ChronoLocalDate 接口，能够对公历的日期进行建模
     *
     *   日期及时间API的设计者建议我们使用 LocalDate ，尽量避免使用 ChronoLocalDate ，
     *   原因是开发者在他们的代码中可能会做一些假设，而这些假设在不同的日历系统中，有可能不成立
     */
    private static void zones() {
        ZoneId shanghai = ZoneId.of("Asia/Shanghai");

        LocalDateTime time = LocalDateTime.of(2019,11,12,12,17);
        ZonedDateTime zonTime = time.atZone(shanghai);
        System.out.println(zonTime);

        System.out.println(LocalDate.parse("2019-09-09").atStartOfDay(shanghai));

        System.out.println(Instant.now().atZone(shanghai));

        //通过zoneId,将localDateTime转换为Instant
        LocalDateTime dateTime = LocalDateTime.parse("2018-01-01T10:12:12");
        Instant instant = dateTime.atZone(shanghai).toInstant();
        System.out.println(instant);

        //将 instant 转换为 LocalDateTime
        System.out.println(LocalDateTime.ofInstant(Instant.now(), shanghai));

        //ZoneOffset
        ZoneOffset beijingOffset = ZoneOffset.of("+07:00");
        int hour = OffsetDateTime.of(LocalDateTime.now(), beijingOffset).getHour();
        System.out.println(hour);

        //使用别的日历系统
        System.out.println(MinguoDate.from(LocalDateTime.now()));
        System.out.println(JapaneseDate.from(LocalDateTime.now()));

        //为某个 Locale 显式地创建日历系统
        Chronology chinaChronology = Chronology.ofLocale(Locale.CHINA);
        ChronoLocalDate date = chinaChronology.dateNow();
        System.out.println(date);
    }


    /**
     * 操纵,解析和格式化日期
     *   withAttribute 方法会创建对象的一个副本，并按照需要修改它的属性
     *   plus与minus,加与减
     *   日期复杂操作
     *      使用 TemporalAdjuster对象中的重载with方法
     *
     *   解析日期-时间
     *      和老的 java.util.DateFormat 相比较，所有的 DateTimeFormatter 实例都是线程安全的
     *      你能够以单例模式创建格式器实例，
     *      就像 DateTimeFormatter 所定义的那些常量，并能在多个线程间共享这些实例。
     *
     */
    private static void fomartDate() {
        LocalDate dateDemo = LocalDate.parse("2018-09-09");
        System.out.println(dateDemo.withYear(2019));
        System.out.println(dateDemo.withMonth(10));
        System.out.println(dateDemo.with(ChronoField.DAY_OF_MONTH, 10));


        System.out.println(dateDemo.plusDays(1));
        System.out.println(dateDemo.minusDays(1));
        System.out.println(dateDemo.plus(1, ChronoUnit.DAYS));

        //练习
        System.out.println(new NextWorkingDay().adjustInto(LocalDate.now()));

        //打印输出及解析日期-时间对象,java.time.format 包就是特别为这个目的而设计的
        //和老的 java.util.DateFormat 相比较，所有的 DateTimeFormatter 实例都是线程安全的
        System.out.println(LocalDate.of(2019,02,01)
                .format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(LocalDate.parse("2019-02-01")
                .format(DateTimeFormatter.BASIC_ISO_DATE));

        //指定转换规则转换为string与转换回localDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String format = LocalDate.of(2018, 02, 01)
                .format(formatter);
        System.out.println(format);
        System.out.println(LocalDate.parse(format, formatter));

        //创建一个本地化的DateTimeFormatter
        DateTimeFormatter localFormatter = DateTimeFormatter
                .ofPattern("yyyy年MM月dd日", Locale.CHINA);
        String localString = LocalDate.of(2018, 02, 02)
                .format(localFormatter);
        LocalDate parseDate = LocalDate.parse(localString, localFormatter);
        System.out.println(localString + "\n" + parseDate);

        //通过DateTimeFormatterBuilder 自定义实现
        DateTimeFormatter customizeFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.YEAR)
                .appendLiteral("年")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("")
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral("日")
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINA);

        String customizeDateS = LocalDate.of(2019, 19, 9)
                .format(customizeFormatter);
        System.out.println(customizeDateS);
    }


    /**
     * Instant静态工厂方法 ofEpochSecond 传递一个代表秒数的值创建一个该类的实例
     * Instant 的设计初衷是为了便于机器使用。它包含的是由秒及纳秒所构成的数字
     *  System.out.println(instant.get(ChronoField.YEAR));
     *  会出现异常,通过 Duration 和 Period 类使用 Instant则可以通过
     *
     *  between比较持续时间,一个是为了便于人阅读使用，另一个是为了便于机器处理，所以你不能将二者混用
     *          不能比较localDate,可以使用Period比较
     */
    private static void instantDurationAndPeriod() {
        Instant instant = Instant.ofEpochSecond(3, 1_0000_0000);
        Instant instant1 = Instant.ofEpochSecond(4, 10_0000_0000);

        //Duration(以差值定义对象)
        Duration between = Duration.between(instant, instant1);
        Duration between1 = Duration.between(LocalTime.parse("14:22:22"), LocalTime.parse("14:23:23"));
        System.out.println(between.getSeconds());
        System.out.println(between1.getSeconds());

        //Period(以差值定义对象)
        int days = Period.between(LocalDate.parse("2018-02-01"), LocalDate.parse("2018-02-02"))
                .getDays();
        System.out.println(days);

        //工厂类生成实例
        Duration duration = Duration.ofMinutes(3);
        Duration.of(3, ChronoUnit.MINUTES);

        Period period = Period.ofDays(10);
        Period period1 = Period.ofWeeks(2);
        Period period2 = Period.of(2018, 02, 12);
    }

    private static void localDateAndLocalTime() {
        //日期操作
        LocalDate date = LocalDate.of(2018, 10, 05);
        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.lengthOfYear());
        System.out.println(date.lengthOfMonth());
        System.out.println(date.isLeapYear());
        //从操作系统获取当前日期
        System.out.println(LocalDate.now());

        //以TemporalField作为参数传递给get方法,可以获得相同的结果
        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));

        //时间操作
        LocalTime time = LocalTime.of(17, 50, 44);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());

        //解析字符串创建日期或时间
        System.out.println(LocalDate.parse("2018-10-05").getYear());
        System.out.println(LocalTime.parse("17:44:44").getHour());

        //合并日期和时间
        LocalDateTime.of(2018, Month.OCTOBER, 05, 17, 34, 33);
        LocalDateTime.of(LocalDate.parse("2018-10-05"), LocalTime.parse("17:34:33"));
        LocalDateTime dateTime = LocalDate.now().atTime(LocalTime.parse("17:34:34"));
        LocalDate.now().atTime(17, 34, 34);
        LocalTime.now().atDate(LocalDate.parse("2018-10-05"));

        //获取日期或时间
        System.out.println(dateTime.toLocalDate());
        System.out.println(dateTime.toLocalTime());





    }

}
