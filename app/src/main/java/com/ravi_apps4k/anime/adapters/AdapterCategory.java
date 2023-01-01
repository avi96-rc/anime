package com.ravi_apps4k.anime.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ravi_apps4k.anime.CatItem;
import com.ravi_apps4k.anime.DummyAd;
import com.ravi_apps4k.anime.Item;
import com.ravi_apps4k.anime.R;
import com.ravi_apps4k.anime.ShowCategory;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import static android.content.ContentValues.TAG;

//import com.facebook.ads.NativeAd;
//import com.google.android.ads.nativetemplates.NativeTemplateStyle;
//import com.google.android.ads.nativetemplates.TemplateView;
//import com.google.android.gms.ads.AdLoader;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.formats.NativeAdOptions;
//import com.google.android.gms.ads.formats.UnifiedNativeAd;
//import static com.iLove.cats.MainActivity.button;


public class AdapterCategory extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    List<Object> itemList;
    LayoutInflater layoutInflater;
    Context context;
    Dialog myDialog;
    FragmentManager fragmentManager;
    String fragName;
//    AdLoader adLoader;
//    public  static List<String> name= new ArrayList<String>();
//    public static List<Integer> url= new ArrayList<Integer>();


    public AdapterCategory(Context ct, List<Object> itemList, FragmentManager fragmentManager, String fragName){

        context = ct;
       this.itemList = itemList;
        Log.d(TAG, "Adapter: size of itemlistmain: "+ itemList.size());
        layoutInflater = LayoutInflater.from(context);
        this.fragmentManager =  fragmentManager;
        this.fragName = fragName;


// nav_image = icons;


//            name.add("ironman");
//            url.add(R.drawable.ironman);
//            name.add("thor");
//            url.add(R.drawable.thor);
//            name.add("captain marvel");
//            url.add(R.drawable.captain_marvel);
//            name.add("captain  america");
//            url.add(R.drawable.captain_marvel);
//            name.add("spiderman");
//            url.add(R.drawable.spiderman);
//            name.add("batman");
//            url.add(R.drawable.batman);
//            name.add("black panther");
//            url.add(R.drawable.black_panther);
//            name.add("superman");
//            url.add(R.drawable.superman);



        }





    @Override
    public int getItemViewType(int position)
    {
//        if (itemList.get(position) instanceof NativeAd){
//            return R.layout.facebook_native_ad_layout;
//        } else

            if(itemList.get(position) instanceof Item)
        {
            return  R.layout.custom_list_view_category;
        }else if(itemList.get(position) instanceof DummyAd)
        {
            return  R.layout.native_ads_layout_sample;
        }
        return  R.layout.custom_list_view_category;    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_list_view_category,parent,false);

        switch (viewType){
            case R.layout.custom_list_view_category:
                view = layoutInflater.inflate(R.layout.custom_list_view_category,parent,false);
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


        // calllBacks

        final com.squareup.picasso.Callback  callback = new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                //do smth when picture is loaded successfully
                // Toast.makeText(MainActivity.context, "Wait..Downloading Image!!",Toast.LENGTH_LONG).show();
                Log.d(TAG, "onSuccess: picasso image loaded");

               }

            @Override
            public void onError(Exception e) {
                //do smth when there is picture loading error
            }
        };


        if (holder instanceof MyViewHolder){
            Log.d(TAG, "onBindViewHolder:position"+position);
//            Log.d(TAG, "onBindViewHolder: itemList:  "+itemList.get(position).getClass());
            final CatItem item= (CatItem) itemList.get(position);
//            ((MyViewHolder)holder).myImage.setImageResource(item.getUrl());
            ((MyViewHolder)holder).title.setText(item.getName());

//            switch (item.getName()){
//                case "roses": ((MyViewHolder)holder).ads.setText("advertisement");
//                    break;
//            }

            if (item.getName().equals("roses") || item.getName().equals("cats") || item.getName().equals("cars") || item.getName().equals("dogs")|| item.getName().equals("flowers")){
                ((MyViewHolder)holder).ads.setText("advertisement");
                ((MyViewHolder)holder).adFrame.setBackground(context.getResources().getDrawable(R.drawable.black_shade_reversed));


            } else {
                ((MyViewHolder)holder).ads.setText("");
                ((MyViewHolder)holder).adFrame.setBackground(null);

            }
//            Picasso.get().load((url.get(position))).placeholder(R.drawable.hour_glass).into(((MyViewHolder)holder).myImage);
//            Picasso.get().load(item.getUrl()).placeholder(R.drawable.hour_glass).memoryPolicy(MemoryPolicy.NO_CACHE).into(((MyViewHolder)holder).myImage,callback);
           // ((MyViewHolder)holder).title.setText(item.getId());
//            ((MyViewHolder)holder).myImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//// start new fragment to show image
//
//                    Intent intent = new Intent(context, ShowCategory.class);
//                    intent.putExtra("category_name",item.getName());
//                    context.startActivity(intent);
//
////                    AppCompatActivity appCompatActivity = (AppCompatActivity)v.getContext();
////                    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
////                    FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
////                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
////                            android.R.anim.fade_out);
////                    pagerFragment frag2 = new pagerFragment(position,item.getId(),fragmentManager);
////                    // frag1.setCallBackInterface(MainActivity.this);
////                    fragmentTransaction.replace(R.id.fragment_container,frag2);
////                    fragmentTransaction.addToBackStack(null);
////                    fragmentTransaction.commit();
////                    button.setClickable(false);
////                    button.setAlpha(0);
//
//                  //  fragmentTransaction.addToBackStack("hello");
//
//
//
//
//                }
//            });


//             get the images links max upto 10;
            // setting the carousel
            Log.d(TAG, "makecarousel: executed");
            ((MyViewHolder)holder).carouselView.setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(final int position, final ImageView imageView) {

                    final com.squareup.picasso.Callback  callback = new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            //do smth when picture is loaded successfully
                            // Toast.makeText(MainActivity.context, "Wait..Downloading Image!!",Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onSuccess: picasso image loaded");

                        }

                        @Override
                        public void onError(Exception e) {
                            //do smth when there is picture loading error
                            Picasso.get().load((item.getimagesforCarousel()).get(position)).
                                    into(imageView);
                        }
                    };


                    Picasso.get().load((item.getimagesforCarousel()).get(position))
                            .networkPolicy(NetworkPolicy.OFFLINE).
                            into(imageView,callback);
                    //imageView.setImageResource(sampleImages[position]);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (item.getName().equals("roses") || item.getName().equals("cats") || item.getName().equals("cars") || item.getName().equals("dogs") || item.getName().equals("flowers")){

                                switch (item.getName()){
                                    case "roses":
                                        Log.d(TAG, "onClick: found rose");
                                        String roses = "https://play.google.com/store/apps/details?id=" + "com.iLove.roses";
                                        Uri rosesUri = Uri.parse(roses);
                                        Intent rosesIntentt = new Intent(Intent.ACTION_VIEW, rosesUri);
                                        if (rosesIntentt.resolveActivity(context.getPackageManager()) != null) {
                                            context.startActivity(rosesIntentt);
                                        }
                                        break;
                                    case "cats":
                                        String cats = "https://play.google.com/store/apps/details?id=" + "com.iLove.cats";
                                        Uri catUri = Uri.parse(cats);
                                        Intent catIntent = new Intent(Intent.ACTION_VIEW, catUri);
                                        if (catIntent.resolveActivity(context.getPackageManager()) != null) {
                                            context.startActivity(catIntent);
                                        }
                                        break;
                                    case "dogs":
                                        String dogs = "https://play.google.com/store/apps/details?id=" + "com.iLove.dogs";
                                        Uri dogsUri = Uri.parse(dogs);
                                        Intent dogsIntentt = new Intent(Intent.ACTION_VIEW, dogsUri);
                                        if (dogsIntentt.resolveActivity(context.getPackageManager()) != null) {
                                            context.startActivity(dogsIntentt);
                                        }
                                        break;
                                    case "cars":
                                        String cars = "https://play.google.com/store/apps/details?id=" + "com.Super.Car";
                                        Uri carsUri = Uri.parse(cars);
                                        Intent carsIntent = new Intent(Intent.ACTION_VIEW, carsUri);
                                        if (carsIntent.resolveActivity(context.getPackageManager()) != null) {
                                            context.startActivity(carsIntent);
                                        }
                                        break;

                                    case "flowers":
                                        String flowers = "https://play.google.com/store/apps/details?id=" + "com.iLove.flowers";
                                        Uri flowersUri = Uri.parse(flowers);
                                        Intent flowersIntent = new Intent(Intent.ACTION_VIEW, flowersUri);
                                        if (flowersIntent.resolveActivity(context.getPackageManager()) != null) {
                                            context.startActivity(flowersIntent);
                                        }
                                        break;
                                }
                            } else {


                                Intent intent = new Intent(context, ShowCategory.class);
                                intent.putExtra("category_name",item.getName());
                                context.startActivity(intent);


                            }



                        }
                    });
                }
            });
            ((MyViewHolder)holder).carouselView.setPageCount((item.getimagesforCarousel()).size());
