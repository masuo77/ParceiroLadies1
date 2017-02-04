package com.example.masuo.parceiroladiesrespectbook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Masuo on 2017/01/21.
 */

public class PlayerInfoFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String LOG = "FragmentPagerAdapter";

    private PlayerInfoItem playerInfoItem;
    private Boolean leaving = false;

    public PlayerInfoFragmentPagerAdapter(FragmentManager fm, PlayerInfoItem playerInfoItem, Boolean leaving) {
        super(fm);

        this.playerInfoItem = playerInfoItem;
        this.leaving = leaving;
    }

    String[] title = {"選手情報", "入団情報", "退団情報"};

    @Override
    public Fragment getItem(int position) {

        Log.i(LOG, "getItem " + position);

        switch (position) {
            case 0:
                return PlayerInfoMainFragment.newInstance(playerInfoItem, "");
            case 1:
                return PlayerInfoJoiningFragment.newInstance(playerInfoItem, "");
            case 2:
            default:
                return PlayerInfoLeavingFragment.newInstance(playerInfoItem, "");
        }
    }

    @Override
    public int getCount() {
        return (leaving) ? 3 : 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position]; //"Page " + position;  //super.getPageTitle(position);
    }

}
