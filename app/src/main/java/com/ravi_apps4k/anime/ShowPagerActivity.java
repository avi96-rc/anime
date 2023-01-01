package com.ravi_apps4k.anime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.ravi_apps4k.anime.fragments.pagerFragment;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.ravi_apps4k.anime.PublicMethods.*;

public class ShowPagerActivity extends AppCompatActivity {

    int pos;
    String fragName;
    String id;
    public  static Context showPagerContext;
    public static Dialog myDialog;
    public static TextView loadingtext;
    public static ProgressBar progressBar;
    public static Window window;
    List<Object> items;
    public  int position = 0;


    private void loadBannerAds() {
        AdView adView = new com.facebook.ads.AdView(this, getResources().getString(R.string.show_banner), AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        FrameLayout adContainer = (FrameLayout) findViewById(R.id.ad_view_container1);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();
    }

//    private void loadInterstitial() {
//
////      AdRequest adRequest = new AdRequest.Builder().build();
//        Log.d("TAG", "loadInterstitial: ");
//
//        InterstitialAd.load(this,showPagerContext.getResources().getString(R.string.category_interstitial),
//                new AdRequest.Builder().build(),
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                        // The mInterstitialAd reference will be null until
//                        // an ad is loaded.
//
//                        mInterstitialAd = interstitialAd;
//                        Log.i("TAG", "onAdLoaded");
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error
//                        Log.i("error loading", loadAdError.getMessage());
////                        mInterstitialAd = null;
//                    }
//
//                });
//
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pager);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showPagerContext = this;

        loadBannerAds();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");                       // get position of this id in pagerItemlist
        fragName = intent.getStringExtra("fragName");
        Log.d("TAG", "onCreate: "+fragName);

        items = getItemlist(showPagerContext,fragName);
        Collections.shuffle(items,new Random(seed));


        switch (fragName){
           case "favourite":
               items = loadFavouritesFromJson(showPagerContext);
               Collections.shuffle(items,new Random(seed));
                 break;
//            default:
//               items = getItemlist(showPagerContext,fragName);
//               Collections.shuffle(items,new Random(seed));

       }




        Log.d("TAG", "onCreate: position:  "+ position +" id :"+ id);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        pagerFragment pagerFragment = new pagerFragment(position,id,fragmentManager,fragName, items);
        // frag1.setCallBackInterface(MainActivity.this);
        fragmentTransaction.replace(R.id.fragment_container, pagerFragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        Log.d("TAG", "onCreate: size of favouriteListfromJson: "+ PublicMethods.favouriteListfromJson);

    }



    public  static void showLoading() {
        myDialog = new Dialog(showPagerContext);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.setContentView(R.layout.loading_popup);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        progressBar = (ProgressBar) myDialog.findViewById(R.id.progressBar);
        loadingtext = (TextView) myDialog.findViewById(R.id.loading_text);

    }

}