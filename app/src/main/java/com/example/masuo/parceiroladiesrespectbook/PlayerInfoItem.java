package com.example.masuo.parceiroladiesrespectbook;

import java.io.Serializable;

/**
 * Created by Masuo on 2016/12/11.
 */

public class PlayerInfoItem implements Serializable {
    private int imageRes;
    private String name;
    private String yomi;
    private String yomi_j;
    private String birthday;
    private String height;
    private String weight;
    private String blood;
    private String home;
    private String career;

    private String number;
    private String position;
    private String season_note;

    private String joining_season;
    private String joining_announced_at;
    private String joining_comment;
    private String joining_note;

    private String leaving_season;
    private String leaving_announced_at;
    private String leaving_comment;
    private String leaving_note;
    private String after_leaving;


    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSeason_note() {
        return season_note;
    }

    public void setSeason_note(String season_note) {
        this.season_note = season_note;
    }

    public String getJoining_season() {
        return joining_season;
    }

    public void setJoining_season(String joining_season) {
        this.joining_season = joining_season;
    }

    public String getJoining_announced_at() {
        return joining_announced_at;
    }

    public void setJoining_announced_at(String joining_announced_at) {
        this.joining_announced_at = joining_announced_at;
    }

    public String getJoining_comment() {
        return joining_comment;
    }

    public void setJoining_comment(String joining_comment) {
        this.joining_comment = joining_comment;
    }

    public String getJoining_note() {
        return joining_note;
    }

    public void setJoining_note(String joining_note) {
        this.joining_note = joining_note;
    }

    public String getLeaving_season() {
        return leaving_season;
    }

    public void setLeaving_season(String leaving_season) {
        this.leaving_season = leaving_season;
    }

    public String getLeaving_announced_at() {
        return leaving_announced_at;
    }

    public void setLeaving_announced_at(String leaving_announced_at) {
        this.leaving_announced_at = leaving_announced_at;
    }

    public String getLeaving_comment() {
        return leaving_comment;
    }

    public void setLeaving_comment(String leaving_comment) {
        this.leaving_comment = leaving_comment;
    }

    public String getLeaving_note() {
        return leaving_note;
    }

    public void setLeaving_note(String leaving_note) {
        this.leaving_note = leaving_note;
    }

    public String getAfter_leaving() {
        return after_leaving;
    }

    public void setAfter_leaving(String after_leaving) {
        this.after_leaving = after_leaving;
    }

    public PlayerInfoItem() {
    }


}
