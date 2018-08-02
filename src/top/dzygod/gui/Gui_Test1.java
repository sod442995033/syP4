package top.dzygod.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/13 10:11
 * @Description: 图形用户接口的练习
 */
public class Gui_Test1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        test1();
//        test2();


        String[] stirngs = new String[10];

            System.out.println(stirngs[11]);
    }


    /**
     * 监听器的练习
     * 传入的参数,不用实现WindowListener接口
     * 可以继承WindowAdapter类,这个适配器实现了WindowListener接口
     * <p>
     * 鼠标监听
     */
    private static void test2() throws UnsupportedEncodingException {
        Frame gui = new Frame("练习GUI");
        gui.setSize(600, 800);

        gui.setLocation(600, 100);
        gui.setIconImage(Toolkit.getDefaultToolkit().createImage("图片.png"));

        Button button = new Button(new String("退出程序".getBytes(), "UTF-8"));
        gui.add(button);

        //设置布局管理器
        gui.setLayout(new GridBagLayout());

        //设置监听器,点击关闭按钮关闭虚拟机
        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        //鼠标监听器:鼠标单击监听与鼠标释放监听
        button.addMouseListener(new MouseAdapter() {
            /*@Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }*/

            @Override
            public void mouseReleased(MouseEvent e) {
                System.exit(0);
            }
        });


        //键盘监听

        //设置窗体可见
        gui.setVisible(true);


        /*
            //设置编码
            System.out.println(System.getProperty("user.encoding"));

            System.getProperties().put("file.encoding", "UTF-8");

            System.getProperties().list(System.out);
        */



    }


    /**
     * 布局管理器
     * FlowLayout(流式布局管理器)
     * 从左到右的顺序排列,永远在一行的中央位置,二个也是,三个也是
     * Panel默认的布局管理器
     * BorderLayout
     * 东,南,西,北,中
     * Frame默认的布局管理器
     * GridLayout(网格布局管理器)
     * 规则的矩阵
     * GardLayout(卡片布局管理器)
     * 选项卡
     * GridBagLayout(网格包布局管理器)
     * 非规则的矩阵
     */
    private static void test1() {
        Frame gui = new Frame("练习GUI");

        gui.setSize(600, 800);

        gui.setLocation(600, 100);
        gui.setIconImage(Toolkit.getDefaultToolkit().createImage("图片.png"));


        Button button = new Button("按钮1");
        gui.add(button);

        //设置布局管理器
        gui.setLayout(new GridBagLayout());

        //设置窗体可见
        gui.setVisible(true);


    }
}