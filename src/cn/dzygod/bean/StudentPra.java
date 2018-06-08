package cn.dzygod.bean;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/9 11:24
 * @Description: *
 */
public class StudentPra implements Serializable {

    /**
     * 序列化版本号,流生成时会自动生成.
     * 流写入会一起写入文件,在读取时会对比版本号,
     * 版本号不符则会抛出java.io.InvalidClassException异常
     */
    private static final long serialVersionUID = 2L;

    private String name;
    private Integer mathScore;
    private Integer chineseScore;
    private Integer sportScore;
    private Integer totalScore;
    private Integer test;

    public StudentPra() {
    }

    public StudentPra(String name) {
        this.name = name;
    }

    public StudentPra(String name, Integer mathScore, Integer chineseScore, Integer sportScore) {
        this.name = name;
        this.mathScore = mathScore;
        this.chineseScore = chineseScore;
        this.sportScore = sportScore;
        this.totalScore = mathScore + chineseScore + sportScore;
    }

    @Override
    public String toString() {
        return "StudentPra{" +
                "name='" + name + '\'' +
                ", mathScore=" + mathScore +
                ", chineseScore=" + chineseScore +
                ", sportScore=" + sportScore +
                ", totalScore=" + totalScore +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMathScore() {
        return mathScore;
    }

    public void setMathScore(Integer mathScore) {
        this.mathScore = mathScore;
    }

    public Integer getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(Integer chineseScore) {
        this.chineseScore = chineseScore;
    }

    public Integer getSportScore() {
        return sportScore;
    }

    public void setSportScore(Integer sportScore) {
        this.sportScore = sportScore;
    }

    public Integer getTotalScore() {
        return this.mathScore + this.chineseScore + this.sportScore;
    }


}
