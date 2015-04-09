package vn.dev.karaoke.adapter;

import java.util.List;

import vn.dev.karaoke.R;
import vn.dev.karaoke.dto.Song;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Song> {

    public CustomAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CustomAdapter(Context context, int resource, List<Song> songs) {
        super(context, resource, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.song_list_view, null);
        }
        Song song = getItem(position);
        if(song != null){
    
            TextView songNum = (TextView) v.findViewById(R.id.songNum);
            TextView vol = (TextView) v.findViewById(R.id.vol);
            TextView songName = (TextView) v.findViewById(R.id.songName);
            TextView songLyric = (TextView) v.findViewById(R.id.songLyric);
            TextView songAuthor = (TextView) v.findViewById(R.id.songAuthor);

            if (songNum != null) {
                songNum.setText(song.getId());
            }

            if (vol != null) {
                vol.setText(song.getVol());
            }

            if (songName != null) {
                songName.setText(song.getSongName().toUpperCase());
            }

            if (songLyric != null) {
                songLyric.setText(song.getSongLyric());
            }

            if (songAuthor != null) {
                songAuthor.setText(song.getSongAuthor());
            }
        }
        return v;
    }
}