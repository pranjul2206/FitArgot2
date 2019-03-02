package in.ac.ksit.android.fitargot.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

import in.ac.ksit.android.fitargot.Fragments.ArgotChallenge;
import in.ac.ksit.android.fitargot.Fragments.SelfChallenge;
import in.ac.ksit.android.fitargot.Fragments.SocialChallenge;

public class ChallengeAdapter extends FragmentPagerAdapter {
    public ChallengeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return new SelfChallenge();

            case 1:
                return new SocialChallenge();
            case 2:
                return new ArgotChallenge();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        switch(position){
            case 0:
                return "SelfChallenge";

            case 1:
                return "SocialChallenge";
            case 2:
                return "ArgotChallenge";

        }
        return null;
    }
}
