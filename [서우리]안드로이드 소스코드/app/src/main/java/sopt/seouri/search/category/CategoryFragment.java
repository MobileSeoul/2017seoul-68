package sopt.seouri.search.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.R;
import sopt.seouri.adapters.CategoryViewpagerAdapter;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.network.NetworkService;
import sopt.seouri.search.searchvillage.SearchVillageName;
import sopt.seouri.search.searchvillage.SearchVillageResult;

import static sopt.seouri.MainActivity.fragmentManager;
import static sopt.seouri.MainActivity.sToolbarImage;
import static sopt.seouri.MainActivity.sToolbarLayout;
import static sopt.seouri.MainActivity.sToolbarText;
import static sopt.seouri.application.ApplicationController.serverToken;


public class CategoryFragment extends Fragment implements View.OnClickListener{
    private Context context;
    private String location;
    private NetworkService service;
    private ArrayList<SearchCategoryResultData> itemData1, itemData2, itemData3, itemData4, itemData5;
    private ViewPager viewpager;
    private ImageView allBtn, nollBtn, muckBtn, gooBtn, chaeBtn;
    private CategoryViewpagerAdapter viewpagerAdapter;
    private int previousBtn = 0;
    private SearchVillageName name = null;

    public CategoryFragment() {
    }

    @SuppressLint("ValidFragment")
    public CategoryFragment(Context context, SearchVillageName name){
        this.context = context;
        this.name = name;
    }

    public void setContext(Context context, String location) {
        this.context = context;
        this.location = location;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        service = ApplicationController.getInstance().getNetworkService();
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.category_fragment, container, false);

        viewpager = (ViewPager)layout.findViewById(R.id.category_viewpager);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setCurrentItem(0);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (previousBtn){
                    case 0:
                        allBtn.setImageResource(R.drawable.all_);
                        break;
                    case 1:
                        nollBtn.setImageResource(R.drawable.noll_);
                        break;
                    case 2:
                        muckBtn.setImageResource(R.drawable.muk_);
                        break;
                    case 3:
                        gooBtn.setImageResource(R.drawable.goo_);
                        break;
                    case 4:
                        chaeBtn.setImageResource(R.drawable.chae_);
                        break;
                }
                switch (position){
                    case 0:
                        allBtn.setImageResource(R.drawable.all);
                        break;
                    case 1:
                        nollBtn.setImageResource(R.drawable.noll);
                        break;
                    case 2:
                        muckBtn.setImageResource(R.drawable.muck);
                        break;
                    case 3:
                        gooBtn.setImageResource(R.drawable.goo);
                        break;
                    case 4:
                        chaeBtn.setImageResource(R.drawable.chae);
                        break;
                }
                previousBtn = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        allBtn = (ImageView)layout.findViewById(R.id.category_all);
        nollBtn = (ImageView)layout.findViewById(R.id.category_noll);
        muckBtn = (ImageView)layout.findViewById(R.id.category_muck);
        gooBtn = (ImageView)layout.findViewById(R.id.category_goo);
        chaeBtn = (ImageView)layout.findViewById(R.id.category_chae);

        allBtn.setOnClickListener(this);
        allBtn.setTag(0);
        nollBtn.setOnClickListener(this);
        nollBtn.setTag(1);
        muckBtn.setOnClickListener(this);
        muckBtn.setTag(2);
        gooBtn.setOnClickListener(this);
        gooBtn.setTag(3);
        chaeBtn.setOnClickListener(this);
        chaeBtn.setTag(4);

        itemData1 = new ArrayList<>();
        itemData2 = new ArrayList<>();
        itemData3 = new ArrayList<>();
        itemData4 = new ArrayList<>();
        itemData5 = new ArrayList<>();

        viewpagerAdapter = new CategoryViewpagerAdapter(fragmentManager, context, itemData1, itemData2, itemData3, itemData4, itemData5);
        viewpager.setAdapter(viewpagerAdapter);
        viewpagerAdapter.notifyDataSetChanged();

