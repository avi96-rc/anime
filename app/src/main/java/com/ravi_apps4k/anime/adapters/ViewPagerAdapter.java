package com.ravi_apps4k.anime.adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ravi_apps4k.anime.DummyAd;
import com.ravi_apps4k.anime.Item;
import com.ravi_apps4k.anime.PublicMethods;
import com.ravi_apps4k.anime.R;
import com.ravi_apps4k.anime.ShowPagerActivity;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;
//import static com.photoHD4k.carr.PublicMethods.favouriteIdfromJson;
import static com.ravi_apps4k.anime.PublicMethods.favouriteIdfromJson;

public class ViewPagerAdapter extends   RecyclerView.Adapter<RecyclerView.ViewHolder>{
    @NonNull

    int  i = 0;
    Context context;
    List<Object> itemList;
  //  int pos;
    String id;
    int il = 0;
    int resHeight;
    int resWidht;
    public int p;
    ViewPager2 viewPager2;
    List<Integer> ads= new ArrayList<>();
    Boolean isDownloaded = false;
    int currentItemPosition;
    LayoutInflater layoutInflater;
    //Bitmap btm;
    FragmentManager fragmentManager;
    private InterstitialAd interstitialAd;

    int pos;
    ProgressDialog dialog = null;
    private com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd;

//    Target target = new Target() {
//        @Override
//        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//            Log.d("TAG", "onBitmapLoaded: bitmap loaded!!! ");
//
////            Log.d("TAG", "onBitmapLoaded: path: "+saveToInternalStorage(bitmap));
//
//        }
//
//        @Override
//        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//            Log.d("TAG", "onBitmapLoaded: bitmap loading failed");
////            img.setImageResource(R.drawable.ic_launcher_background);
//        }
//
//        @Override
//        public void onPrepareLoad(Drawable placeHolderDrawable) {
////            img.setImageResource(R.drawable.ic_android_black_24dp);
//        }
//    };
////    Item item;

    int counter = 0;


    //LayoutInflater layoutInflater;

    private void loadInterstitial(Context context) {

        Log.d(TAG, "loadInterstitial:");

        com.google.android.gms.ads.interstitial.InterstitialAd.load(context,context.getResources().getString(R.string.admob_viewpager_adapter_interstitial),
                new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.

                        mInterstitialAd = interstitialAd;
                        Log.d(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG," error"+ loadAdError.getMessage());
//                        mInterstitialAd = null;
                    }

                });

    }


    public ViewPagerAdapter(Context context, int  position, String id, ViewPager2 viewPager2, FragmentManager fragmentManager, List<Object> itemList){
        this.context = context;
//        this.itemList = itemListPager;
        this.itemList = itemList;
        this.pos = position;
        layoutInflater = LayoutInflater.from(context);
        p =position;
        this.id = id;
        this.viewPager2 = viewPager2;
        resHeight = PublicMethods.getResolution(context)[0];
        resWidht = PublicMethods.getResolution(context)[1];
        this.fragmentManager =fragmentManager;
        Log.d(TAG, "ViewPagerAdapter: pager adapter laoded!");
        //Log.d(TAG, "ViewPagerAdapter: postion: "+ pos);
        Log.d(TAG, "ViewPagerAdapter: id: "+ this.id);
        Log.d(TAG, "ViewPagerAdapter: itemlist size: "+ itemList.size());
        Log.d(TAG, "ViewPagerAdapter: counter: "+counter);

        loadInterstitial(context);



//          TODO: load google ads here



    }



