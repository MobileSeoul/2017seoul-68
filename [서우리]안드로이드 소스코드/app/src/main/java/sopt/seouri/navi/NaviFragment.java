package sopt.seouri.navi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import sopt.seouri.MainActivity;
import sopt.seouri.MyColor;
import sopt.seouri.R;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.ask.AskFragment;
import sopt.seouri.community.CommunityFragment;
import sopt.seouri.home.HomeFragment;
import sopt.seouri.mypage.MyPageFragment;
import sopt.seouri.recommend.RecommendFragment;
import sopt.seouri.search.SearchFragment;

import static sopt.seouri.MainActivity.fragmentManager;

/**
 * Created by ash on 2017-09-20.
 */

public class NaviFragment extends Fragment {
    public View viewHomeTop;
    public View viewHomeBottom;
    public View viewSearchTop;
    public View viewSearchBottom;
    public View viewRecoTop;
    public View viewRecoBottom;
    public View viewComuTop;
    public View viewComuBottom;
    public View viewQuestionTop;
    public View viewQuestionBottom;
    public LinearLayout linearLayoutHome;
    public LinearLayout linearLayoutSearch;
    public LinearLayout linearLayoutReco;
    public LinearLayout linearLayoutComu;
    public LinearLayout linearLayoutQuestion;
    public TextView textViewMyPageBtn;
    public CircularImageView circularImageView;

    public NaviFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        linearLayoutHome.setOnClickListener(clickListner1);
        linearLayoutSearch.setOnClickListener(clickListner2);
        linearLayoutComu.setOnClickListener(clickListner3);
        linearLayoutReco.setOnClickListener(clickListner4);
        linearLayoutQuestion.setOnClickListener(clickListener5);
        textViewMyPageBtn.setOnClickListener(clickListener);
        circularImageView.setOnClickListener(clickListener);

