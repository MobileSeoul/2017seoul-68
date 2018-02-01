package sopt.seouri.community;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sopt.seouri.R;

/**
 * Created by yangseunghyuk on 2017-09-30.
 */

public class B_list_Viewholder extends RecyclerView.ViewHolder {

    TextView B_title;
    TextView B_writer;
    TextView B_date;
    TextView B_views;
    ImageView B_profile;

    public B_list_Viewholder(View itemView) {
        super(itemView);

        B_title = (TextView)itemView.findViewById(R.id.b_title);
        B_writer = (TextView)itemView.findViewById(R.id.b_writer);
        B_date = (TextView)itemView.findViewById(R.id.b_date);
        B_views = (TextView)itemView.findViewById(R.id.b_views);
        B_profile = (ImageView)itemView.findViewById(R.id.b_profile);



    }
}
