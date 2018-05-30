package cn.dzygod.file;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/14 16:26
 * @Description: *
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
//        constructors();
//        create();
//        renameAndDel();
//        judge();
//        achieve();


    }

    /**
     * 获取功能
     *  public String getAbsolutePath(); 获取绝对路径
     *  public String getPath(); 获取路径
     *  public String getName(); 获取名称
     *  public long length(); 获取文件长度字节数
     *  public long lastModified();获取最后一次的修改时间,毫秒值
     *  public String[] list(); 获取指定目录下的所有文件或者文件夹的名称数组
     *  public File[] listFiles();获取指定目录下的所有文件或者文件夹的File数组
     */
    private static void achieve() {


        File file = new File("测试.txt");
        File file1 = new File("E:\\workStudy\\双元P4\\测试.txt");

        //获取绝对路径
        String absolutePath = file.getAbsolutePath();
        String absolutePath2 = file1.getAbsolutePath();

        System.out.println(absolutePath+"\n"+absolutePath2);


        //获取构造里传入的路径
        String path = file.getPath();
        String path1 = file1.getPath();

        System.out.println(path1);

        //获取文件名称
        String name = file.getName();
        String name1 = file1.getName();
        System.out.println(name+"\n"+name1);

        //获取文件长度(字节数)
        long length = file.length();
        long length1 = file1.length();
        System.out.println(length+"\n"+length1);

        //获取最后一次修改的时间
        long lastModified = file.lastModified();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        String format1 = format.format(new Date(lastModified));
        System.out.println(format1);

        //获取指定目录下的所有文件或者文件夹的名称数组
        File file2 = new File("文件夹.txt");
        String[] list = file2.list();
        for (String s : list) {
            //输出所有子级目录的名称
            System.out.println(s);
        }

        //获取指定目录下的所有文件或者文件夹的File数组
        File[] files = file2.listFiles();
        for (File file3 : files) {
            //包括父级输出全限定名
            System.out.println(file3);
        }
    }


    /**
     * File类的判断功能
     *  public boolean  isDirectory(); 判断是否是目录
     *  public boolean  isFile();判断是否为文件
     *  public boolean  exists();判断是否存在
     *  public boolean  canWrite();判断是否可写
     *  public boolean  canRead();判断是否可读
     *  public boolean  isHidden();判断是否隐藏
     *
     */
    private static void judge() {


        File test = new File("测试.txt");
        boolean isDirectory = test.isDirectory();

        boolean isFile = test.isFile();

        boolean isExists = test.exists();

        //设置为不可读   windows下默认所有文件都是可读的
        test.setReadable(false);
        boolean read = test.canRead();

        //设置为不可写    输出为false
        test.setWritable(false);
        boolean write = test.canWrite();

        boolean isHidden = test.isHidden();

        System.out.println(isDirectory+"\n"+isFile+"\n"+isExists+"\n"+read+"\n"+write+"\n"+isHidden);
    }


    /**
     * 重命名和删除功能
     * public boolean ranameTo(File file);  把文件命名为指定的文件路径
     * public boolean delete(); 删除文件或文件夹
     * 重命名注意事项
     *  如果路径名相同,就是改名
     *  如果路径名不同,就是改名并剪切
     * 删除注意事项
     * java的 删除不走回收站
     * 要删除一个文件夹,请注意该文件夹内不能包含文件或者文件夹
     */
    private static void renameAndDel() {


        File file = new File("文件夹.txt\\测试");
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);


        File after = new File("改名后");
        boolean afterBoln = file.renameTo(after);
        System.out.println(after);

        after.delete();
    }


    /**
     *  创建功能
     *      public  boolean  createNewFile();  创建文件 如果存在这样的文件就不创建了
     *      public boolean mkdir(); 创建文件夹,如果存在这样的文件夹,就不创建了
     *      public boolean mkdirs();创建文件夹,如果父文件夹不存在,会帮你创建出来
     */
    private static void create() throws IOException {

        File file = new File("测试.txt");
        boolean file1 = file.createNewFile();
        System.out.println(file1);

        File dir = new File("文件夹.txt");
        boolean dirBoen = dir.mkdir();
        System.out.println(dirBoen);

        File dirs = new File("D://多级目录.txt");
        boolean dirsBoen = dirs.mkdirs();
        System.out.println(dirsBoen);
    }


    /**
     * file类的构造方法
     * File(String pathname): 根据一个路径获得到File对象
     * File(String parent,String child):  根据一个目录和一个子文件/目录得到File对象
     * File(File parent,String child): 根据一个父file对象和一个子文件/目录得到file对象
     */
    private static void constructors() {

        File xxx = new File("xxx.txt");

        File xxx1 = new File("E:\\workStudy\\双元P4","yy.txt");

        File file = new File("E:\\workStudy\\双元P4");
        File xxx2 = new File(file,"ff.txt");


    }

}
