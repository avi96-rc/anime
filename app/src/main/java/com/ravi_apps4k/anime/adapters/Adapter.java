package com.ravi_apps4k.anime.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ravi_apps4k.anime.DummyAd;
import com.ravi_apps4k.anime.Item;
import com.ravi_apps4k.anime.MainActivity;
import com.ravi_apps4k.anime.R;
import com.ravi_apps4k.anime.ShowPagerActivity;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import static android.content.ContentValues.TAG;


public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Drawable> icon ;
    List<Object> itemList;
    LayoutInflater layoutInflater;
    Context context;
    Dialog myDialog;
    FragmentManager fragmentManager;
    String fragName;
    List<Integer> ads= new ArrayList<>();

    private com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd;
    private com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd2;


    private void loadInterstitial(Context context) {

        Log.d(TAG, "loadInterstitial:");

        com.google.android.gms.ads.interstitial.InterstitialAd.load(context,context.getResources().getString(R.string.admob_home_adapter_interstitial),
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
    private void loadInterstitial2(Context context) {

        Log.d(TAG, "loadInterstitial:");

        com.google.android.gms.ads.interstitial.InterstitialAd.load(context,context.getResources().getString(R.string.admob_home_interstitial),
                new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.

                        mInterstitialAd2 = interstitialAd;
                        Log.d(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG," error"+ loadAdError.getMessage());
                        mInterstitialAd2 = null;
                    }

                });

    }



    public Adapter(Context ct, List<Object> itemList, FragmentManager fragmentManager, String fragName){

        context = ct;

        this.itemList = itemList;
        layoutInflater = LayoutInflater.from(context);
//        this.fragmentManager =  fragmentManager;
        this.fragName = fragName;
       // icon = icons;

    }

    @Override
    public int getItemViewType(int position)
    {
        if (itemList.get(position) instanceof NativeAd){
            return R.layout.facebook_native_ad_layout;
        } else

            if(itemList.get(position) instanceof Item)
        {
            return  R.layout.custom_list_view;
        }else if(itemList.get(position) instanceof DummyAd)
        {
            return  R.layout.native_ads_layout_sample;
        }
        return  R.layout.custom_list_view;    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_list_view,parent,false);

        switch (viewType){
            case R.layout.custom_list_view:
                view = layoutInflater.inflate(R.layout.custom_list_view,parent,false);
                return new MyViewHolder(view);

            case R.layout.facebook_native_ad_layout:
                view = layoutInflater.inflate(R.layout.facebook_native_ad_layout,parent,false);
                return new AdHolder(view);

            case R.layout.native_ads_layout_sample:
                view = layoutInflater.inflate(R.layout.native_ads_layout_sample,parent,false);
                return new DummyAdHolder(view);

        }


        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if (position<itemList.size()-9){                    // for loading adding before 9 position ..  pre loading

            if (itemList.get(position+9) instanceof DummyAd ){
                loadNativeAd(position+9);

            }

        }

        Log.d(TAG, "onBindViewHolder: itemList:  "+itemList.get(position).getClass());


        // google ads

        // show interstitial ads after 150 images


        final int ads_after_image = 70;

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



        if (holder instanceof MyViewHolder){



            Log.d(TAG, "onBindViewHolder:position"+position);
            Log.d(TAG, "onBindViewHolder: itemList:  "+itemList.get(position).getClass());
            final Item item= (Item) itemList.get(position);

            final com.squareup.picasso.Callback  callback = new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    //do smth when picture is loaded successfully
                    Log.d(TAG, "onSuccess: picasso image loaded");

                }

                @Override
                public void onError(Exception e) {
                    //do smth when there is picture loading error
                    Picasso.get()
                            .load(item.getSmall()).placeholder(R.drawable.placeholder)
                            .into(((MyViewHolder)holder).myImage);
                }
            };


            Picasso.get()
                    .load(item.getSmall()).placeholder(R.drawable.placeholder)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(((MyViewHolder)holder).myImage,callback);

           // ((MyViewHolder)holder).title.setText(item.getId());
            ((MyViewHolder)holder).myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // start new fragment to show image
                //                    show ads



                    Intent intent = new Intent(MainActivity.context, ShowPagerActivity.class);
                    intent.putExtra("position",position);
                    intent.putExtra("id",item.getId());
                    intent.putExtra("fragName",fragName);
                    context.startActivity(intent);

                    if (mInterstitialAd2 != null) {

                        mInterstitialAd2.show((Activity)context);
                        mInterstitialAd2.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                loadInterstitial2(context);
                            }


                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd2 = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });

                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }




                }
            });
        //    ((MyViewHolder)holder).card.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
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
        //return 3;
        return itemList.size();
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView picNo;
        TextView serial;
        ImageView myImage;
        ImageView favourite;
        ConstraintLayout mainLayout;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.mytitle);
            //serial = itemView.findViewById(R.id.serial);
            myImage = itemView.findViewById(R.id.myimage);
            // favourite = itemView.findViewById(R.id.favouite);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            card = itemView.findViewById(R.id.cardView);
            picNo = itemView.findViewById(R.id.no);




        }


        @Override
        public void onClick(View v) {


        }
    }


    // inner class for ads data


    // inner class for ads data

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




    private void loadNativeAd(final int p) {
// Instantiate a NativeAd object.
// NOTE: the placement ID will eventually identify this as your App, you can ignore it for
// now, while you are testing and replace it later when you have signed up.
// While you are using this temporary code you will only get test ads and if you release
// your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        final NativeAd nativeAd = new NativeAd(context, context.getString(R.string.home_native_facebook));

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad nativeAd) {
// Native nativeAd finished downloading all assets
                Log.e(TAG, "Native nativeAd finished downloading all assets.");
            }

            @Override
            public void onError(Ad nativeAd, AdError adError) {
// Native nativeAd failed to load
                Log.e(TAG, "Native nativeAd failed to load: " + adError.getErrorMessage());
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
