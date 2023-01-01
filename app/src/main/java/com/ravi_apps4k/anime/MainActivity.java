package com.ravi_apps4k.anime;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.ads.Ad;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ravi_apps4k.anime.ui.main.ScreenSlidePagerAdapter;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;
import static com.ravi_apps4k.anime.PublicMethods.getListOfImages;
import static com.ravi_apps4k.anime.PublicMethods.isUpdateAvailable;
import static com.ravi_apps4k.anime.PublicMethods.makeFolder;
import static com.ravi_apps4k.anime.PublicMethods.saveToInternalStorage;


public class MainActivity extends AppCompatActivity implements CallBackInterface {
    FragmentManager fragmentManager;
    public static final int STORAGE_PERMISSION_CODE = 101;
    public static LinearLayout linearLayout;
    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    int couter = 0;
    public static Context context;
    public static String result= "";
    public static RecyclerView recyclerView;
    Intent intent;
    String playListId;
//    UnifiedNativeAd unifiedNativeAd;
public  static Toolbar toolbar;

    List<Object> itemList;
//    List<UnifiedNativeAd> unifiedNativeAdsList;
    static  final int NO_OF_ADS = 2;
    int sizeOfitemList;
    public static TextView loadingtext;
    public static Dialog myDialog;
    public static ProgressBar progressBar;
    public static Window window;
    public static Fragment homeFragment;
    public static Fragment downloadsFragment;
    public static Fragment categoryFragment;
    public  static Boolean per = false;

     int sigma = 0;
     boolean showads = true;

    ViewPager2 viewPager;
    FragmentActivity fragmentActivity;
    TabLayout tabs ;
    SwipeRefreshLayout mySwipeRefreshLayout;
    ScreenSlidePagerAdapter screenSlidePagerAdapter ;


    private final String TAG = MainActivity.class.getSimpleName();


    public void ShowPopup() {

        showads = false;

        Button later;
        Button exit;
        Button rate;
        final ImageView one;
        final ImageView two;
        final ImageView three;
        final ImageView four;
        final ImageView five;

        myDialog.setContentView(R.layout.on_exit_popup);
        later=(Button)myDialog.findViewById(R.id.later);
        exit =(Button)myDialog.findViewById(R.id.exit);
        rate =(Button)myDialog.findViewById(R.id.rate);
        one =(ImageView)myDialog.findViewById(R.id.one);
        two =(ImageView)myDialog.findViewById(R.id.two);
        three =(ImageView)myDialog.findViewById(R.id.three);
        five =(ImageView)myDialog.findViewById(R.id.five);
        four =(ImageView)myDialog.findViewById(R.id.four);



        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setImageResource(R.drawable.ic_round_star_24);
                two.setImageResource(R.drawable.ic_round_star_border_24);
                three.setImageResource(R.drawable.ic_round_star_border_24);
                four.setImageResource(R.drawable.ic_round_star_border_24);
                five.setImageResource(R.drawable.ic_round_star_border_24);
                rateThisApp();

            }
        });
      two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setImageResource(R.drawable.ic_round_star_24);
                two.setImageResource(R.drawable.ic_round_star_24);
                three.setImageResource(R.drawable.ic_round_star_border_24);
                four.setImageResource(R.drawable.ic_round_star_border_24);
                five.setImageResource(R.drawable.ic_round_star_border_24);
                rateThisApp();
            }
        });
      three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                one.setImageResource(R.drawable.ic_round_star_24);
                two.setImageResource(R.drawable.ic_round_star_24);
                three.setImageResource(R.drawable.ic_round_star_24);
                four.setImageResource(R.drawable.ic_round_star_border_24);
                five.setImageResource(R.drawable.ic_round_star_border_24);
                rateThisApp();

            }
        });
      four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setImageResource(R.drawable.ic_round_star_24);
                two.setImageResource(R.drawable.ic_round_star_24);
                three.setImageResource(R.drawable.ic_round_star_24);
                four.setImageResource(R.drawable.ic_round_star_24);
                five.setImageResource(R.drawable.ic_round_star_border_24);
                rateThisApp();


            }
        });
      five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setImageResource(R.drawable.ic_round_star_24);
                two.setImageResource(R.drawable.ic_round_star_24);
                three.setImageResource(R.drawable.ic_round_star_24);
                four.setImageResource(R.drawable.ic_round_star_24);
                five.setImageResource(R.drawable.ic_round_star_24);
                rateThisApp();


            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateThisApp();
                myDialog.dismiss();

            }
        });

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                finish();
            }
        });

