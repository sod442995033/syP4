package top.dzygod.jdk8.practice.chapterseven;


import java.util.Spliterator;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/7 9:41
 * @Description: 自定义并行spliterator可分迭代器
 */
public class WordCounter {
    private int counter;
    private boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }


    /**
     * 上一个char是空格,当前遍历的字符不是空格时,将计数器加一
     * @param c
     * @return
     */
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }




    public WordCounter combine(WordCounter wordCount) {
        return new WordCounter(this.counter + wordCount.getCounter(),wordCount.lastSpace);
    }

    public int getCounter() {
        return counter;
    }

}
