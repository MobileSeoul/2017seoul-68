package sopt.seouri.community.for_search_recylcer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sopt.seouri.R;

/**
 * Created by yangseunghyuk on 2017-10-26.
 */

public class S_list_Viewholder extends RecyclerView.ViewHolder {

    TextView S_title;
    TextView S_writer;
    TextView S_date;
    TextView S_views;
    ImageView S_profile;

    public S_list_Viewholder(View itemView) {
        super(itemView);

        S_title = (TextView)itemView.findViewById(R.id.s_title);
        S_writer = (TextView)itemView.findViewById(R.id.s_writer);
        S_date = (TextView)itemView.findViewById(R.id.s_date);
        S_views = (TextView)itemView.findViewById(R.id.s_views);
        S_profile = (ImageView)itemView.findViewById(R.id.s_profile);
    }

}
