package com.ravi_apps4k.anime;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MyPlayListAdapter extends RecyclerView.Adapter<MyPlayListAdapter.MyViewHolder>{

    private List<Drawable> icon ;
    private List<String> Title ;
    private List<String> playListId ;
    private List<String> imageUrl ;


    Context context;
    Dialog myDialog;

    public MyPlayListAdapter(Context ct, List<String> title, List<String> playListId, List<String> imageUrl){

        context = ct;
        this.Title = title;
        this.playListId = playListId;
        this.imageUrl = imageUrl;
       // icon = icons;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view  =  inflater.inflate(R.layout.my_play_list,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.title.setText((String)Title.get(position));
        int p = position+1;
//        holder.serial.setText(p+". ");
        Picasso.get().load(imageUrl.get(position)).into(holder.myImage);




        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // code when list is clicked ....

//                Intent intent = new Intent(context,SecondActivity.class);
//                intent.putExtra("data1",data1[position]);
//                intent.putExtra("data2",data2[position]);
//                intent.putExtra("image",images[position]);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //return 3;
        return Title.size();
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView serial;
        ImageView myImage;
        ImageView bin;
        ConstraintLayout mainLayout;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.mytitle);
           // serial = itemView.findViewById(R.id.serial);
            myImage = itemView.findViewById(R.id.myimage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            card = itemView.findViewById(R.id.mycard);

            View.OnClickListener uninstal= new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("id",playListId.get(getAdapterPosition()));
                    intent.putExtra("title",Title.get(getAdapterPosition()));
                        context.startActivity(intent);
                }
            };

                card.setOnClickListener(uninstal);

        }

        @Override
        public void onClick(View v) {


        }
    }




}
