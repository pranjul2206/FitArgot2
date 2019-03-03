package in.ac.ksit.android.fitargot.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.ac.ksit.android.fitargot.Network.Model.DietData;
import in.ac.ksit.android.fitargot.R;


enum CustomPagerEnum {

    BREAKFAST(R.string.breakfast, R.layout.diet_list),
    LUNCH(R.string.lunch, R.layout.diet_list),
    EVENINGLIGHT(R.string.eveninglight, R.layout.diet_list),
    DINNER(R.string.dinner,R.layout.diet_list);

    private int mTitleResId;
    private int mLayoutResId;

    CustomPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}

public class DietPagerAdapter extends FragmentPagerAdapter {

    Context mContext;
    List<DietData> data;

    public DietPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
