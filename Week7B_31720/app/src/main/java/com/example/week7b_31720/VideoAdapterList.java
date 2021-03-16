package com.example.week7b_31720;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class VideoAdapterList extends RecyclerView.Adapter<VideoAdapterList.ItemVideoViewHolder> {
    private LinkedList<VideoSource> mVideoList;
    private LayoutInflater mInflater;
    private Context mContext;

    public VideoAdapterList(Context context, LinkedList<VideoSource> videoList){
        this.mContext = context;
        this.mVideoList = videoList;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VideoAdapterList.ItemVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.video_item_layout,parent,false);
        return new ItemVideoViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapterList.ItemVideoViewHolder holder, int position) {
        VideoSource mVideoSource = mVideoList.get(position);
        holder.TitleView.setText(mVideoSource.getTitle());
        holder.DescView.setText(mVideoSource.getDesc());
        holder.UriView.setText(mVideoSource.getVideoURI());
        holder.VideoBox.setVideoURI(Uri.parse(mVideoSource.getVideoURI()));
        holder.VideoBox.seekTo(100);
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    class ItemVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private VideoView VideoBox;
        private TextView TitleView;
        private TextView DescView;
        private TextView UriView;
        private VideoAdapterList mAdapter;
        private int mPosition;
        private VideoSource mVideoSource;
        public ItemVideoViewHolder(@NonNull View itemView,VideoAdapterList adapter){
            super(itemView);
            mAdapter = adapter;
            VideoBox = (VideoView) itemView.findViewById(R.id.VideoBox);
            TitleView = (TextView) itemView.findViewById(R.id.TitleView);
            DescView = (TextView) itemView.findViewById(R.id.DescView);
            UriView = (TextView) itemView.findViewById(R.id.UriView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            mPosition = getLayoutPosition();
            mVideoSource = mVideoList.get(mPosition);
            Intent intentDetail = new Intent(mContext,DetailVideoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("DetailVideo",mVideoSource);
            intentDetail.putExtras(bundle);
            mContext.startActivity(intentDetail);
        }
    }
}
