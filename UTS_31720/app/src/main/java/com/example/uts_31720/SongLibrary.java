package com.example.uts_31720;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.uts_31720.PermissionCheck;

import java.util.ArrayList;
import java.util.List;

public class SongLibrary extends AppCompatActivity implements SongAdapter.SongAdapterListener{

    public PopupWindow welcomeCard;
    public Button closeBtn;
    public LinearLayout cL;
    public Toolbar toolbar;
    private final int STORAGE_PERMISSION_ID = 0;
    private List<Song> songLists = new ArrayList<>();
    public RecyclerView songRecyclerView;
    public SongAdapter sAdapter;
    //public LinearLayout lLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_library);
        cL = (LinearLayout) findViewById(R.id.rootLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Song Library");
        init();
        songRecyclerView = (RecyclerView) findViewById(R.id.songListView);
        sAdapter = new SongAdapter(getApplicationContext(),songLists,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        songRecyclerView.setAdapter(sAdapter);
        songRecyclerView.setLayoutManager(layoutManager);

        LayoutInflater lI = (LayoutInflater) SongLibrary.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cV = lI.inflate(R.layout.welcome_popup,null);

        closeBtn = (Button) cV.findViewById(R.id.btnClose);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                welcomeCard = new PopupWindow(cV, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                welcomeCard.showAtLocation(findViewById(R.id.rootLayout),Gravity.CENTER,0,0);
            }
        },10);

        closeBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                welcomeCard.dismiss();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.song_library_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.toProfile2:
                Intent prof = new Intent(SongLibrary.this,ActivityProfile.class);
                startActivityForResult(prof,1);
                return true;
            case R.id.toLogout2:
                Intent log = new Intent(SongLibrary.this,LoginActivity.class);
                startActivityForResult(log,1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init(){
        if(!CheckStorePermission(STORAGE_PERMISSION_ID)){
            ShowRequestPermission(STORAGE_PERMISSION_ID);
        }
    }

    private boolean CheckStorePermission(int permission){
        if (permission == STORAGE_PERMISSION_ID){
            return PermissionCheck.CheckPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else {
            return true;
        }
    }

    private void ShowRequestPermission(int requestCode){
        String[] permissions;
        if(requestCode == STORAGE_PERMISSION_ID){
            permissions = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
        } else{
            permissions = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
        }
        PermissionCheck.RequestPermission(this,requestCode,permissions);
    }

    public void onRequestPermissionResult(int requestCode,
                                          @NonNull String[] permissions,
                                          @NonNull int[] grantResults){
        if(requestCode == 0){
            for( int i = 0, len = permissions.length; i<len ; i++){
                if(grantResults[i]== PackageManager.PERMISSION_GRANTED){
                    GetSongLists();
                    return;
                }
            }
        }
    }

    public void GetSongLists(){
        ContentResolver musicResolver = getContentResolver();
        Uri mscUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor mscCursor = musicResolver.query(mscUri,null,selection,null,null);

        if (mscCursor != null && mscCursor.moveToFirst()){
            int titleColumn = mscCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = mscCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int durationColumn = mscCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int sizeColumn = mscCursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
            int artistColumn = mscCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumColumn = mscCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int albumIdColumn= mscCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int dataColumn=mscCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                long thisId = mscCursor.getLong(idColumn);
                String thisTitle = mscCursor.getString(titleColumn);
                int thisDuration = mscCursor.getInt(durationColumn);
                int thisSize = mscCursor.getInt(sizeColumn);
                Uri thisUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,thisId);
                String thisArtist = mscCursor.getString(artistColumn);
                String thisAlbum = mscCursor.getString(albumColumn);
                int thisAlbumId = mscCursor.getInt(albumIdColumn);
                String thisData = mscCursor.getString(dataColumn);
                songLists.add(new Song(thisUri,thisId,thisTitle,thisDuration,thisSize,thisArtist,thisAlbum,thisAlbumId, thisData));
            } while(mscCursor.moveToNext());
        }
        assert mscCursor != null;
        mscCursor.close();
        Toast.makeText(this, songLists.size() +" Songs Found",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSongSelected(Song song) {
        //later
    }
    //Set up recycler View
}