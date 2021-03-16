package com.example.week7b_31720;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvVideoList;
    VideoAdapterList mAdapter;
    LinkedList<VideoSource> videoList = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvVideoList = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new VideoAdapterList(this,videoList);
        rvVideoList.setAdapter(mAdapter);
        rvVideoList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void ListVideoContent(){
        videoList.add(new VideoSource("Mini Cooper Drag","Drag Race Mini Cooper w Honda Civic hatchback","android.resource://"+getPackageName()+"/"+R.raw.mini01));
        videoList.add(new VideoSource("Porsche 918","Enjoys sport car Porsche 918 Spyder","android.resource://"+getPackageName()+"/"+R.raw.porsche918));
        videoList.add(new VideoSource("Drift H2H","Head to head drifting, Fast and Furious sneakpeek","android.resource://"+getPackageName()+"/"+R.raw.drift01));
        videoList.add(new VideoSource("Play Tag","Tag in fast and furious","android.resource://"+getPackageName()+"/"+R.raw.drift02));
        videoList.add(new VideoSource("Mini Cooper Drag Race","Drag Race Mini Cooper w Ford Fiesta hatchback","android.resource://"+getPackageName()+"/"+R.raw.mini02));
    }
}