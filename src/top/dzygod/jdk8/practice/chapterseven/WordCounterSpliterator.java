package top.dzygod.jdk8.practice.chapterseven;

import com.sun.istack.internal.NotNull;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/7 10:49
 * @Description: 实现Spliterator处理并行流
 */
public class WordCounterSpliterator implements Spliterator<Character> {


    private String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string ) {
        this.string = string;
    }



    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;

        //返回 null 表示要解析的 String已经足够小，可以顺序处理
        if (currentSize < 10) {
            return null;
        }

        //将试探拆分位置放在要解析的string的中间
        for (int splitPos = currentSize / 2 + currentChar;
             splitPos < string.length();
             splitPos++) {
            //让拆分位置前进,直到下一个空格
            if (Character.isWhitespace(string.charAt(splitPos))) {

                //实例化一个新的WordCounterSpliterator来解析string从开始到拆分位置的部分
                WordCounterSpliterator spliterator = new WordCounterSpliterator
                        (string.substring(currentChar));

                //将这个WordCounterSpliterator的起始位置设置成拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    /**
     * characteristic 方法告诉框架这个 Spliterator 是 ORDERED （顺序就是 String
     * 中各个 Character 的次序）、 SIZED （ estimatedSize 方法的返回值是精确的）、
     * SUBSIZED （ trySplit 方法创建的其他 Spliterator 也有确切大小）、 NONNULL （ String
     * 中 不 能 有 为 null 的 Character ） 和 IMMUTABLE （ 在 解 析 String 时 不 能 再 添 加
     * Character ，因为 String 本身是一个不可变类）的。
     * @return
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
