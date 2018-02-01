package sopt.seouri.ask;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sopt.seouri.R;

public class AskViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewContent;
    public TextView textViewTime;
    public TextView textViewState;


    public AskViewHolder(View itemView) {
        super(itemView);
        textViewContent = (TextView)itemView.findViewById(R.id.ask_content);
        textViewState = (TextView)itemView.findViewById(R.id.ask_state);
        textViewTime = (TextView) itemView.findViewById(R.id.ask_time);

    }
}
