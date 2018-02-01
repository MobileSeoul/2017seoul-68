package sopt.seouri.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sopt.seouri.R;
import sopt.seouri.home.networkData.PosterData;

/**
 * Created by ash on 2017-09-23.
 */

public class PageFragment extends Fragment{
    ArrayList<PosterData> mDatas;
    ImageView imageView;
    int position;
    public static PageFragment create(ArrayList<PosterData> datas, int position) {

        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putSerializable("pageDatas", datas);
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDatas = (ArrayList<PosterData>) getArguments().getSerializable("pageDatas");
        position = getArguments().getInt("position");
        Glide.with(getActivity()).load(mDatas.get(position).image).into(imageView);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_fragment, container, false);
        imageView = (ImageView)rootView.findViewById(R.id.main_image_poster);
        return rootView;
    }
}
