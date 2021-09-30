package com.example.myapplication.common.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.fragments.EjercitacionFragment;
import com.example.myapplication.fragments.EvaluacionFragment;


public class PageAdapter extends FragmentPagerAdapter {
    private int numbOfTabs;

    public PageAdapter(FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.numbOfTabs = numbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new EjercitacionFragment();
            case 1:
                return new EvaluacionFragment();
            default:
                return null;
        }
    }




    @Override
    public int getCount() {
        return numbOfTabs;
    }
}
