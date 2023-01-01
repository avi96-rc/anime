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

import com.ravi_apps4k.anime.CallBackInterface;
import com.ravi_apps4k.anime.R;
import com.ravi_apps4k.anime.adapters.DownloadsAdapter;

import java.util.List;

import static com.ravi_apps4k.anime.PublicMethods.*;


//import com.google.android.gms.ads.InterstitialAd;
//import com.google.android.gms.ads.formats.UnifiedNativeAd;


//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Frag1#newInstance} factory method to
// * create an instance of this fragment.
// */
public class DownloadedFrag extends Fragment {
    private static final String TAG = "";
    public static FragmentManager fragmentManager;

    //
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public Frag1() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Frag1.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Frag1 newInstance(String param1, String param2) {
//        Frag1 fragment = new Frag1();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
////        }
//
//
//    }



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

    public DownloadedFrag(Context context) {

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

        Log.d(TAG, "onCreate: size of itemList"+ sizeOfitemList);
//        DownloadedData downloadedData = new DownloadedData();
//        downloadedData.addObjects();
//        itemList = downloadedData.getItemsList();
        itemList = itemListDownloads;
        Log.d(TAG, "onCreate: size of itemList:"+itemList.size());

        addDummyNativeAdstoItemList();
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

        adapt(context,itemList);
    }


    public  static void adapt(Context context, List<Object> itemList){

        final DownloadsAdapter myAdapter = new DownloadsAdapter(context,itemList,fragmentManager,"downloads");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false);
        //gridLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myAdapter);



    }



    public void setCallBackInterface(CallBackInterface callBackInterface){
        this.callBackInterface = callBackInterface;

    }

    private void initUI() {
        context = getContext();


    }
}