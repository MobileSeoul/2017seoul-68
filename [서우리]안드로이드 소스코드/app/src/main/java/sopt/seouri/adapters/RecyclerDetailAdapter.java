package sopt.seouri.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sopt.seouri.R;
import sopt.seouri.search.detail.DetailImageData;
import sopt.seouri.viewholders.DetailViewHolder;

/**
 * Created by 김지원 on 2017-10-11.
 */

public class RecyclerDetailAdapter extends RecyclerView.Adapter<DetailViewHolder> {
    ArrayList<DetailImageData> itemData;
    Context context;

    public RecyclerDetailAdapter(ArrayList<DetailImageData> itemData, Context context) {
        this.itemData = itemData;
        this.context = context;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_detail_item,parent,false);
        DetailViewHolder viewHolder = new DetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        Glide.with(context).load(itemData.get(position).image).into(holder.recyclerImg);
    }

    @Override
    public int getItemCount() {
        return itemData != null ? itemData.size() : 0;
    }
}
