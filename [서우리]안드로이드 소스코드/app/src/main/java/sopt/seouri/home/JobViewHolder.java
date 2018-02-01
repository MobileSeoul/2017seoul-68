package sopt.seouri.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sopt.seouri.R;

public class JobViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewCom;
    public ImageView imageViewJob;
    public TextView textViewAddress;
    public TextView textViewPay;
    public TextView textViewTime;


    public JobViewHolder(View itemView) {
        super(itemView);
        textViewCom = (TextView)itemView.findViewById(R.id.job_list_com);
        imageViewJob = (ImageView)itemView.findViewById(R.id.job_list_img);
        textViewAddress = (TextView)itemView.findViewById(R.id.job_list_address);
        textViewPay = (TextView)itemView.findViewById(R.id.job_list_pay);
        textViewTime = (TextView)itemView.findViewById(R.id.job_list_time);

    }
}