        return layout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.category_all:
                viewpager.setCurrentItem(0);
                break;
            case R.id.category_noll:
                viewpager.setCurrentItem(1);
                break;
            case R.id.category_muck:
                viewpager.setCurrentItem(2);
                break;
            case R.id.category_goo:
                viewpager.setCurrentItem(3);
                break;
            case R.id.category_chae:
                viewpager.setCurrentItem(4);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sToolbarLayout.setVisibility(View.VISIBLE);
        sToolbarImage.setVisibility(View.VISIBLE);
        sToolbarText.setVisibility(View.VISIBLE);

        if(name == null){
            Call<SearchCategoryResult> searchCategoryResultCall = service.getSearchCategoryResult(serverToken, location);
            searchCategoryResultCall.enqueue(new Callback<SearchCategoryResult>() {
                @Override
                public void onResponse(Call<SearchCategoryResult> call, Response<SearchCategoryResult> response) {
                    if (response.isSuccessful()) {
                        if (response.body().message.equals("Succeed in selecting location") || response.body().message.equals("Succeed in selecting total location")) {
                            //Toast.makeText(context, "카테고리 검색 성공", Toast.LENGTH_SHORT).show();
                            itemData1 = response.body().list;

                            for (int i = 0; i < itemData1.size(); i++) {
                                if (itemData1.get(i).category.equals("1")) {
                                    itemData2.add(itemData1.get(i));
                                } else if (itemData1.get(i).category.equals("3")) {
                                    itemData3.add(itemData1.get(i));
                                } else if (itemData1.get(i).category.equals("2")) {
                                    itemData4.add(itemData1.get(i));
                                } else if (itemData1.get(i).category.equals("4")) {
                                    itemData5.add(itemData1.get(i));
                                }
                            }

                            viewpagerAdapter = new CategoryViewpagerAdapter(fragmentManager, context, itemData1, itemData2, itemData3, itemData4, itemData5);
                            viewpager.setAdapter(viewpagerAdapter);
                            viewpagerAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Log.d("SearchCategory 통신 에러", "");
                    }
                }

                @Override
                public void onFailure(Call<SearchCategoryResult> call, Throwable t) {
                    Log.d("SearchCategory", "통신 실패");
                }
            });
        } else if(name != null) {
            Call<SearchVillageResult> getSearchVillageResult = service.getSearchVillageResult(serverToken, name);
            getSearchVillageResult.enqueue(new Callback<SearchVillageResult>() {
                @Override
                public void onResponse(Call<SearchVillageResult> call, Response<SearchVillageResult> response) {
                    if(response.isSuccessful()){
                        if(response.body().message.equals("Succeed in selecting keywordList")){

//                            Toast.makeText(context, "키워드 검색 성공", Toast.LENGTH_SHORT).show();
                            itemData1 = response.body().KeywordList;

                            for (int i = 0; i < itemData1.size(); i++) {
                                if (itemData1.get(i).category.equals("1")) {
                                    itemData2.add(itemData1.get(i));
                                } else if (itemData1.get(i).category.equals("3")) {
                                    itemData3.add(itemData1.get(i));
                                } else if (itemData1.get(i).category.equals("2")) {
                                    itemData4.add(itemData1.get(i));
                                } else if (itemData1.get(i).category.equals("4")) {
                                    itemData5.add(itemData1.get(i));
                                }
                            }

                            viewpagerAdapter = new CategoryViewpagerAdapter(fragmentManager, context, itemData1, itemData2, itemData3, itemData4, itemData5);
                            viewpager.setAdapter(viewpagerAdapter);
                            viewpagerAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SearchVillageResult> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sToolbarLayout.setVisibility(View.INVISIBLE);
        sToolbarImage.setVisibility(View.INVISIBLE);
        sToolbarText.setVisibility(View.INVISIBLE);
    }

}
