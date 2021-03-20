package com.example.uts_31720;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>implements Filterable{

    private List<Song> songLists;
    private List<Song> songListsFiltered;
    private Context songContext;
    private SongAdapterListener songAdapter;

    public SongAdapter(Context context,List<Song> songList, SongAdapterListener adapter){
        this.songContext = context;
        this.songAdapter = adapter;
        this.songLists = songList;
        this.songListsFiltered = songList;
    }

    @NonNull
    @Override
    public SongAdapter.SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list,parent,false);
        return new SongViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.SongViewHolder holder, int position) {
        Song song = songListsFiltered.get(position);
        holder.songTitle.setText(song.getTitle());
    }

    @Override
    public int getItemCount() {
        return songListsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter(){

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()){
                    songListsFiltered = songLists;
                } else{
                    List<Song> filteredList = new ArrayList<>();
                    for ( Song row : songLists){
                        if(row.getTitle().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    songListsFiltered = filteredList;
                }
                FilterResults fres = new FilterResults();
                fres.values = songListsFiltered;
                return fres;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                songListsFiltered = (ArrayList<Song>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface SongAdapterListener{
      void onSongSelected(Song song);
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        public TextView songTitle;
        final SongAdapter sAdapter;
        public SongViewHolder(@NonNull View itemView, SongAdapter adapter) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songTitle);
            this.sAdapter = adapter;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    songAdapter.onSongSelected(songListsFiltered.get(getAdapterPosition()));
                }
            });
        }
    }
}
