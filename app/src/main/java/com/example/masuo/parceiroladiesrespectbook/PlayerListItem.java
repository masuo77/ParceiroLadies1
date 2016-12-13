package com.example.masuo.parceiroladiesrespectbook;

/**
 * Created by Masuo on 2016/12/11.
 */

public class PlayerListItem {
    private int imageRes;
    private String name;

    public PlayerListItem(int imageRes, String name) {
        this.imageRes = imageRes;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }
}