//        retry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isNetworkConnected()){
//                    startActivity(i);
//                    myDialog.dismiss();
//                    finish();
//                } else {
//                    Toast.makeText(Splash.this,"no internet access",Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        showads= true;
        Log.d(TAG, "onResume: "+showads);

        super.onResume();
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");



    }

    @Override
    protected void onStart() {
        Log.d("TAG", "onCreate: mainactiviity started");
        Log.d("TAG", "onCreate: update: "+isUpdateAvailable);
//        writeToFile("hello","audi"+".json", MainActivity.this);
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
            return;
        }
        else {

            ShowPopup();

        }
    }


    private void loadBannerAds() {
         AdView adView = new com.facebook.ads.AdView(this, context.getResources().getString(R.string.banner_home), AdSize.BANNER_HEIGHT_90);
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
        setContentView(R.layout.activity_main2);
        context = this;

        // get storage permission

        fragmentManager = getSupportFragmentManager();

        checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE);


        String ID = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d("deviceid", "deviceid: "+ ID);
        Log.d("deviceid", "deviceid: ");



        // firebase stuff



        myDialog = new Dialog(this);
        AudienceNetworkAds.initialize(context);

//        loadInterstitial();
        loadBannerAds();



        fragmentActivity =this;

        viewPager = findViewById(R.id.view_pager);
        screenSlidePagerAdapter = new ScreenSlidePagerAdapter( fragmentActivity);
        viewPager.setAdapter(screenSlidePagerAdapter);
        viewPager.setCurrentItem(1);



        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, findViewById(R.id.view_pager),
                (tab, position) -> tab.setIcon(R.drawable.ic_round_favorite_24 )
        ).attach();




        tabs.getTabAt(0).setIcon(R.drawable.ic_round_favorite_muted);
        tabs.getTabAt(1).setIcon(R.drawable.ic_round_home_24);
        tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_apps_muted);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tabs.getTabAt(tab.getPosition()).setIcon(R.drawable.ic_round_favorite_24);
                switch (tab.getPosition()){
                    case 0: tabs.getTabAt(0).setIcon(R.drawable.ic_round_favorite_24);
                        tabs.getTabAt(1).setIcon(R.drawable.ic_round_home_muted);
                        tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_apps_muted);
                        break;
                    case 1: tabs.getTabAt(1).setIcon(R.drawable.ic_round_home_24);
                        tabs.getTabAt(0).setIcon(R.drawable.ic_round_favorite_muted);
                        tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_apps_muted);
                        break;
                    case 2: tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_apps_24);
                        tabs.getTabAt(0).setIcon(R.drawable.ic_round_favorite_muted);
                        tabs.getTabAt(1).setIcon(R.drawable.ic_round_home_muted);
                        break;
                }
                Log.d(TAG, "onTabSelected: "+ tab.getPosition());
                Log.d(TAG, "onTabSelected: "+ tab.getIcon());
                Log.d(TAG, "onTabSelected: "+ tab.getTag());
                Log.d(TAG, "onTabSelected: "+ tab.getText());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        mySwipeRefreshLayout= findViewById(R.id.swiperefresh);
//        mySwipeRefreshLayout.setEnabled(false);
        mySwipeRefreshLayout.setEnabled(viewPager.getScrollState() != SCROLL_STATE_DRAGGING);


        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("LOG_TAG", "onRefresh called from SwipeRefreshLayout");


                        // randomise the itemlist .. here
