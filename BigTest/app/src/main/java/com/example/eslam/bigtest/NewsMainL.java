package com.example.eslam.bigtest;

/**
 * Created by xrezut on 03/03/2018.
 */

public class NewsMainL {

    private String titleL;
    private String descL;
    private String imageL;
    private String urlL;

    public NewsMainL(String titleL, String descL, String imageL, String urlL) {
        this.titleL = titleL;
        this.descL = descL;
        this.imageL = imageL;
        this.urlL = urlL;
    }

    public NewsMainL(){

    }

    public String getTitleL() {
        return titleL;
    }

    public void setTitleL(String titleL) {
        this.titleL = titleL;
    }

    public String getDescL() {
        return descL;
    }

    public void setDescL(String descL) {
        this.descL = descL;
    }

    public String getImageL() {
        return imageL;
    }

    public void setImageL(String imageL) {
        this.imageL = imageL;
    }

    public String getUrlL() {
        return urlL;
    }

    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }
}
