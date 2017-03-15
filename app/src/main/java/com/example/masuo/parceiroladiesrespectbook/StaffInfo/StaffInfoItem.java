package com.example.masuo.parceiroladiesrespectbook.StaffInfo;

import java.io.Serializable;

/**
 * Created by Masuo on 2016/12/11.
 */

public class StaffInfoItem implements Serializable {
    //    private int imageRes;
    private String name;
    private String yomi;
    private String yomi_j;
    private String birthday;
    private String height;
    private String weight;
    private String blood;
    private String home;
    private String career;
    private String face;
    private String post;
    private String season_comment;
    private String season;

    public String getSeasonComment() {
        return season_comment;
    }

    public void setSeasonComment(String season_comment) {
        this.season_comment = season_comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYomi() {
        return yomi;
    }

    public void setYomi(String yomi) {
        this.yomi = yomi;
    }

    public String getYomi_j() {
        return yomi_j;
    }

    public void setYomi_j(String yomi_j) {
        this.yomi_j = yomi_j;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

}
