package com.example.masuo.parceiroladiesrespectbook.PlayerList;

/**
 * Created by Masuo on 2016/12/11.
 */

public class PlayerListItem {
    private int imageRes;
    private String id;
    private String name;
    private String number;
    private String position;
    private String note;
    private String join;
    private String leaving;
    private String season;
    private String face;
//    private String latest_face;

    public PlayerListItem() {
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getLeaving() {
        return leaving;
    }

    public void setLeaving(String leaving) {
        this.leaving = leaving;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

//    public String getLatestFace() {
//        return latest_face;
//    }
//
//    public void setLatestFace(String latest_face) {
//        this.latest_face = latest_face;
//    }


}
