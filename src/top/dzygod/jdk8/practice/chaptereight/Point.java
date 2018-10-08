package top.dzygod.jdk8.practice.chaptereight;


import java.util.Comparator;


/**
 * @Author: dingziyuan
 * @Date: 2018/9/9 16:49
 * @Description:
 */
public class Point {

    public static Comparator<Point> compareByXAndThenY =
            Comparator.comparing(Point::getX).thenComparing(Point::getY);

    private int x;
    private int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point moveRightBy(int x) {
        return new Point(this.x + x, this.y);
    }




}
