package com.example.week7b_31720;

import java.io.Serializable;
import android.net.Uri;

public class VideoSource implements Serializable {
    private String title;
    private String desc;
    private String videoURI;

    public VideoSource(String title, String desc,String videoURI){
        this.title = title;
        this.desc = desc;
        this.videoURI = videoURI;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getVideoURI() {
        return videoURI;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setVideoURI(String videoURI) {
        this.videoURI = videoURI;
    }

    public String toString(){
        return this.getTitle() + "=>" + this.getDesc();
    }
}
