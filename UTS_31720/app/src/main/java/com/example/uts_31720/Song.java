package com.example.uts_31720;

import android.net.Uri;

import java.io.Serializable;

public class Song implements Serializable {
    private long song_id;
    private String song_title;
    private int song_duration;
    private int song_size;
    private Uri song_uri;

    public Song(Uri songUri, long songId, String songTitle, int songDuration, int songSize){
        this.song_id = songId;
        this.song_title = songTitle;
        this.song_uri = songUri;
        this.song_duration = songDuration;
        this.song_size = songSize;
    }

    public long getID(){
        return song_id;
    }

    public String getTitle(){
        return song_title;
    }

    public Uri getUri(){return song_uri;}

    public int getDuration(){return song_duration;}

    public int getSize(){return song_size;}
}
