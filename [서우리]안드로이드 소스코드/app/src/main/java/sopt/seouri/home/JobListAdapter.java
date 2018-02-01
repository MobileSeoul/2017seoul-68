package sopt.seouri.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sopt.seouri.R;
import sopt.seouri.home.networkData.JobinformationData;

/**
 * Created by yangseunghyuk on 2017-09-30.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobViewHolder> {

    ArrayList<JobinformationData> datas;
    Context context;

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.job_list_recyler,parent,false);
        return new JobViewHolder(v);
    }

    public JobListAdapter(ArrayList<JobinformationData> datas, Context context) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        if(position==1)holder.imageViewJob.setImageResource(R.drawable.shoes);
        else holder.imageViewJob.setImageResource(R.drawable.sill);
        holder.textViewAddress.setText(datas.get(position).address);
        holder.textViewCom.setText(datas.get(position).name);
        holder.textViewTime.setText(datas.get(position).time);
        holder.textViewPay.setText(datas.get(position).pay);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
