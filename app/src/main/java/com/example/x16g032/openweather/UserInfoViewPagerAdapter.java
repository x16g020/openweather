package com.example.x16g032.openweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UserInfoViewPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_NUM = 2;

    public UserInfoViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new WeatherFragment();
                break;
            default:
                fragment = new WeatherInfoFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

}