//    private List<Object> loadData() {
//        Data data = new Data(context);
//        int sizeOfItemlist = data.addObjects();
//        Log.d(TAG, "loadData: itemlist size: " +sizeOfItemlist);
//        return data.getItemsList();
//    }

    @Override
    public int getItemViewType(int position)
    {
        if (itemList.get(position) instanceof NativeAd){
            return R.layout.facebook_native_ad_layout_pager ;
        } else

        if(itemList.get(position) instanceof Item)
        {
            return  R.layout.pageview_layout;
        }else if(itemList.get(position) instanceof com.ravi_apps4k.anime.DummyAd)
        {
            return  R.layout.native_ads_layout_sample_pager;
        }

        return  R.layout.pageview_layout;    }





    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.pageview_layout,parent,false);

        switch (viewType){
            case R.layout.pageview_layout:
                view = layoutInflater.inflate(R.layout.pageview_layout,parent,false);
                return new MyViewHolder(view);

            case R.layout.facebook_native_ad_layout_pager:
                view = layoutInflater.inflate(R.layout.facebook_native_ad_layout_pager,parent,false);
                return new AdHolder(view);

            case R.layout.native_ads_layout_sample_pager:
                view = layoutInflater.inflate(R.layout.native_ads_layout_sample_pager,parent,false);
                return new DummyAdHolder(view);

        }

        return new MyViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        // ads

//         for the native ads

        if (position<itemList.size()-5){                    // for loading adding before 9 position ..  pre loading

            if (itemList.get(position+5) instanceof DummyAd ){
                loadNativeAd(position+5);

            }
        }

        if (position<itemList.size()-4){                    // for loading adding before 9 position ..  pre loading

            if (itemList.get(position+4) instanceof DummyAd ){
                loadNativeAd(position+4);

            }
        }

        if (position<itemList.size()-3){                    // for loading adding before 9 position ..  pre loading

            if (itemList.get(position+3) instanceof DummyAd ){
                loadNativeAd(position+3);

            }
        }

        if (position<itemList.size()-2){                    // for loading adding before 9 position ..  pre loading

            if (itemList.get(position+2) instanceof DummyAd ){
                loadNativeAd(position+2);

            }
        }

        Log.d(TAG, "onBindViewHolder: itemList:  "+itemList.get(position).getClass());



        final int ads_after_image = 15;

        boolean showAds = true;
        for (Integer in :ads){
            if (position== in) showAds = false;
        }

        if (position %ads_after_image== 0  && position != 0 && showAds){
            ads.add(position);
            Log.d(TAG, "onBindViewHolder: trying to show ads.. position:"+ position);

//       TODO: show google ads

            if (mInterstitialAd != null) {

                mInterstitialAd.show((Activity)context);
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("TAG", "The ad was dismissed.");
                        loadInterstitial(context);
                    }


                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
                    }
                });

            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

        }




        // check for what holder is instance of


        if (holder instanceof MyViewHolder){


            if (position == pos){
                dialog = ProgressDialog.show(context, "Please Wait",
                        "Loading 4k Image...", true);
            }

            final Item item= (Item) itemList.get(position);
            final Bitmap[] btm = new Bitmap[1];
            final com.squareup.picasso.Callback  callback2 = new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    //do smth when picture is loaded successfully

                    Log.d(TAG, "onSuccess: picasso image loaded");
                    if (position == pos){
                        dialog.dismiss();
                    }
                    Bitmap btt = ((BitmapDrawable)((MyViewHolder) holder).myImage.getDrawable()).getBitmap();
                    btm[0] = btt;
                    ((MyViewHolder) holder).add.animate().alpha(1);
                }


                @Override
                public void onError(Exception e) {
                    //do smth when there is picture loading error
                    Picasso.get().load(item.getUrl()).placeholder(R.drawable.hour_glass)
                            .into(((MyViewHolder) holder).myImage);
                    Log.d(TAG, "onSuccess: picasso image loaded");
                }


            };
            final com.squareup.picasso.Callback  callback = new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    //do smth when picture is loaded successfully

                    Log.d(TAG, "onSuccess: picasso image loaded");
                    if (position == pos){
                        dialog.dismiss();
                    }
                    Bitmap btt = ((BitmapDrawable)((MyViewHolder) holder).myImage.getDrawable()).getBitmap();
                    btm[0] = btt;
                    ((MyViewHolder) holder).add.animate().alpha(1);
               }


                @Override
                public void onError(Exception e) {
                    //do smth when there is picture loading error
                    Picasso.get().load(item.getUrl()).placeholder(R.drawable.hour_glass)
                            .into(((MyViewHolder) holder).myImage,callback2);


                }


            };

            if (pos== position ){
//                dialog.show();
            }


            Picasso.get().load(item.getUrl()).placeholder(R.drawable.hour_glass)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(((MyViewHolder) holder).myImage,callback);
