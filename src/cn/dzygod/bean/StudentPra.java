package cn.dzygod.bean;

import java.util.Comparator;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/9 11:24
 * @Description: *
 */
public class StudentPra{

    private String name;
    private Integer mathScore;
    private Integer chineseScore;
    private Integer sportScore;
    private Integer totalScore;


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
