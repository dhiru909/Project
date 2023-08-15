package com.example.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ClockPageAdapter extends FragmentStateAdapter {
    public ClockPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
         if(position == 0){
            return new Timer();
        }
        else{
            return new StopWatch();
        }
    }
}
