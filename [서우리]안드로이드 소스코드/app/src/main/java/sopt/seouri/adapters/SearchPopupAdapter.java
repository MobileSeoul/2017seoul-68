package sopt.seouri.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sopt.seouri.R;
import sopt.seouri.search.popup.SearchPopupResultData;
import sopt.seouri.viewholders.SearchPopupViewHolder;

/**
 * Created by 김지원 on 2017-10-27.
 */

public class SearchPopupAdapter extends RecyclerView.Adapter<SearchPopupViewHolder> {
    ArrayList<SearchPopupResultData> itemData;
    Context context;

    public SearchPopupAdapter(ArrayList<SearchPopupResultData> itemData, Context context) {
        this.itemData = itemData;
        this.context = context;
    }

    @Override
    public SearchPopupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_popup_item,parent,false);
        SearchPopupViewHolder viewHolder = new SearchPopupViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchPopupViewHolder holder, int position) {
        holder.villageName.setText(itemData.get(position).name);
    }

    @Override
    public int getItemCount() {
        return itemData != null ? itemData.size() : 0;
    }
}
