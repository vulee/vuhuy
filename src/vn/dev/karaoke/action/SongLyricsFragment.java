package vn.dev.karaoke.action;

import vn.dev.karaoke.R;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SongLyricsFragment extends Fragment {

    String[] arrSongs = {"lyric", "Cháu lên ba", "Cả nhà thương nhau", "Việt nam ơi"};
    /** Text search */
    private String txtSearch;
    /** Position of tab */
    private int posTab;

    private EditText txtSongLyricSearch;

    public SongLyricsFragment(String txtSearch, int posTab){
        this.txtSearch = txtSearch;
        this.posTab = posTab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_song_lyrics, container, false);
        txtSongLyricSearch = (EditText) rootView.findViewById(R.id.txtSongLyricSearch);

        ListView lv = (ListView)rootView.findViewById(R.id.song_list);
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(getActivity(),
                                                                     R.layout.list_view,
                                                                     arrSongs);
        lv.setAdapter(arrAdapter);

        Toast.makeText(this.getActivity(), "song Text search: " + txtSongLyricSearch.getText().toString() + "\n" + "Position of tab: " + posTab, Toast.LENGTH_SHORT).show();
        return rootView;
    }
}
