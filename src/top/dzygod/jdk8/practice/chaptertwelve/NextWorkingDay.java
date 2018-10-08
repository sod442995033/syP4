package top.dzygod.jdk8.practice.chaptertwelve;

import java.time.DayOfWeek;
import java.time.temporal.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/6 10:02
 * @Description:
 *  请设计一个 NextWorkingDay 类，该类实现了 TemporalAdjuster 接口，
 *  能够计算明天的日期，同时过滤掉周六和周日这些节假日。
 *  如果当天的星期介于周一至周五之间，日期向后移动一天；如果当天是周六或者周日，
 *  则返回下一个周一。
 *
 */
public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dow == DayOfWeek.FRIDAY) {
            dayToAdd = 3;
        } else if (dow == DayOfWeek.SATURDAY) {
            dayToAdd = 2;
        }
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
