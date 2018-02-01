package sopt.seouri.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sopt.seouri.R;

/**
 * Created by yangseunghyuk on 2017-10-08.
 */

public class ListViewAdapter extends BaseAdapter {

    ArrayList<CommentsData> commentsDatas = new ArrayList<>();

    Context context;

    public ListViewAdapter(ArrayList<CommentsData> cc,Context context)
    {
        commentsDatas = cc;
        this.context = context;
    }

    @Override
    public int getCount() {
        return commentsDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return commentsDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reply_listview_item, parent, false);
        }

        TextView R_writer = (TextView)convertView.findViewById(R.id.R_writer);
        TextView R_date = (TextView)convertView.findViewById(R.id.R_date);
        TextView R_content = (TextView)convertView.findViewById(R.id.R_content);

        CommentsData commentsData = (CommentsData)getItem(position);


        R_writer.setText(commentsData.getName().toString());
        R_content.setText(commentsData.getContent().toString());
        R_date.setText(commentsData.date.toString());


        return convertView;
    }

}
