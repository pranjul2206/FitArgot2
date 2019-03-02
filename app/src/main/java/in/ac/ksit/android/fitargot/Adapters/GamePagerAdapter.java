package in.ac.ksit.android.fitargot.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.ac.ksit.android.fitargot.Fragments.CreateGame;
import in.ac.ksit.android.fitargot.Fragments.JoinGame;

public class GamePagerAdapter extends FragmentPagerAdapter {
    public GamePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:

                return new CreateGame();
            case 1:
                return new JoinGame();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:

                return "Create Event";
            case 1:
                return "Join Game";

        }

        return null;

    }
}
