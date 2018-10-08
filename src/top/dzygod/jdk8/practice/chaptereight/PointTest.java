package top.dzygod.jdk8.practice.chaptereight;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/9 17:02
 * @Description: 测试可见lambda函数的行为
 */
public class PointTest {

    @Test
    public void testMoveRightBy() throws Exception {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);
        assertEquals(15, p2.getX());
        assertEquals(5, p2.getY());
    }
    @Test
    public void testComparingTwoPoints() throws Exception {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1 , p2);
            assertEquals(-1, result);
    }
}