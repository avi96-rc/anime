package com.ravi_apps4k.anime.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.ravi_apps4k.anime.DummyAd;
import com.ravi_apps4k.anime.Item;
import com.ravi_apps4k.anime.R;
import com.ravi_apps4k.anime.adapters.ViewPagerAdapter;

import java.util.List;

import static android.content.ContentValues.TAG;
import static com.ravi_apps4k.anime.PublicMethods.pagerAdsGap;
import static com.ravi_apps4k.anime.ShowPagerActivity.showPagerContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pagerFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public pagerFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment pagerFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static pagerFragment newInstance(String param1, String param2) {
//        pagerFragment fragment = new pagerFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
    public  FragmentManager fragmentManager;


    View view;
    Context context;
    ViewPager2 viewPager2;
    List<String> tittles;
    List<Integer> images;
    List<Object> itemList;
    int position;
    String id;
    int sizeOfitemList;

    // remove  String id ... no need later
    public pagerFragment(int position, String id, FragmentManager fragmentManager, String fragName, List<Object> items) {
        this.position = position;
        this.id = id;
        this.fragmentManager = fragmentManager;
        itemList =items;
        sizeOfitemList = itemList.size();

//       switch (fragName){
//           case "favourite":
//               itemList = loadFavouritesFromJson(showPagerContext);
//
//           break;
//           case "home":
//               itemList = PublicMethods.getPosititon(showPagerContext);
//           break;
//       }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = showPagerContext;
         view= inflater.inflate(R.layout.fragment_frag2, container, false);
        addDummyNativeAdstoItemList();
        return  view;
    }


   


    private void addDummyNativeAdstoItemList() {
//                  ad ads  after 100+ downloads

//        Log.d(TAG, ": size of itemList:"+itemList.size());
//
        for (int i = pagerAdsGap; i<sizeOfitemList; i=i+pagerAdsGap,sizeOfitemList++){
            itemList.add(i,new DummyAd());
            Log.d(TAG, "addDummyNativeAdstoItemList: size of itemList loop: "+i+" size: "+itemList.size());
        }

        for (int i= 0; i<itemList.size();i++){
            Log.d(TAG, "addDummyNativeAdstoItemList: position: "+i+" items: "+itemList.get(i).getClass());
        }



        getPosititon();
      
    }

    private void getPosititon() {


        for (int i = 0; i<itemList.size(); i++ ){
            if (itemList.get(i) instanceof Item){
                Item item = (Item)itemList.get(i);
                if (item.getId().equals(id)){
                    position = i;
                    break;
                }
            }

        }

        adapt(context,itemList);

    }

    public void adapt(Context context, List<Object> itemList){

        viewPager2 =  view.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context,position,id,viewPager2,fragmentManager,itemList);
        // GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
        //viewPager2.setLayoutManager(gridLayoutManager);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setCurrentItem(position,false);
        Log.d(TAG, "onCreateView: viewPager2.getCurrentItem():  "+ viewPager2.getCurrentItem());
        viewPager2.setOffscreenPageLimit(5);
    }



}