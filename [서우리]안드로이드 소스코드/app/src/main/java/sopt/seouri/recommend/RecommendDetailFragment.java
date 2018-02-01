package sopt.seouri.recommend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import sopt.seouri.R;
import sopt.seouri.search.detail.SearchDetailFragment;

import static sopt.seouri.MainActivity.fragmentManager;
import static sopt.seouri.MainActivity.mToolbar;
import static sopt.seouri.MainActivity.toggle;
import static sopt.seouri.MainActivity.toolbarText;

/**
 * Created by ash on 2017-09-20.
 */

public class RecommendDetailFragment extends Fragment {
    /*뭘 의미하는지는 모르겠음*/
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String  mParam1;
    private String  mParam2;*/
    Context context;
    RecommendData recommendData;
    ImageView imageView;
    TextView textViewRecommendCompany1;
    TextView textViewRecommendCompany2;
    TextView textViewRecommendCompany3;
    TextView textViewRecommendCompanyComment1;
    TextView textViewRecommendCompanyComment2;
    TextView textViewRecommendCompanyComment3;
    TextView textViewToolbar;
    private ImageView imageViewText;
    private ImageView imageViewCommentText;

    public RecommendDetailFragment() {
    }

    @SuppressLint("ValidFragment")
    public RecommendDetailFragment(RecommendData recommendData) {
        this.recommendData =recommendData;
    }
    /*뭘 의미하는지는 모르겠음*/
    /*public static RecommendDetailFragment newInstance(String param1, String param2){
        RecommendDetailFragment fragment = new RecommendDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2,param2);
        fragment.setArguments(args);
        return fragment;
    }*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        toolbarText.setText("");
        mToolbar.getBackground().setAlpha(0);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        //toggle.getDrawerArrowDrawable().setColor(getColor(R.color.colorPrimary));
        imageViewText.setImageResource(recommendData.recommendName);
        imageViewCommentText.setImageResource(recommendData.recommendComment);
        imageView.setImageResource(recommendData.recommendImg);
        textViewRecommendCompany1.setText(recommendData.commpanyName1);
        textViewRecommendCompany1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                SearchDetailFragment searchDetailFragment = new SearchDetailFragment();
                searchDetailFragment.setContext(context,String.valueOf(recommendData.commpanyID1));
                transaction.replace(R.id.container,searchDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        textViewRecommendCompany2.setText(recommendData.commpanyName2);
        textViewRecommendCompany2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                SearchDetailFragment searchDetailFragment = new SearchDetailFragment();
                searchDetailFragment.setContext(context,String.valueOf(recommendData.commpanyID2));
                transaction.replace(R.id.container,searchDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        textViewRecommendCompany3.setText(recommendData.commpanyName3);
        textViewRecommendCompany3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                SearchDetailFragment searchDetailFragment = new SearchDetailFragment();
                searchDetailFragment.setContext(context,String.valueOf(recommendData.commpanyID3));
                transaction.replace(R.id.container,searchDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        textViewRecommendCompanyComment1.setText(recommendData.commpanyComment1.substring(0,60)+"...");

        textViewRecommendCompanyComment2.setText(recommendData.commpanyComment2.substring(0,60)+"...");
        if(recommendData.commpanyComment3.length()>30)
        textViewRecommendCompanyComment3.setText(recommendData.commpanyComment3.substring(0,60)+"...");
        textViewRecommendCompanyComment3.setText(recommendData.commpanyComment3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.recommend_detail_fragment, container, false);
        imageView = (ImageView)rootView.findViewById(R.id.recommend_img);
        textViewRecommendCompany1 = (TextView)rootView.findViewById(R.id.reco_compa1);
        textViewRecommendCompany2 = (TextView)rootView.findViewById(R.id.reco_compa2);
        textViewRecommendCompany3 = (TextView)rootView.findViewById(R.id.reco_compa3);
        textViewRecommendCompanyComment1 = (TextView)rootView.findViewById(R.id.reco_coma_comment1);
        textViewRecommendCompanyComment2 = (TextView)rootView.findViewById(R.id.reco_coma_comment2);
        textViewRecommendCompanyComment3 = (TextView)rootView.findViewById(R.id.reco_coma_comment3);
        imageViewText = (ImageView)rootView.findViewById(R.id.recommend_text_img);
        imageViewCommentText = (ImageView)rootView.findViewById(R.id.reco_comment_img);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mToolbar.getBackground().setAlpha(255);
        toggle.getDrawerArrowDrawable().setColor(Color.rgb(55,176,166));//37B0A6
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