//            ((MyViewHolder)holder).carouselView.;
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
//        return 20;
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView ads;
        FrameLayout adFrame;
        ImageView myImage;
        ImageView favourite;
        ConstraintLayout mainLayout;
        CardView card;
        CarouselView carouselView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.mytitle);
            ads = itemView.findViewById(R.id.ads);
            adFrame = itemView.findViewById(R.id.ad_frame);
//            myImage = itemView.findViewById(R.id.myimage);
           // favourite = itemView.findViewById(R.id.favouite);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            card = itemView.findViewById(R.id.cardView);
            carouselView = (CarouselView) itemView.findViewById(R.id.carouselView);


        }


        @Override
        public void onClick(View v) {


        }
    }


    // inner class for ads data

    class AdvertismentViewHolder  extends RecyclerView.ViewHolder{

        TextView name;
//        TemplateView template;
        public AdvertismentViewHolder(@NonNull View itemView) {
            super(itemView);


            // get all the ads id... images title media of the layout
            int decode = Integer.decode("#FF6666");
            ColorDrawable colorDrawable = new ColorDrawable(decode);
//            NativeTemplateStyle styles = new
//                    NativeTemplateStyle.Builder().withMainBackgroundColor(colorDrawable).build();
//
//            template = itemView.findViewById(R.id.my_template);
//              template.setStyles(styles);
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
