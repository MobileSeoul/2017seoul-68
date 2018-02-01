package sopt.seouri.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import sopt.seouri.R;

/**
 * Created by 김지원 on 2017-10-11.
 */

public class DetailViewHolder extends RecyclerView.ViewHolder{
    public ImageView recyclerImg;

    public DetailViewHolder(View itemView) {
        super(itemView);
        recyclerImg = (ImageView)itemView.findViewById(R.id.search_detail_recycler_item);
    }
}
