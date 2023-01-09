package com.example.fakecall.Adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fakecall.CharacterActivity;
import com.example.fakecall.Fagment.HistoryFragment;
import com.example.fakecall.Fagment.HomeFragment;
import com.example.fakecall.MainActivity;

public class ViewPageAdapter extends FragmentStateAdapter {
    public  static  int chung  ;
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public  Fragment createFragment(int position) {
        chung = position ;
        Log.e(" Tab","" + chung) ;
        switch (chung)
        {
            case 0 :
                return new MainActivity();
            case 1 :
                return new CharacterActivity();
            default:
                return new MainActivity() ;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
