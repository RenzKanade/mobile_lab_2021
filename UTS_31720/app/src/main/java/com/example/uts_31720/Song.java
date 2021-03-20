package com.example.uts_31720;

import android.net.Uri;

import java.io.Serializable;

public class Song implements Serializable {
    private long song_id;
    private String song_title;
    private String song_artist;
    private String song_album;
    private int song_albumId;
    private long song_duration;
    private int song_size;
    private Uri song_uri;
    private String song_data;

    public Song(Uri songUri, long songId, String songTitle, long songDuration, int songSize, String songArtist, String songAlbum, int songAlbumId, String songData){
        this.song_id = songId;
        this.song_title = songTitle;
        this.song_uri = songUri;
        this.song_duration = songDuration;
        this.song_size = songSize;
        this.song_artist = songArtist;
        this.song_album = songAlbum;
        this.song_albumId = songAlbumId;
        this.song_data = songData;
    }

    public long getID(){
        return song_id;
    }

    public String getTitle(){
        return song_title;
    }

    public Uri getUri(){return song_uri;}

    public long getDuration(){return song_duration;}

    public int getSize(){return song_size;}

    public int getAlbumID(){ return song_albumId;}

    public String getArtist(){return song_artist;}

    public String getAlbum(){return song_album;}

    public String getData(){return song_data;}
}
