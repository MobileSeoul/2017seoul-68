package sopt.seouri.community.for_search_recylcer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sopt.seouri.R;
import sopt.seouri.community.BulletinPostData;

/**
 * Created by yangseunghyuk on 2017-10-26.
 */

public class S_list_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<BulletinPostData> bulletinItems;
    Context context;
    View.OnClickListener onClick;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.s_list_recyler,parent,false);
        v.setOnClickListener(onClick);
        return new S_list_Viewholder(v);
    }

    public S_list_Adapter(ArrayList<BulletinPostData> bulletinItems, Context context, View.OnClickListener click)
    {
        this.context = context;
        this.bulletinItems = bulletinItems;
        this.onClick = click;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        S_list_Viewholder ViewHolder = (S_list_Viewholder) holder;
        ViewHolder.S_title.setText(bulletinItems.get(position).title.toString());
        ViewHolder.S_writer.setText(bulletinItems.get(position).getName().toString());
        ViewHolder.S_date.setText(bulletinItems.get(position).date.toString());
        ViewHolder.S_views.setText(bulletinItems.get(position).view_num + " ");
        if(!bulletinItems.get(position).profile.equals("")) {
            Glide.with(context).load(bulletinItems.get(position).profile).into(ViewHolder.S_profile);
        }
    }

    @Override
    public int getItemCount() {
      return bulletinItems.size();
    }
}
