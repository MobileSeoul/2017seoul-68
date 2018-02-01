package sopt.seouri.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sopt.seouri.R;

/**
 * Created by 김지원 on 2017-10-05.
 */

public class GridAdapter extends BaseAdapter{
    private Context context;
    private String[] countryList;

    public GridAdapter(Context context, String[] countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @Override
    public int getCount() {
        return 25;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout griditem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            griditem = new RelativeLayout(context);
            griditem = (RelativeLayout) inflater.inflate(R.layout.search_griditem, null);
            TextView textView = (TextView) griditem.findViewById(R.id.griditem);
            textView.setText(countryList[position]);
//            griditem = (LinearLayout)inflater.inflate(R.layout.search_griditem_image,null);
//            ImageView imageView = (ImageView)griditem.findViewById(R.id.griditem_image);

        } else {
            griditem = (RelativeLayout) convertView;
        }

        return griditem;
    }
}
