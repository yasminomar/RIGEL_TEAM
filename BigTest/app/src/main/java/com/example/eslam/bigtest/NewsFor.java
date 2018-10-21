package com.example.eslam.bigtest;

/**
 * Created by xrezut on 03/03/2018.
 */

public class NewsFor {

    private String titlefor;
    private String descfor;
    private String imagefor;
    private String urlfor;

    public NewsFor(String titlefor, String descfor, String imagefor, String urlfor) {
        this.titlefor = titlefor;
        this.descfor = descfor;
        this.imagefor = imagefor;
        this.urlfor = urlfor;
    }

    public NewsFor(){

    }

    public String getTitlefor() {
        return titlefor;
    }

    public void setTitlefor(String titlefor) {
        this.titlefor = titlefor;
    }

    public String getDescfor() {
        return descfor;
    }

    public void setDescfor(String descfor) {
        this.descfor = descfor;
    }

    public String getImagefor() {
        return imagefor;
    }

    public void setImagefor(String imagefor) {
        this.imagefor = imagefor;
    }

    public String getUrlfor() {
        return urlfor;
    }

    public void setUrlfor(String urlfor) {
        this.urlfor = urlfor;
    }
}
