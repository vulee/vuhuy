package vn.dev.karaoke.action;

import vn.dev.karaoke.R;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnKeyListener;
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

        // add a keylistener to keep track user input

            txtSongLyricSearch.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Toast.makeText(SongLyricsFragment.this.getActivity(), txtSongLyricSearch.getText(), Toast.LENGTH_LONG).show();
                // if keydown and "enter" is pressed
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // display a floating message
                    Toast.makeText(SongLyricsFragment.this.getActivity(), txtSongLyricSearch.getText(), Toast.LENGTH_LONG).show();
                    return true;
                } else if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_9)) {
                    // display a floating message
                    Toast.makeText(SongLyricsFragment.this.getActivity(), "Number 9 is pressed!", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });

        Toast.makeText(this.getActivity(), "Text search: " + txtSongLyricSearch.getText(), Toast.LENGTH_SHORT).show();
        return rootView;
    }
}
