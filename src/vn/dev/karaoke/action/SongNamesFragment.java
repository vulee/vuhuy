package vn.dev.karaoke.action;

import java.io.IOException;
import java.util.List;

import vn.dev.karaoke.R;
import vn.dev.karaoke.adapter.CustomAdapter;
import vn.dev.karaoke.helper.DatabaseHelper;
import vn.dev.karaoke.dto.Song;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SongNamesFragment extends Fragment {
    String[] arrSongs = {"Hello Vietnam", "Cháu lên ba", "Cả nhà thương nhau", "Việt nam ơi"};
    /** Text search */
    private String txtSearch;
    /** Position of tab */
    private int posTab;

    private EditText txtSongNameSearch;

    public SongNamesFragment(String txtSearch, int posTab){
        this.txtSearch = txtSearch;
        this.posTab = posTab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        DatabaseHelper dataBaseHelper = new DatabaseHelper(this.getActivity());
        List<Song> songList = dataBaseHelper.getAllSongs();

        View rootView = inflater.inflate(R.layout.fragment_song_names, container, false);
        txtSongNameSearch = (EditText) rootView.findViewById(R.id.txtSongNameSearch);
        
        ListView lv = (ListView) rootView.findViewById(R.id.song_list);

         // get data from the table by the ListAdapter
         CustomAdapter customAdapter = new CustomAdapter(getActivity(), R.layout.song_list_view, songList);

         lv.setAdapter(customAdapter);
        /*ListView lv = (ListView)rootView.findViewById(R.id.song_list);
         ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(getActivity(), 
                                                                     R.layout.list_view, 
                                                                     arrSongs);
        lv.setAdapter(arrAdapter);*/
        Toast.makeText(this.getActivity(), "song Text search: " + txtSongNameSearch.getText().toString() + "\n" + "Position of tab: " + posTab, Toast.LENGTH_SHORT).show();
        return rootView;
    }
}