//        Picasso.get().load(item.getUrl()).resize(resWidht,resHeight).onlyScaleDown().placeholder(R.drawable.hour_glass).memoryPolicy(MemoryPolicy.NO_CACHE).into((holder).myImage,callback);

            ((MyViewHolder) holder).myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: image is clicked: "+item.getUrl() );
                }
            });
            Log.d(TAG, "onBindViewHolder: bitmap from imageview: "+ btm[0]);

            //((MyViewHolder) holder).title.setText(item.getId());
//        ((MyViewHolder) holder).title.setText(position+"/"+itemList.size());

            Log.d(TAG, "onBindViewHolder: item id: "+item.getId());

            // check for favourites

            // ((MyViewHolder) holder).checkFavourite(item.getId());

            Log.d(TAG, "\n\ncheckFavourite: current id: "+position +" :"+item.getId());
            Log.d(TAG, "checkFavourite: isfavourite: "+((MyViewHolder) holder).isFavourite);

            for (String items: favouriteIdfromJson){
                if (item.getId().equals(items)){
                    ((MyViewHolder) holder).isFavourite = !((MyViewHolder) holder).isFavourite;
                    Log.d(TAG, "checkFavourite: "+i+" found a favourite> "+item.getId()+" is favourite");
                    // set color of favourite to red...
                    Log.d(TAG, "checkFavourite: "+i+" setting red favourite> ");
                    ((MyViewHolder) holder).favourite.setImageResource(R.drawable.ic_heart_selected);
                    Log.d(TAG, "checkFavourite: "+i+" isfavourite: "+((MyViewHolder) holder).isFavourite);
                    break;
                }
            }



