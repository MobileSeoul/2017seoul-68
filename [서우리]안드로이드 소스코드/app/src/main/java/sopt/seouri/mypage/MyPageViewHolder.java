package sopt.seouri.mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sopt.seouri.R;

public class MyPageViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public ImageView imageView;

    public MyPageViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.mypage_list_text);
        imageView = (ImageView) itemView.findViewById(R.id.mypage_list_imageview);
    }
}
