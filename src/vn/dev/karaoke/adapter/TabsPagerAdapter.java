package vn.dev.karaoke.adapter;

import vn.dev.karaoke.action.SongLyricsFragment;
import vn.dev.karaoke.action.SongNamesFragment;
import vn.dev.karaoke.action.SongNumsFragment;
import vn.dev.karaoke.action.SongSingsFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /** Text search */
    private String txtSearch;

    /** Position of tab */
    private int posTab;

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 5;
    }

    @Override
    public Fragment getItem(int posTab) {
        switch (posTab) {
        case 0:
            return new SongNamesFragment(txtSearch, posTab);
        case 1:
            return new SongLyricsFragment(txtSearch, posTab);
        case 2:
            return new SongSingsFragment(txtSearch, posTab);
        case 3:
            return new SongNumsFragment(txtSearch, posTab);

        default:
            return new SongNamesFragment(txtSearch, 0);
        }
    }

    public void setTxtSearch(String txtSearch){
        this.txtSearch = txtSearch;
    }

    public String getTxtSearch(){
        return this.txtSearch;
    }

    public int getPosTab() {
        return posTab;
    }

    public void setPosTab(int posTab) {
        this.posTab = posTab;
    }
}