// listener for floating actio bar
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "onClick: button clicked.");
                    switch (v.getId()) {
                        case R.id.add_button:
                            Log.d("TAG", "add button clicked: ");
                            Log.d(TAG, "onClick: counter:"+position+" id: "+item.getId());
                            if (((MyViewHolder) holder).isMenuopen) {
                                ((MyViewHolder) holder).closeMenu();
                            } else {
                                ((MyViewHolder) holder).openMenu();
                            }
                            break;
                        case R.id.quickwallpaper_btn:
                            Log.d("TAG", "quickwallpaper button clicked: ");
                            PublicMethods.saveToInternalStorage(context, btm[0] ,"4k_"+item.getId(),item.getUrl(),true,false,false,"fullhd");
////
//                        ((MyViewHolder) holder).bottomSheetDialogFragment = new BottonSheetFrag(((MyViewHolder) holder).bottomSheetDialogFragment,context, btm[0],item.getId(),item.getUrl(),true,false,false);
//                        ((MyViewHolder) holder).bottomSheetDialogFragment.show(fragmentManager,"bottm sheet");
//                        bottomSheetDialogFragmentList.add(((MyViewHolder) holder).bottomSheetDialogFragment);

                            //PublicMethods.saveToInternalStorage(context, btm ,item.getId(),item.getUrl(),true,false,false);
                            if (((MyViewHolder) holder).isMenuopen) {
                                ((MyViewHolder) holder).closeMenu();
                            } else {
                                ((MyViewHolder) holder).openMenu();
                            }
                            break;
                        case R.id.seta_btn:
                            Log.d("TAG", "setAs button clicked: ");
                            PublicMethods.saveToInternalStorage(context, btm[0] ,"4k_"+item.getId(),item.getUrl(),false,true,false,"fullhd");
////
//                        ((MyViewHolder) holder).bottomSheetDialogFragment = new BottonSheetFrag(((MyViewHolder) holder).bottomSheetDialogFragment,context, btm[0],item.getId(),item.getUrl(),false,true,false);
//                        ((MyViewHolder) holder).bottomSheetDialogFragment.show(fragmentManager,"bottm sheet");
//                        bottomSheetDialogFragmentList.add(((MyViewHolder) holder).bottomSheetDialogFragment);


                            // PublicMethods.saveToInternalStorage(context, btm ,item.getId(),item.getUrl(),false,true,false);
                            if (((MyViewHolder) holder).isMenuopen) {
                                ((MyViewHolder) holder).closeMenu();
                            } else {
                                ((MyViewHolder) holder).openMenu();
                            }
                            break;
                        case R.id.share_btn:
                            Log.d("TAG", "sahre button clicked: ");
                            PublicMethods.saveToInternalStorage(context, btm[0] ,"4k_"+item.getId(),item.getUrl(),false,false,true,"fullhd");
//                        ((MyViewHolder) holder).bottomSheetDialogFragment = new BottonSheetFrag(((MyViewHolder) holder).bottomSheetDialogFragment,context, btm[0],item.getId(),item.getUrl(),false,false,true);
//                        ((MyViewHolder) holder).bottomSheetDialogFragment.show(fragmentManager,"bottm sheet");
//                        bottomSheetDialogFragmentList.add(((MyViewHolder) holder).bottomSheetDialogFragment);

                            //PublicMethods.saveToInternalStorage(context, btm ,item.getId(),item.getUrl(),false,false,true);
                            if (((MyViewHolder) holder).isMenuopen) {
                                ((MyViewHolder) holder).closeMenu();
                            } else {
                                ((MyViewHolder) holder).openMenu();
                            }
                            break;
                        case R.id.donwload_btn:
                            Log.d("TAG", "downlaoad button clicked: ");
                            //checkPermission();
                            PublicMethods.saveToInternalStorage(context, btm[0] ,"4k_"+item.getId(),item.getUrl(),false,false,false,"fullhd");
//
//                        ((MyViewHolder) holder).bottomSheetDialogFragment = new BottonSheetFrag(((MyViewHolder) holder).bottomSheetDialogFragment,context, btm[0],item.getId(),item.getUrl(),false,false,false);
//                        ((MyViewHolder) holder).bottomSheetDialogFragment.show(fragmentManager,"bottm sheet");
//                        bottomSheetDialogFragmentList.add(((MyViewHolder) holder).bottomSheetDialogFragment);

                            //PublicMethods.saveToInternalStorage(context, btm ,item.getId(),item.getUrl(),false,false,false);
                            if (((MyViewHolder) holder).isMenuopen) {
                                ((MyViewHolder) holder).closeMenu();
                            } else {
                                ((MyViewHolder) holder).openMenu();
                            }
                            break;
                        case R.id.favourite_btn:
                            Log.d("TAG", "favourite button clicked: ");
                            //checkPermission();
                            if (((MyViewHolder) holder).isFavourite) {
                                // remove from favourite
                                ((MyViewHolder) holder).isFavourite = !((MyViewHolder) holder).isFavourite;
                                PublicMethods.removeItemsFromfavouriteListfromJson(item.getId(),item.getUrl(),context);
                                ((MyViewHolder) holder).favourite.setImageResource(R.drawable.ic_round_favorite_24);
                                Toast.makeText(context, "removed from favourites", Toast.LENGTH_SHORT).show();


                            }else {
                                ((MyViewHolder) holder).isFavourite = !((MyViewHolder) holder).isFavourite;
                                PublicMethods.addItemsTofavouriteListfromJson(item.getId(),item.getUrl(),context);
                                ((MyViewHolder) holder).favourite.setImageResource(R.drawable.ic_round_favorite_24_red);
                                Toast.makeText(context, "added to favourites", Toast.LENGTH_SHORT).show();

                            }
                            //PublicMethods.saveToInternalStorage(context, btm ,item.getId(),item.getUrl(),false,false,false);
                            if (((MyViewHolder) holder).isMenuopen) {
                                ((MyViewHolder) holder).closeMenu();
                            } else {
                                ((MyViewHolder) holder).openMenu();
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + v.getId());
                    }
                }
            };


            ((MyViewHolder) holder).add.setOnClickListener(onClickListener);
            ((MyViewHolder) holder).quickWall.setOnClickListener(onClickListener);
            ((MyViewHolder) holder).setAs.setOnClickListener(onClickListener);
            ((MyViewHolder) holder).download.setOnClickListener(onClickListener);
            ((MyViewHolder) holder).share.setOnClickListener(onClickListener);
            ((MyViewHolder) holder).favourite.setOnClickListener(onClickListener);

