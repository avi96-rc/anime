package com.ravi_apps4k.anime.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import static com.ravi_apps4k.anime.MainActivity.context;
import static com.ravi_apps4k.anime.MainActivity.toolbar;

import com.facebook.ads.InterstitialAd;
import com.ravi_apps4k.anime.fragments.CategoryFrag;
import com.ravi_apps4k.anime.fragments.FavouriteFrag;
import com.ravi_apps4k.anime.fragments.HomeFrag;

// A simple ViewPager adapter class for paging through fragments


// An equivalent ViewPager2 adapter class
public class ScreenSlidePagerAdapter extends FragmentStateAdapter {

    int NUM_PAGES = 3;
    InterstitialAd interstitialAd;
    private InterstitialAd mInterstitialAd;

    public ScreenSlidePagerAdapter(FragmentActivity fa ) {
        super(fa);
        this.mInterstitialAd = mInterstitialAd;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new FavouriteFrag(context,mInterstitialAd);
                break;
            case 1: fragment = new HomeFrag(context,"home",mInterstitialAd);
                break;
            case 2: fragment = new CategoryFrag(context);
                break;

        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}