//
                        int cur = viewPager.getCurrentItem();
                        screenSlidePagerAdapter = new ScreenSlidePagerAdapter( fragmentActivity);
                        viewPager.setAdapter(screenSlidePagerAdapter);
                        viewPager.setCurrentItem(cur);


                        TabLayout tabs = findViewById(R.id.tabs);
                        new TabLayoutMediator(tabs, findViewById(R.id.view_pager),
                                (tab, position) -> tab.setIcon(R.drawable.ic_round_favorite_24 )
                        ).attach();

                        viewPager.setOffscreenPageLimit(3);

                        tabs.getTabAt(0).setIcon(R.drawable.ic_round_favorite_muted);
                        tabs.getTabAt(1).setIcon(R.drawable.ic_round_home_24);
                        tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_apps_muted);

                        switch (tabs.getSelectedTabPosition()){

                            case 0: tabs.getTabAt(0).setIcon(R.drawable.ic_round_favorite_24);
                                tabs.getTabAt(1).setIcon(R.drawable.ic_round_home_muted);
                                tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_apps_muted);
                                break;
                            case 1: tabs.getTabAt(1).setIcon(R.drawable.ic_round_home_24);
                                tabs.getTabAt(0).setIcon(R.drawable.ic_round_favorite_muted);
                                tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_apps_muted);
                                break;
                            case 2: tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_apps_24);
                                tabs.getTabAt(0).setIcon(R.drawable.ic_round_favorite_muted);
                                tabs.getTabAt(1).setIcon(R.drawable.ic_round_home_muted);

                                break;

                        }


                        mySwipeRefreshLayout.setRefreshing(false);

                    }
                }
        );





//
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrollStateChanged(int state) {
                toggleRefreshing(state == ViewPager.SCROLL_STATE_IDLE);
            }
        });



        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_listt);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
          this,drawer,toolbar,R.string.nav_open,R.string.nav_close);
        toggle.setDrawerIndicatorEnabled(false);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                 R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.openDrawer(Gravity.LEFT);
            }
        });



        linearLayout = findViewById(R.id.fab);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                showads = false;

                switch (item.getItemId()){

                    case R.id.nav_left_share:
                        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.splash_screen);
                        saveToInternalStorage(MainActivity.this,bitmap,"share","",false,false,true,"share");
                        drawer.close();
                        break;


                    case R.id.nav_left_feed:
                        String[] addresses = { getString(R.string.my_email)};
                        String subject = getString(R.string.app_name);
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                            startActivity(intent);

                        drawer.close();
                        break;

                    case R.id.nav_left_exit:
                        drawer.close();
                        break;

                    case R.id.clear_cache:
                        deleteCache(MainActivity.this);
                         Toast.makeText(MainActivity.this, "cache cleared!", Toast.LENGTH_SHORT).show();
                         drawer.close();
                        break;
//

                    case R.id.more_apps:
                        String myDeveloperId = "https://play.google.com/store/apps/developer?id=4k Hd Wallpaper for Android";
                        String devid = "7260052955541103935";

                        Intent more = new Intent(Intent.ACTION_VIEW);
                        more.setData(Uri.parse(
                                "https://play.google.com/store/apps/dev?id="+devid));
                        more.setPackage("com.android.vending");
                        startActivity(more);


//                        try {
//                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:"+devid)));
//                        } catch (android.content.ActivityNotFoundException anfe) {
//                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id="+devid)));
//                        }
                        drawer.close();
                        break;


                    case R.id.nav_left_rate:
                        Intent rate = new Intent(Intent.ACTION_VIEW);
                        rate.setData(Uri.parse(
                                "https://play.google.com/store/apps/details?id="+getPackageName()));
                        rate.setPackage("com.android.vending");
                        startActivity(rate);
                        drawer.close();
                        break;


                    case R.id.super_hero:
                        String heros = "com.Super.hero";
                        Intent hero = new Intent(Intent.ACTION_VIEW);
                        hero.setData(Uri.parse(
                                "https://play.google.com/store/apps/details?id="+heros));
                        hero.setPackage("com.android.vending");
                        startActivity(hero);
                        drawer.close();
                        break;

                    case R.id.artistic:
                        String animes = "com.ravi_apps4k.artistic";
                        Intent anime = new Intent(Intent.ACTION_VIEW);
                        anime.setData(Uri.parse(
                                "https://play.google.com/store/apps/details?id="+animes));
                        anime.setPackage("com.android.vending");
                        startActivity(anime);
                        drawer.close();
                        break;