////        (holder).myImage.setOnTouchListener(new ImageMatrixTouchHandler(context));
//            viewPager.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_MOVE:
//                            mySwipeRefreshLayout.setEnabled(false);
//                            break;
//                        case MotionEvent.ACTION_UP:
//                            break;
//                        case MotionEvent.Action:
//                        case MotionEvent.ACTION_CANCEL:
//                            mySwipeRefreshLayout.setEnabled(true);
//                            break;
//                    }
//                    return false;
//                }
//            });

            ((MyViewHolder) holder).myImage.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d(TAG, "onLongClick: long press");
                    // TODO: show lock screen view here
                    ((MyViewHolder) holder).showLockScreen();
                    ((MyViewHolder) holder).hideControls();

                    // TODO: vibrate when user long press
                    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    int timee = 200; // milliseconds
// Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(timee, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26

                        vibrator.vibrate(timee);
                    }

                    return true;
                }
            });

            ((MyViewHolder) holder).myImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_UP:
                            Log.d(TAG, "onTouch: touch released ");
                            // TODO: hide lock screen view here
                            ((MyViewHolder) holder).hideLockScreen();
                            ((MyViewHolder) holder).showControls();

                            break;

                        case MotionEvent.ACTION_CANCEL:
                            Log.d(TAG, "onTouch: touch released ");
                            // TODO: hide lock screen view here
                            ((MyViewHolder) holder).hideLockScreen();
                            ((MyViewHolder) holder).showControls();

                            break;
                    }


