package com.example.hotel.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hotel.Fragment.FirstFragment;
import com.example.hotel.Fragment.SecondFragment;


public class Fragmentadapter extends FragmentPagerAdapter {


    public Fragmentadapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public Fragmentadapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:return new FirstFragment();
//            case 1:return new SecondFragment();


            default: return new FirstFragment();


        }

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String  title=null;
        if(position==0){
            title="Booked";
        }
//        if(position==1){
//            title="Cancelled";
//        }


        return title;
    }
}
