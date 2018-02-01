package sopt.seouri.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sopt.seouri.R;

/**
 * Created by 김지원 on 2017-10-06.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {
//    public String id;
//    public String category;
    public TextView cardText;
    public ImageView cardImg;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        cardImg = (ImageView)itemView.findViewById(R.id.card_image);
        cardText = (TextView)itemView.findViewById(R.id.card_text);
    }
}
