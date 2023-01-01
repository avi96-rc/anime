package com.ravi_apps4k.anime.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.InterstitialAd;
import com.ravi_apps4k.anime.CallBackInterface;
import com.ravi_apps4k.anime.DummyAd;
import com.ravi_apps4k.anime.PublicMethods;
import com.ravi_apps4k.anime.R;
import com.ravi_apps4k.anime.adapters.Adapter;

import java.util.List;

//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.formats.UnifiedNativeAd;


//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Frag1#newInstance} factory method to
// * create an instance of this fragment.
// */

public class FavouriteFrag extends Fragment {
    private static final String TAG = "";
    public static FragmentManager fragmentManager;


    public static RecyclerView recyclerView;
    Intent intent;
    String playListId;
//    UnifiedNativeAd unifiedNativeAd;
    List<Object> itemList;
//    List<UnifiedNativeAd> unifiedNativeAdsList;
    static  final int NO_OF_ADS = 2;
//    private InterstitialAd mInterstitialAd;
    int sizeOfitemList;



    View view;
    private InterstitialAd mInterstitialAd;

    public FavouriteFrag(Context context, InterstitialAd mInterstitialAd) {
        this.mInterstitialAd = mInterstitialAd;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    Context context;
    Button button;
    CallBackInterface callBackInterface;

    @Override
    public void onResume() {
      //  ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("title set by  fragment1");
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_frag1, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        loadData();
        return view;
    }

    private void loadData() {

        Log.d(TAG, "loadData: size of itemList"+ sizeOfitemList);
//        itemList = itemListMain;
        itemList = PublicMethods.loadFavouritesFromJson(context);
        for (Object object: itemList){
            Log.d(TAG, "loadData: object of itemList"+ object.getClass());
        }
//        seed = System.nanoTime();
//        Collections.shuffle(itemList,new Random(seed));
        sizeOfitemList = itemList.size();
        Log.d(TAG, "onCreate: size of itemList:"+itemList.size());
        addDummyNativeAdstoItemList();
    }



    private void addDummyNativeAdstoItemList() {
        //                  ad ads  after 100+ downloads

        Log.d(TAG, ": size of itemList:"+itemList.size());

//        for (int i = 9; i<sizeOfitemList;i=i+13,sizeOfitemList++){
//            itemList.add(i,new DummyAd());
//            Log.d(TAG, "addDummyNativeAdstoItemList: size of itemList loop: "+i+" size: "+itemList.size());
//        }
//
//        for (int i= 0; i<itemList.size();i++){
//            Log.d(TAG, "addDummyNativeAdstoItemList: position: "+i+" items: "+itemList.get(i).getClass());
//        }

        adapt(context,itemList);
    }


    public   void adapt(Context context, List<Object> itemList){

        final Adapter myAdapter = new Adapter(context,itemList,fragmentManager,"favourite");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false);
        //gridLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myAdapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (myAdapter.getItemViewType(position) == R.layout.native_ads_layout_sample){
                    return 3;
                }
                if (myAdapter.getItemViewType(position) == R.layout.facebook_native_ad_layout    ){
                    return 3;
                }
                return 1;
            }
        });





    }



    public void setCallBackInterface(CallBackInterface callBackInterface){
        this.callBackInterface = callBackInterface;

    }

    private void initUI() {
        context = getContext();


    }
}