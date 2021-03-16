package com.example.week7b_31720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

public class DetailVideoActivity extends AppCompatActivity {

    private VideoView detailVideo;
    private EditText titleEdit;
    private EditText descEdit;
    private EditText uriEdit;
    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        detailVideo = (VideoView) findViewById(R.id.detailVid);
        titleEdit = (EditText) findViewById(R.id.TitleEdit);
        descEdit = (EditText)findViewById(R.id.DescEdit);
        uriEdit = (EditText)findViewById(R.id.UriEdit);
        backBtn = (Button) findViewById(R.id.BackBtn);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        VideoSource sv =(VideoSource) bundle.getSerializable("DetailVideo");
        titleEdit.setText(sv.getTitle());
        descEdit.setText(sv.getDesc());
        uriEdit.setText(sv.getVideoURI());
        detailVideo.setVideoURI(Uri.parse(sv.getVideoURI()));
        detailVideo.seekTo(100);
        detailVideo.start();
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(detailVideo);
        detailVideo.setMediaController(controller);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}