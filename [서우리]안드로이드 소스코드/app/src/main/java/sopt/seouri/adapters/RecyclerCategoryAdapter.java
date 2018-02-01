package sopt.seouri.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sopt.seouri.R;
import sopt.seouri.search.category.SearchCategoryResultData;
import sopt.seouri.viewholders.CategoryViewHolder;

/**
 * Created by 김지원 on 2017-10-06.
 */

public class RecyclerCategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    ArrayList<SearchCategoryResultData> itemData;
    View.OnClickListener clickListener;
    Context context;

    public RecyclerCategoryAdapter(ArrayList<SearchCategoryResultData> itemData, View.OnClickListener clickListener, Context context) {
        this.itemData = itemData;
        this.clickListener = clickListener;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_cardview,parent,false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
        view.setOnClickListener(clickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.cardText.setText(itemData.get(position).name);
//        holder.cardImg.setImageResource(itemData.get(position).photo);
        Glide.with(context).load(itemData.get(position).photo).into(holder.cardImg);

    }

    @Override
    public int getItemCount() {
        return itemData != null ? itemData.size() : 0;
    }
}
