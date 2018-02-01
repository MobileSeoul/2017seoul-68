package sopt.seouri.ask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sopt.seouri.R;

/**
 * Created by yangseunghyuk on 2017-09-30.
 */

public class AskListAdapter extends RecyclerView.Adapter<AskViewHolder> {

    ArrayList<MyQuestion> datas;
    Context context;

    @Override
    public AskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ask_recyler,parent,false);
        return new AskViewHolder(v);
    }

    public AskListAdapter(ArrayList<MyQuestion> datas, Context context) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public void onBindViewHolder(AskViewHolder holder, int position) {
        holder.textViewTime.setText(datas.get(position).title);
        holder.textViewState.setText("대기중");
        holder.textViewContent.setText(datas.get(position).content);
    }

    @Override
    public int getItemCount() {
        return (datas != null) ? datas.size() : 0;
    }
}