        Log.d("ash", "userImg" + ApplicationController.memberImg);
        Glide.with(getActivity()).load(ApplicationController.memberImg).into(circularImageView);
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            MyPageFragment myPageFragment = new MyPageFragment();
            myPageFragment.setContext(getContext());
            transaction.replace(R.id.container, myPageFragment);
            transaction.commit();
            MainActivity.drawer.closeDrawer(GravityCompat.START);
        }
    };
    public View.OnClickListener clickListner1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewHomeBottom.setBackgroundColor(MyColor.ORANGE);
            viewHomeTop.setBackgroundColor(MyColor.ORANGE);
            viewComuBottom.setBackgroundColor(Color.WHITE);
            viewComuTop.setBackgroundColor(Color.WHITE);
            viewSearchBottom.setBackgroundColor(Color.WHITE);
            viewSearchTop.setBackgroundColor(Color.WHITE);
            viewRecoBottom.setBackgroundColor(Color.WHITE);
            viewRecoTop.setBackgroundColor(Color.WHITE);
            viewQuestionTop.setBackgroundColor(Color.WHITE);
            viewQuestionBottom.setBackgroundColor(Color.WHITE);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setContext(getContext());
            transaction.replace(R.id.container, homeFragment);
            transaction.commit();
            MainActivity.drawer.closeDrawer(GravityCompat.START);
        }
    };
    public View.OnClickListener clickListner2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewHomeBottom.setBackgroundColor(Color.WHITE);
            viewHomeTop.setBackgroundColor(Color.WHITE);
            viewSearchTop.setBackgroundColor(MyColor.ORANGE);
            viewSearchBottom.setBackgroundColor(MyColor.ORANGE);
            viewComuBottom.setBackgroundColor(Color.WHITE);
            viewComuTop.setBackgroundColor(Color.WHITE);
            viewRecoBottom.setBackgroundColor(Color.WHITE);
            viewRecoTop.setBackgroundColor(Color.WHITE);
            viewQuestionTop.setBackgroundColor(Color.WHITE);
            viewQuestionBottom.setBackgroundColor(Color.WHITE);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            SearchFragment searchFragment = new SearchFragment();
            searchFragment.setContext(getContext());
            transaction.replace(R.id.container, searchFragment);
            transaction.commit();
            MainActivity.drawer.closeDrawer(GravityCompat.START);
        }
    };
    public View.OnClickListener clickListner3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewHomeBottom.setBackgroundColor(Color.WHITE);
            viewHomeTop.setBackgroundColor(Color.WHITE);
            viewSearchBottom.setBackgroundColor(Color.WHITE);
            viewSearchTop.setBackgroundColor(Color.WHITE);
            viewRecoBottom.setBackgroundColor(Color.WHITE);
            viewRecoTop.setBackgroundColor(Color.WHITE);
            viewComuBottom.setBackgroundColor(MyColor.ORANGE);
            viewComuTop.setBackgroundColor(MyColor.ORANGE);
            viewQuestionTop.setBackgroundColor(Color.WHITE);
            viewQuestionBottom.setBackgroundColor(Color.WHITE);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            CommunityFragment communityFragment = new CommunityFragment();
            communityFragment.setContext(getContext());
            transaction.replace(R.id.container, communityFragment);
            transaction.commit();
            MainActivity.drawer.closeDrawer(GravityCompat.START);
        }
    };
    public View.OnClickListener clickListner4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewHomeBottom.setBackgroundColor(Color.WHITE);
            viewHomeTop.setBackgroundColor(Color.WHITE);
            viewSearchBottom.setBackgroundColor(Color.WHITE);
            viewSearchTop.setBackgroundColor(Color.WHITE);
            viewRecoBottom.setBackgroundColor(MyColor.ORANGE);
            viewRecoTop.setBackgroundColor(MyColor.ORANGE);
            viewComuBottom.setBackgroundColor(Color.WHITE);
            viewComuTop.setBackgroundColor(Color.WHITE);
            viewQuestionTop.setBackgroundColor(Color.WHITE);
            viewQuestionBottom.setBackgroundColor(Color.WHITE);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            RecommendFragment recommendFragment = new RecommendFragment();
            recommendFragment.setContext(getContext());
            transaction.replace(R.id.container, recommendFragment);
            transaction.commit();

            MainActivity.drawer.closeDrawer(GravityCompat.START);
        }
    };

    public View.OnClickListener clickListener5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewHomeBottom.setBackgroundColor(Color.WHITE);
            viewHomeTop.setBackgroundColor(Color.WHITE);
            viewSearchBottom.setBackgroundColor(Color.WHITE);
            viewSearchTop.setBackgroundColor(Color.WHITE);
            viewRecoBottom.setBackgroundColor(Color.WHITE);
            viewRecoTop.setBackgroundColor(Color.WHITE);
            viewComuBottom.setBackgroundColor(Color.WHITE);
            viewComuTop.setBackgroundColor(Color.WHITE);
            viewQuestionTop.setBackgroundColor(MyColor.ORANGE);
            viewQuestionBottom.setBackgroundColor(MyColor.ORANGE);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            AskFragment askFragment = new AskFragment();
            askFragment.setContext(getContext());
            transaction.replace(R.id.container, askFragment);
            transaction.commit();

            MainActivity.drawer.closeDrawer(GravityCompat.START);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.navi_fragment, container, false);

        viewHomeTop = (View) rootView.findViewById(R.id.drawer_home_line_top);
        viewHomeBottom = (View) rootView.findViewById(R.id.drawer_home_line_bottom);
        viewSearchTop = (View) rootView.findViewById(R.id.drawer_search_line_top);
        viewSearchBottom = (View) rootView.findViewById(R.id.drawer_search_line_bottom);
        viewRecoTop = (View) rootView.findViewById(R.id.drawer_reco_line_top);
        viewRecoBottom = (View) rootView.findViewById(R.id.drawer_reco_line_bottom);
        viewComuTop = (View) rootView.findViewById(R.id.drawer_comu_line_top);
        viewComuBottom = (View) rootView.findViewById(R.id.drawer_comu_line_bottom);
        viewQuestionTop = (View) rootView.findViewById(R.id.drawer_qu_line_top);
        viewQuestionBottom = (View) rootView.findViewById(R.id.drawer_qu_line_bottom);
        linearLayoutHome = (LinearLayout) rootView.findViewById(R.id.drawer_home_layout);
        linearLayoutSearch = (LinearLayout) rootView.findViewById(R.id.drawer_search_layout);
        linearLayoutReco = (LinearLayout) rootView.findViewById(R.id.drawer_reo_layout);
        linearLayoutComu = (LinearLayout) rootView.findViewById(R.id.drawer_comu_layout);
        linearLayoutQuestion = (LinearLayout) rootView.findViewById(R.id.drawer_ques_layout);
        textViewMyPageBtn = (TextView) rootView.findViewById(R.id.drawer_mypage_btn);
        circularImageView = (CircularImageView) rootView.findViewById(R.id.drawer_myimage);


        return rootView;
    }
}