//                Log.d(TAG, "onTouch: "+event.getAction());
                    if (event.getAction() == 0) {
                        //your code
                        Log.d(TAG, "onTouch: image touched: "+i++);
                        Log.d(TAG, "onTouch: image touched: "+ item.getUrl());

                        // collapse fab on next item.
                        if (((MyViewHolder) holder).isMenuopen){
                            ((MyViewHolder) holder).closeMenu();
                            Log.d(TAG, "onTouch: image id: "+item.getId());
                        }

                    }


                    return false;
                }
            });


            //    Picasso.get().load("https://images.unsplash.com/photo-1572805249460-37b745d80509?ixlib=rb-1.2").placeholder(R.drawable.cat).into((holder).myImage);


            ((MyViewHolder) holder).title.setText(position+1+"/"+itemList.size());
            Log.d(TAG, "onBindViewHolder: position on title: "+position);



        }

        else if(holder instanceof AdHolder){
            Log.d(TAG, "onBindViewHolder:position"+position);
            Log.d(TAG, "onBindViewHolder: itemList:  "+itemList.get(position).getClass());
            final NativeAd nativeAd =  (NativeAd) itemList.get(position);
            nativeAd.unregisterView();

            AdHolder adHolder = (AdHolder) holder;

            adHolder.tvAdTitle.setText(nativeAd.getAdvertiserName());
            adHolder.tvAdBody.setText(nativeAd.getAdBodyText());
            adHolder.tvAdSocialContext.setText(nativeAd.getAdSocialContext());
            adHolder.tvAdSponsoredLabel.setText(R.string.sponsored);
            adHolder.btnAdCallToAction.setText(nativeAd.getAdCallToAction());
            adHolder.btnAdCallToAction.setVisibility(
                    nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, adHolder.nativeAdLayout);
            adHolder.adChoicesContainer.removeAllViews();
            adHolder.adChoicesContainer.addView(adOptionsView, 0);

            //a list of clickable views

//            List<View> clickableViews = new ArrayList<>();
//            clickableViews.add(adHolder.tvAdTitle);
//            clickableViews.add(adHolder.btnAdCallToAction);
//            nativeAd.registerViewForInteraction(
//                    adView,
//                    nativeAdMedia,
//                    nativeAdIcon,
//                    clickableViews);


            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(adHolder.ivAdIcon);
            clickableViews.add(adHolder.mvAdMedia);
            clickableViews.add(adHolder.btnAdCallToAction);
            nativeAd.registerViewForInteraction(
                    adHolder.nativeAdLayout, adHolder.mvAdMedia, adHolder.ivAdIcon, clickableViews);








            // ((AdHolder)holder).name.setText(ads.getAdsText());
            // set the nativeAd to layout here...
            //((AdHolder)holder).template.setNativeAd(unifiedNativeAd);

        }

        else if(holder instanceof DummyAdHolder){
            Log.d(TAG, "onBindViewHolder:Dummy nativeAd"+position);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
//        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public  BottomSheetDialogFragment bottomSheetDialogFragment;
        TextView title;
        TextView date;
        TextView time;
        FrameLayout phone;
        FrameLayout camera;
        LinearLayout unlock;
        LinearLayout bar;
        LinearLayout neelam;
        ImageView myImage;
        boolean isFavourite = false;


        public   FloatingActionButton add,setAs,quickWall,share,download,favourite;
        public  FrameLayout frameLayout1,frameLayout3,frameLayout4, frameLayout5,frameLayout2;
        public Float translationYY = 300f;
        public float translationY = 212f;
        public Float translationX= 300f;
        public  int duration =700;
        public  float rotate =0f;
        public  int durationExtended =700;
        public  int linesdurationOpen =700;
        public  int linesdurationExit =100;
        public Boolean isMenuopen = false;
        public OvershootInterpolator overshootInterpolator = new OvershootInterpolator();



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.counter);
            myImage = itemView.findViewById(R.id.page_imageView);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
            phone = itemView.findViewById(R.id.phone);
            camera = itemView.findViewById(R.id.camera);
            bar = itemView.findViewById(R.id.bar);
            unlock = itemView.findViewById(R.id.unlock);
            neelam = itemView.findViewById(R.id.neelam);

            initFabMenu();



        }

        private  void showLockScreen(){
                time.animate().alpha(1);
                date.animate().alpha(1);
                phone.animate().alpha(1);
                camera.animate().alpha(1);
                unlock.animate().alpha(1);
                bar.animate().alpha(1);
                neelam.animate().alpha(1);
                setDAteTime();
        }

        private  void hideLockScreen(){
                time.animate().alpha(0);
                date.animate().alpha(0);
                phone.animate().alpha(0);
                camera.animate().alpha(0);
                unlock.animate().alpha(0);
                bar.animate().alpha(0);
                neelam.animate().alpha(0);
        }

       private  void hideControls(){
                add.animate().alpha(0);
                title.animate().alpha(0);
        }

       private  void showControls(){
                add.animate().alpha(1);
                title.animate().alpha(1);
        }

       private  void setDAteTime(){

           DateFormat timee = new SimpleDateFormat("mm");


           Calendar cal = Calendar.getInstance();
           int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

           int dayOfWeekInt = cal.get(Calendar.DAY_OF_WEEK);
           int monthInt = cal.get(Calendar.MONTH);

           if (dayOfWeekInt>6) dayOfWeekInt = 6;
           if (monthInt>11) monthInt = 11;

           int hour = cal.get(Calendar.HOUR);
           int minute = cal.get(Calendar.MINUTE);

//           System.out.println(time.format(cal.getTime()));



           String daysList[] = { "Sat","Sun", "Mon", "Tue", "Wed",
                   "Thursday", "Friday"};
           String monthslist[]  = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


//           String date =toString();
           time.setText(hour+":"+timee.format(cal.getTime()));
           date.setText(daysList[dayOfWeekInt]+", "+monthslist[monthInt]+" "+dayOfMonth);



       }


        private void initFabMenu() {

            add = itemView.findViewById(R.id.add_button);
            quickWall = itemView.findViewById(R.id.quickwallpaper_btn);
            share = itemView.findViewById(R.id.share_btn);
            setAs = itemView.findViewById(R.id.seta_btn);
            download = itemView.findViewById(R.id.donwload_btn);
            favourite = itemView.findViewById(R.id.favourite_btn);
            frameLayout1 = itemView.findViewById(R.id.horizontal_left);
            frameLayout2 = itemView.findViewById(R.id.horizontal_right);
            frameLayout3 = itemView.findViewById(R.id.vertical);
            frameLayout4 = itemView.findViewById(R.id.inclined_left);
            frameLayout5 = itemView.findViewById(R.id.inclined_right);
            hideLockScreen();

            // add.setTranslationY(translationY);
//            setAs.setTranslationY(0);
//            share.setTranslationY(0);
//            quickWall.setTranslationY(0);
//            download.setTranslationY(0);
//            favourite.setTranslationY(0);
//

            add.setAlpha(0f);
            quickWall.setAlpha(0f);
            share.setAlpha(0f);
            setAs.setAlpha(0f);
            download.setAlpha(0f);
            favourite.setAlpha(0f);
            frameLayout1.setAlpha(0f);
            frameLayout2.setAlpha(0f);
            frameLayout3.setAlpha(0f);
            frameLayout4.setAlpha(0f);
            frameLayout5.setAlpha(0f);


        }

        public  void openMenu(){
            Log.d("TAG", "openMenu: ");
            isMenuopen = !isMenuopen;
            add.animate().setInterpolator(overshootInterpolator).rotation(45f).setDuration(duration).start();
            setAs.animate().translationX(translationX).alpha(1f).setDuration(duration).setInterpolator(overshootInterpolator);
            quickWall.animate().translationX(-translationX).alpha(1f).setDuration(duration).setInterpolator(overshootInterpolator);

            share.animate().translationY(-translationY).translationX(-translationY).alpha(1f).setDuration(durationExtended);
            favourite.animate().translationY(-translationY).translationX(translationY).alpha(1f).setDuration(durationExtended);

            download.animate().translationY(-translationYY).alpha(1f).setInterpolator(overshootInterpolator).setDuration(duration);

            setAs.animate().rotation(rotate).setDuration(durationExtended);
            quickWall.animate().rotation(rotate).setDuration(durationExtended);
            share.animate().rotation(rotate).setDuration(durationExtended);
            favourite.animate().rotation(rotate).setDuration(durationExtended);
            download.animate().rotation(rotate).setDuration(durationExtended);

            frameLayout1.animate().alpha(1f).setDuration(linesdurationOpen);
            frameLayout2.animate().alpha(1f).setDuration(linesdurationOpen);
            frameLayout3.animate().alpha(1f).setDuration(linesdurationOpen);
            frameLayout4.animate().alpha(1f).setDuration(linesdurationOpen);
            frameLayout5.animate().alpha(1f).setDuration(linesdurationOpen);


        }


        public  void closeMenu(){
            Log.d("TAG", "closeMenu: ");
            isMenuopen = !isMenuopen;
            add.animate().setInterpolator(overshootInterpolator).rotation(0f).setDuration(duration).start();
            setAs.animate().translationX(0f).alpha(0f).setDuration(duration).setInterpolator(overshootInterpolator).setInterpolator(overshootInterpolator);
//            setAs.animate().translationX(0).alpha(0f).setInterpolator(overshootInterpolator).setDuration(duration);
            quickWall.animate().translationX(0f).alpha(0f).setDuration(duration).setInterpolator(overshootInterpolator).setInterpolator(overshootInterpolator);
//            quickWall.animate().translationX(0).alpha(0f).setInterpolator(overshootInterpolator).setDuration(duration);

            share.animate().translationY(0f).translationX(0f).alpha(0f).setDuration(duration);
            favourite.animate().translationY(0f).translationX(0f).alpha(0f).setDuration(duration);
            download.animate().translationY(0f).translationX(0f).alpha(0f).setInterpolator(overshootInterpolator).setDuration(duration);

            setAs.animate().rotation(-rotate).setDuration(durationExtended);
            quickWall.animate().rotation(-rotate).setDuration(durationExtended);
            share.animate().rotation(-rotate).setDuration(durationExtended);
            favourite.animate().rotation(-rotate).setDuration(durationExtended);
            download.animate().rotation(-rotate).setDuration(durationExtended);


            frameLayout1.animate().alpha(0f).setDuration(linesdurationExit);
            frameLayout2.animate().alpha(0f).setDuration(linesdurationExit);
            frameLayout3.animate().alpha(0f).setDuration(linesdurationExit);
            frameLayout4.animate().alpha(0f).setDuration(linesdurationExit);
            frameLayout5.animate().alpha(0f).setDuration(linesdurationExit);



        }



    }



    private void loadNativeAd(final int p) {
// Instantiate a NativeAd object.
// NOTE: the placement ID will eventually identify this as your App, you can ignore it for
// now, while you are testing and replace it later when you have signed up.
// While you are using this temporary code you will only get test ads and if you release
// your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        final NativeAd nativeAd = new NativeAd(context, context.getString(R.string.pager_native_facebook));

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad nativeAd) {
// Native nativeAd finished downloading all assets
                Log.e(TAG, "Native nativeAd finished downloading all assets.");
            }

            @Override
            public void onError(Ad nativeAd, AdError adError) {
// Native nativeAd failed to load
                Log.e(TAG, "Native nativeAd failed to load: pager " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
// Native nativeAd is loaded and ready to be displayed
                Log.d(TAG, "Native nativeAd is loaded and ready to be displayed!");
                // Race condition, load() called again before last nativeAd was displayed
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
// Inflate Native Ad into Container
                //inflateAd(nativeAd);
                //initAdUI(nativeAd,itemview);
                addNativeAdstoItemList(p,nativeAd);
            }

            @Override
            public void onAdClicked(Ad ad) {
// Native nativeAd clicked
                Log.d(TAG, "Native nativeAd clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
// Native nativeAd impression
                Log.d(TAG, "Native nativeAd impression logged!");
            }
        };

// Request an nativeAd
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());


