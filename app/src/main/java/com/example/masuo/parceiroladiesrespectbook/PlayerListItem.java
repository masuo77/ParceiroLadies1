package com.example.masuo.parceiroladiesrespectbook;

/**
 * Created by Masuo on 2016/12/11.
 */

public class PlayerListItem {
    private int imageRes;
    private String name;
    private String position;
    private String number;

    public PlayerListItem(int imageRes, String name) {
        this.imageRes = imageRes;
        this.name = name;
    }
    public PlayerListItem(int imageRes, String name, String position) {
        this.imageRes = imageRes;
        this.name = name;
        this.position = position;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

}
