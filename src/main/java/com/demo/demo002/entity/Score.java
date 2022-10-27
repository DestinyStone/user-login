package com.demo.demo002.entity;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 17:27
 * @Description:
 */
public class Score {
    private String name;

    private Double score;

    public Score() {

    }

    public Score(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
