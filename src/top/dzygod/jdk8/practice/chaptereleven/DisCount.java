package top.dzygod.jdk8.practice.chaptereleven;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/5 15:12
 * @Description: *
 */
public class DisCount {
    public enum Code {
        /**
         * 不同会员的折扣率
         */
        NONE(0),SILVER(5),GOLD(10),PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }


}
