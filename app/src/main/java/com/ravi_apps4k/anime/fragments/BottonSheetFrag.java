package com.ravi_apps4k.anime.fragments;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ravi_apps4k.anime.MainActivity;
import com.ravi_apps4k.anime.PublicMethods;
import com.ravi_apps4k.anime.R;

import static android.content.ContentValues.TAG;
import static com.ravi_apps4k.anime.PublicMethods.bottomSheetDialogFragmentList;
import static com.ravi_apps4k.anime.MainActivity.STORAGE_PERMISSION_CODE;
import static com.ravi_apps4k.anime.MainActivity.per;


public class BottonSheetFrag extends BottomSheetDialogFragment {

    BottomSheetDialogFragment bottomSheetDialogFragment;
    Context context;
    Bitmap bitmap;
    String fileName;
    String url;
    Boolean quickWall;
    Boolean setAs;
    Boolean share;

    public BottonSheetFrag(BottomSheetDialogFragment bottomSheetDialogFragment, Context context, Bitmap bitmap, String fileName, String url, Boolean quickWall, Boolean setAs, Boolean share) {
        this.bottomSheetDialogFragment = bottomSheetDialogFragment;
        this.context = context;
        this.bitmap = bitmap;
        this.fileName = fileName;
        this.url = url;
        this.quickWall = quickWall;
        this.setAs = setAs;
        this.share = share;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet,container,false);

        final LinearLayout fourK = v.findViewById(R.id.four_K);
        fourK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 4k clicked");
//                bottomSheetDialogFragment.dismiss();
                Log.d(TAG, "onClick: per: "+per);
                if (per == true){
                    dismissBottomSheet();
                    PublicMethods.saveToInternalStorage(context, bitmap ,"4k_"+fileName,url,quickWall,setAs,share,"4k");
                }else {
                    getPermission();
                }


            }
        });
        final LinearLayout fourKFit = v.findViewById(R.id.four_K_fit);
        fourKFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: fourKFit clicked"); Log.d(TAG, "onClick: per: "+per);
                //bottomSheetDialogFragment.dismiss();
                if (per == true){
                    dismissBottomSheet();
                    PublicMethods.saveToInternalStorage(context, bitmap ,"4kfitted_"+fileName,url,quickWall,setAs,share,"4kfit");
                }else {
                    getPermission();
                }
                   }
        });

        final LinearLayout fullHd = v.findViewById(R.id.full_hd);
        fullHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: fullHd clicked"); Log.d(TAG, "onClick: per: "+per);
                if (per == true){
                    dismissBottomSheet();
                    PublicMethods.saveToInternalStorage(context, bitmap ,"fullhd_"+fileName,url,quickWall,setAs,share,"fullhd");
                }else {
                    getPermission();
                }
//                bottomSheetDialogFragment.dismiss();

            }
        });
        return v;
    }

    public void dismissBottomSheet(){
        Log.d(TAG, "dismissBottomSheet: bottomSheetDialogFragmentList size: "+bottomSheetDialogFragmentList.size());
        for (BottomSheetDialogFragment bottomSheetDialog : bottomSheetDialogFragmentList ){
            bottomSheetDialog.dismiss();
        }
        bottomSheetDialogFragmentList.clear();
    }

    public interface BottomSheetListener{
        void onbuttonClicked(String str);
    }

    public  void getPermission() {
        Log.d(TAG, "getPermission: method callled");

        ((MainActivity) MainActivity.context).checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE);

    }


}
