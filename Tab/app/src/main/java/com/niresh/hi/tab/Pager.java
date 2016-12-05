package com.niresh.hi.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by hi on 10/21/2016.
 */

public class Pager extends FragmentStatePagerAdapter {
    public Pager(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Home tab1 = new Home();
                return tab1;
            case 1:
               Summary tab2 = new Summary();
                return tab2;
            case 2:
                Report tab3 = new Report();
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
