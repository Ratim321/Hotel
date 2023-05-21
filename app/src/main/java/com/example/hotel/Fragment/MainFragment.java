package com.example.hotel.Fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hotel.R;
import com.example.hotel.databinding.ActivityMainFragmentBinding;
import com.example.hotel.model.Fragmentadapter;
import com.google.android.material.tabs.TabLayout;

public class MainFragment extends AppCompatActivity {

   ViewPager pager;
   TabLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
       pager=findViewById(R.id.viewpager);
       layout=findViewById(R.id.tablayout);
       pager.setAdapter(new Fragmentadapter(getSupportFragmentManager())) ;

       layout.setupWithViewPager(pager);
    }
}