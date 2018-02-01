package sopt.seouri.community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sopt.seouri.R;

/**
 * Created by yangseunghyuk on 2017-09-30.
 */

public class B_list_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<BulletinPostData> bulletinItems;
    Context context;
    View.OnClickListener onClick;



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.b_list_recyler,parent,false);
        v.setOnClickListener(onClick);
        return new B_list_Viewholder(v);
    }

    public B_list_Adapter(ArrayList<BulletinPostData> bulletinItems, Context context, View.OnClickListener click)
    {
        this.context = context;
        this.bulletinItems = bulletinItems;
        this.onClick = click;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        B_list_Viewholder ViewHolder = (B_list_Viewholder) holder;
        ViewHolder.B_title.setText(bulletinItems.get(position).title.toString());
        ViewHolder.B_writer.setText(bulletinItems.get(position).getName().toString());
        ViewHolder.B_date.setText(bulletinItems.get(position).date.toString());
        ViewHolder.B_views.setText(bulletinItems.get(position).view_num + " ");
        if(!bulletinItems.get(position).profile.equals("")) {
            Glide.with(context).load(bulletinItems.get(position).profile).into(ViewHolder.B_profile);
        }
    }

    @Override
    public int getItemCount() {
        return bulletinItems.size();
    }
}