//        // Here is just an example for displaying the nativeAd with delay
//        // Please call this method at appropriate timing in your project
//        showNativeAdWithDelay();

    }


    private void addNativeAdstoItemList(int po, NativeAd nativeAd) {

        itemList.remove(po);
        itemList.add(po,nativeAd);
//        notifyDataSetChanged();
        Log.d(TAG, "addNativeAdstoItemList: new native nativeAd loaded!!! ");

        for (int i = 0; i<itemList.size();i++){
            Log.d(TAG, "addNativeAdstoItemList: itemList:  "+ itemList.get(i).getClass());

        }
    }

    }


 class AdHolder  extends RecyclerView.ViewHolder{

    NativeAdLayout nativeAdLayout;
    MediaView mvAdMedia;
    MediaView ivAdIcon;
    TextView tvAdTitle;
    TextView tvAdBody;
    TextView tvAdSocialContext;
    TextView tvAdSponsoredLabel;
    Button btnAdCallToAction;
    LinearLayout adChoicesContainer;





    public AdHolder(@NonNull View itemView) {
        super(itemView);



        nativeAdLayout = itemView.findViewById(R.id.native_ad_container);
        mvAdMedia = itemView.findViewById(R.id.native_ad_media);
        tvAdTitle = itemView.findViewById(R.id.native_ad_title);
        tvAdBody = itemView.findViewById(R.id.native_ad_body);
        tvAdSocialContext = itemView.findViewById(R.id.native_ad_social_context);
        tvAdSponsoredLabel = itemView.findViewById(R.id.native_ad_sponsored_label);
        btnAdCallToAction = itemView.findViewById(R.id.native_ad_call_to_action);
        ivAdIcon = itemView.findViewById(R.id.native_ad_icon);
        adChoicesContainer = itemView.findViewById(R.id.ad_choices_container);




    }
}




class DummyAdHolder  extends RecyclerView.ViewHolder{


    public DummyAdHolder(@NonNull View itemView) {
        super(itemView);


    }
}

