package com.example.masuo.parceiroladiesrespectbook.StaffList;

/**
 * Created by Masuo on 2016/12/11.
 */

public class StaffListItem {
    //    private int imageRes;
    private String id;
    private String name;
    private String post;
    private String season;
    private String face;
//    private String latest_face;

    public StaffListItem() {
    }

//    public int getImageRes() {
//        return imageRes;
//    }
//    public void setImageRes(int imageRes) {
//        this.imageRes = imageRes;
//    }

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

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

}
