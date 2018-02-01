package sopt.seouri.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sopt.seouri.R;

/**
 * Created by 김지원 on 2017-10-27.
 */

public class SearchPopupViewHolder extends RecyclerView.ViewHolder {
    public TextView villageName;

    public SearchPopupViewHolder(View itemView) {
        super(itemView);
        villageName = (TextView)itemView.findViewById(R.id.villageName);
    }
}