//                    case R.id.sketchy:
//                        String sketchys = "com.ravi_apps4k.sketchy";
//                        Intent sketchy = new Intent(Intent.ACTION_VIEW);
//                        sketchy.setData(Uri.parse(
//                                "https://play.google.com/store/apps/details?id="+sketchys));
//                        sketchy.setPackage("com.android.vending");
//                        startActivity(sketchy);
//                        drawer.close();
//                        break;

                    case R.id.car:
                        String artistics = "com.photoHD4k.carr";
                        Intent artistic = new Intent(Intent.ACTION_VIEW);
                        artistic.setData(Uri.parse(
                                "https://play.google.com/store/apps/details?id="+artistics));
                        artistic.setPackage("com.android.vending");
                        startActivity(artistic);
                        drawer.close();
                        break;

                }
                return false;
            }
        });




    }


//
//    private void loadBannerAds() {
//        AdView mAdView = findViewById(R.id.adView1);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//    }



    public void toggleRefreshing(boolean enabled) {
        if (mySwipeRefreshLayout != null) {
            mySwipeRefreshLayout.setEnabled(enabled);
        }
    }



    private void addDummyNativeAdstoItemList() {
     /*             /// ad ads  after 100+ downloads

        Log.d(TAG, ": size of itemList:"+itemList.size());

        for (int i = 4; i<sizeOfitemList;i=i+4,sizeOfitemList++){
                itemList.add(i,new DummyAd());
                Log.d(TAG, "addDummyNativeAdstoItemList: size of itemList loop: "+i+" size: "+itemList.size());
        }

        for (int i= 0; i<itemList.size();i++){
            Log.d(TAG, "addDummyNativeAdstoItemList: position: "+i+" items: "+itemList.get(i).getClass());
        }
        */

        //adapt(context,itemList);
    }


//    private void addNativeAdstoItemList() {
//        Log.d(TAG, "addNativeAdstoItemList: size of itemList:"+itemList.size());
//
//        for (int i = 4; i<sizeOfitemList;i=i+4,sizeOfitemList++){
//            if (!unifiedNativeAdsList.isEmpty()){
//                itemList.add(i,unifiedNativeAdsList.get(0));
//                Log.d(TAG, "addNativeAdstoItemList: size of itemList loop: "+i+" size: "+itemList.size());
//
//            }
//        }
//        adapt(context,itemList);
//    }


    public  static void adapt(Context context, List<Object> itemList){
//
//        final MyPlayListItemAdapter myAdapter = new MyPlayListItemAdapter(context,itemList,fragmentManager);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(myAdapter);


    }


    public  static void showLoading() {
        myDialog = new Dialog(MainActivity.context);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.setContentView(R.layout.loading_popup);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        progressBar = (ProgressBar) myDialog.findViewById(R.id.progressBar);
        loadingtext = (TextView) myDialog.findViewById(R.id.loading_text);

    }






    // menu items onlclick








    public void rateThisApp() {
        String myYoutubeLInk = "https://play.google.com/store/apps/details?id=" + getPackageName();
        Uri webpage = Uri.parse(myYoutubeLInk);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void youutbe(MenuItem item) {
        String myYoutubeLInk = "https://www.youtube.com/channel/UCrf3lKqm_L5iXihMnBhosHg";
        Uri webpage = Uri.parse(myYoutubeLInk);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void more(MenuItem item) {
        String myDeveloperId = "https://play.google.com/store/apps/developer?id=4k Hd Wallpaper for Android";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(myDeveloperId)));
    }



    public void disclaimer(MenuItem item) {

        startActivity(new Intent(this, Credits.class));
    }


    public void composeEmail( MenuItem view) {
        String[] addresses = { getString(R.string.my_email)};
        String subject = getString(R.string.app_name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void whatsapp(MenuItem item) {
        String myYoutubeLInk = "https://chat.whatsapp.com/GjSyDZ6TcrXD4ZXFAffUQy";
        Uri webpage = Uri.parse(myYoutubeLInk);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }





    public void checkPermission(String permission, int requestCode)

    {



        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                MainActivity.this,
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
        else {

            makeFolder();
            getListOfImages();
            per = true;

           // Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);


        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        "Storage Permission Granted",


                        Toast.LENGTH_SHORT)
                        .show();
                makeFolder();
                getListOfImages();
                per = true;
            }
            else {
                Toast.makeText(MainActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
                per = false;
            }
        }
    }




    // clear cache



    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }


    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }




    @Override
    public void CallBackMethod() {

        Toast.makeText(this,"call back called",Toast.LENGTH_SHORT).show();

    }
}




