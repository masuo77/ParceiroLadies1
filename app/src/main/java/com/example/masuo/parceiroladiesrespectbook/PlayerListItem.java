package com.example.masuo.parceiroladiesrespectbook;

/**
 * Created by Masuo on 2016/12/11.
 */

public class PlayerListItem {
    private int imageRes;
    private int id;
    private String name;
    private String number;
    private String position;
    private String note;
    private String join;
    private String leaving;

    public PlayerListItem() {
    }

//    public PlayerListItem(int imageRes, String name, String number, String position) {
//        this.imageRes = imageRes;
//        this.name = name;
//        this.number = number;
//        this.position = position;
//    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() { return name; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

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

}
