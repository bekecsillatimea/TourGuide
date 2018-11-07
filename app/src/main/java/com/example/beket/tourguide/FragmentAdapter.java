package com.example.beket.tourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter{

    private String tabTitles[] = new String[] { "To Sleep", "To Eat", "To Visit", "To Do" };

    FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ToSleepFragment();
        } else if (position == 1) {
            return new ToEatFragment();
        } else if (position == 2) {
            return new ToVisitFragment();
        } else {
            return new ToDoFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
