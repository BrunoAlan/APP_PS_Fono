package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.fragments.EjercitacionFragment;
import com.example.myapplication.fragments.EvaluacionFragment;
import com.example.myapplication.fragments.PracticaFragment;

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
                return new PracticaFragment();
            case 1:
                return new EjercitacionFragment();
            case 2:
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
