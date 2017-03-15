package com.example.masuo.parceiroladiesrespectbook.StaffInfo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Masuo on 2017/01/21.
 */

public class StaffInfoFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String LOG = "FragmentPagerAdapter";

    private StaffInfoItem staffInfoItem;
    private Boolean comment = false;

    public StaffInfoFragmentPagerAdapter(FragmentManager fm, StaffInfoItem staffInfoItem, Boolean comment) {
        super(fm);

        this.staffInfoItem = staffInfoItem;
        this.comment = comment;
    }

    String[] title = {"情報", "コメント"};

    @Override
    public Fragment getItem(int position) {

        Log.i(LOG, "getItem " + position);

        switch (position) {
            case 0:
                return StaffInfoMainFragment.newInstance(staffInfoItem, "");
            case 1:
            default:
                return StaffInfoSeasonFragment.newInstance(staffInfoItem, "");
        }
    }

    @Override
    public int getCount() {
        return (comment) ? 2 : 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position]; //"Page " + position;  //super.getPageTitle(position);
    }

}
