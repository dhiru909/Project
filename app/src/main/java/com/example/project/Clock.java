package com.example.project;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import com.example.project.databinding.ActivityClockBinding;

public class Clock extends AppCompatActivity {

private ActivityClockBinding binding;
private TabLayout tabLayout;
private ViewPager2 viewPager2;
private ClockPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClockBinding.inflate(getLayoutInflater());setContentView(binding.getRoot());
        tabLayout = binding.tabLayout;
        viewPager2 = binding.viewPager;
        adapter = new ClockPageAdapter(getSupportFragmentManager(), getLifecycle()) ;
        tabLayout.addTab(tabLayout.newTab().setText("Timer"));
        tabLayout.addTab(tabLayout.newTab().setText("Stop watch"));

        viewPager2.setAdapter(adapter);
//        viewPager2.setAnimation();
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab!=null){
                    viewPager2.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}