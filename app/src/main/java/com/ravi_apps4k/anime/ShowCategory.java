package com.ravi_apps4k.anime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.ravi_apps4k.anime.fragments.HomeFrag;

public class ShowCategory extends AppCompatActivity {

    private static final String TAG = "";
    int position;
    String categoryName;
    String id;
    public  static Context showCategoryContext;
    public static Window window;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onPause() {
//        Log.d(TAG, "onPause: ");
//        if (mInterstitialAd != null) {
//            mInterstitialAd.show((Activity)showCategoryContext);
//            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
//                @Override
//                public void onAdDismissedFullScreenContent() {
//                    // Called when fullscreen content is dismissed.
//                    Log.d("TAG", "The ad was dismissed.");
//                    loadInterstitial();
//                }
//
//                @Override
//                public void onAdFailedToShowFullScreenContent(AdError adError) {
//                    // Called when fullscreen content failed to show.
//                    Log.d("TAG", "The ad failed to show.");
//                }
//
//                @Override
//                public void onAdShowedFullScreenContent() {
//                    // Called when fullscreen content is shown.
//                    // Make sure to set your reference to null so you don't
//                    // show it a second time.
//                    mInterstitialAd = null;
//                    Log.d("TAG", "The ad was shown.");
//                }
//            });
//
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }



        super.onPause();
    }

    private void loadBannerAds() {


        AdView adView = new com.facebook.ads.AdView(this, getResources().getString(R.string.banner_category), AdSize.BANNER_HEIGHT_90);
        // Find the Ad Container
        FrameLayout adContainer = (FrameLayout) findViewById(R.id.ad_view_container1);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showCategoryContext = this;
        Intent intent = getIntent();
        categoryName = intent.getStringExtra("category_name");
        setTitle("   "+categoryName);

        loadBannerAds();



        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        HomeFrag homeFrag = new HomeFrag(this,categoryName,mInterstitialAd);
        // frag1.setCallBackInterface(MainActivity.this);
        fragmentTransaction.replace(R.id.fragment_container,homeFrag);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}