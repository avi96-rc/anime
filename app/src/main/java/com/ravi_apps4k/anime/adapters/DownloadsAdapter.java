package com.ravi_apps4k.anime.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.facebook.ads.NativeAd;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.ravi_apps4k.anime.DownloadedItem;
import com.ravi_apps4k.anime.DummyAd;
import com.ravi_apps4k.anime.Item;
import com.ravi_apps4k.anime.MainActivity;
import com.ravi_apps4k.anime.R;
import com.ravi_apps4k.anime.ShowPagerActivity;

import java.util.List;

import static android.content.ContentValues.TAG;

//import com.google.android.gms.ads.AdLoader;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.formats.NativeAdOptions;
//import com.google.android.gms.ads.formats.UnifiedNativeAd;
//import static com.iLove.cats.MainActivity.button;


public class DownloadsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Drawable> icon ;
    List<Object> itemList;
    LayoutInflater layoutInflater;
    Context context;
    Dialog myDialog;
    FragmentManager fragmentManager;
    String fragName;
//    AdLoader adLoader;

    public DownloadsAdapter(Context ct, List<Object> itemList, FragmentManager fragmentManager, String fragName){

        context = ct;
        this.itemList = itemList;
        layoutInflater = LayoutInflater.from(context);
        this.fragmentManager =  fragmentManager;
        this.fragName = fragName;
       // icon = icons;


    }


    @Override
    public int getItemViewType(int position)
    {
//        if (itemList.get(position) instanceof NativeAd){
//            return R.layout.facebook_native_ad_layout;
//        } else

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

           case R.layout.native_ads_layout:
                view = layoutInflater.inflate(R.layout.native_ads_layout,parent,false);
                return new AdvertismentViewHolder(view);

           case R.layout.native_ads_layout_sample:
                view = layoutInflater.inflate(R.layout.native_ads_layout_sample,parent,false);
                return new DummyAdvertismentViewHolder(view);

        }


        return new MyViewHolder(view);
 }



    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        /*  uncomment when adding native ads...

        if (position<itemList.size()-4){                    // for loading adding before 3 position

            if (itemList.get(position+4) instanceof DummyAd ){
                laodNativeAds(position+4);

                // check if item at this postion is native or not..
//            if (!(itemList.get(position) instanceof UnifiedNativeAd)){              // if item is not a native ad{}
//                // insert native ad
//                Log.d(TAG, "onBindViewHolder: at position: "+position+" native not found.. requesting native ad");
//                laodNativeAds(position);
//            }else {
//                Log.d(TAG, "onBindViewHolder: at position"+position+" native found!! yay");
//
//            }
                // load a native ad .. insert into itemlist
            }

        }


         */

        if (holder instanceof MyViewHolder){
            Log.d(TAG, "onBindViewHolder:position"+position);
            Log.d(TAG, "onBindViewHolder: itemList:  "+itemList.get(position).getClass());
            final DownloadedItem item=  (DownloadedItem) itemList.get(position);

            ((MyViewHolder)holder).myImage.setImageBitmap((Bitmap) item.getBitmap() );
           // Picasso.get().load(item.getSmall()).placeholder(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE).into(((MyViewHolder)holder).myImage);
           // ((MyViewHolder)holder).title.setText(item.getId());
            ((MyViewHolder)holder).myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

// start new fragment to show image

                    Intent intent = new Intent(MainActivity.context, ShowPagerActivity.class);
                    intent.putExtra("position",position);
                    intent.putExtra("id",(String) item.getId());
                    intent.putExtra("fragName",fragName);


                    context.startActivity(intent);

//                    AppCompatActivity appCompatActivity = (AppCompatActivity)v.getContext();
//                    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                            android.R.anim.fade_out);
//                    pagerFragment frag2 = new pagerFragment(position,item.getId(),fragmentManager);
//                    // frag1.setCallBackInterface(MainActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_container,frag2);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                    button.setClickable(false);
//                    button.setAlpha(0);

                  //  fragmentTransaction.addToBackStack("hello");




                }
            });
        //    ((MyViewHolder)holder).card.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        }

//       else if(holder instanceof AdvertismentViewHolder){
//            Log.d(TAG, "onBindViewHolder:position"+position);
//            Log.d(TAG, "onBindViewHolder: itemList:  "+itemList.get(position).getClass());
//            final UnifiedNativeAd unifiedNativeAd =  (UnifiedNativeAd) itemList.get(position);
//           // ((AdvertismentViewHolder)holder).name.setText(ads.getAdsText());
//            // set the ad to layout here...
//            ((AdvertismentViewHolder)holder).template.setNativeAd(unifiedNativeAd);
//
//        }
        else if(holder instanceof DummyAdvertismentViewHolder){
            Log.d(TAG, "onBindViewHolder:Dummy ad"+position);

        }




    }

    @Override
    public int getItemCount() {
        //return 3;
        return itemList.size();
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
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


        }


        @Override
        public void onClick(View v) {


        }
    }


    // inner class for ads data

    class AdvertismentViewHolder  extends RecyclerView.ViewHolder{

        TextView name;
        TemplateView template;
        public AdvertismentViewHolder(@NonNull View itemView) {
            super(itemView);


            // get all the ads id... images title media of the layout
            int decode = Integer.decode("#FF6666");
            ColorDrawable colorDrawable = new ColorDrawable(decode);
            NativeTemplateStyle styles = new
                    NativeTemplateStyle.Builder().withMainBackgroundColor(colorDrawable).build();

            template = itemView.findViewById(R.id.my_template);
              template.setStyles(styles);
//            //template.setNativeAd(unifiedNativeAd);    // do this in onViewHOlderBinder
//
//            name = itemView.findViewById(R.id.ad_textView);
        }
    }


    class DummyAdvertismentViewHolder  extends RecyclerView.ViewHolder{


        public DummyAdvertismentViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }


//    private void laodNativeAds(int p) {
//        final int pos = p;
//
//        adLoader = new AdLoader.Builder(context,  context.getString(R.string.sample_native_ad))
//                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//                    @Override
//                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//
//                        if (!adLoader.isLoading()){
//                            //unifiedNativeAdsList.add(unifiedNativeAd);
//                           // Log.d(TAG, "onUnifiedNativeAdLoaded: size of unifiedNativeAdsList"+unifiedNativeAdsList.size());
//                            addNativeAdstoItemList(pos, unifiedNativeAd);
//                        }
//
//                        // Show the ad.
//
//                    /*    if (isDestroyed()) {
//                            unifiedNativeAd.destroy();
//                            return;
//                        }
//                    */
//                    }
//                })
//                .withNativeAdOptions(new NativeAdOptions.Builder()
//                        // Methods in the NativeAdOptions.Builder class can be
//                        // used here to specify individual options settings.
//                        .build())
//                .build();
//
//        adLoader.loadAd(new AdRequest.Builder().build());
//
//    }

//    private void addNativeAdstoItemList(int po, UnifiedNativeAd  unifiedNativeAd) {
//
//                itemList.remove(po);
//                itemList.add(po,unifiedNativeAd);
//                notifyDataSetChanged();
//              Log.d(TAG, "addNativeAdstoItemList: new ad loade");
//        }


}
