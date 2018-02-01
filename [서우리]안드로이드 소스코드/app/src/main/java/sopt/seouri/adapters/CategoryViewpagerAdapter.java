package sopt.seouri.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import sopt.seouri.search.category.CategoryRecyclerView;
import sopt.seouri.search.category.SearchCategoryResultData;

/**
 * Created by 김지원 on 2017-10-30.
 */

public class CategoryViewpagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    ArrayList<SearchCategoryResultData> itemData1, itemData2, itemData3, itemData4, itemData5;
    public CategoryViewpagerAdapter(FragmentManager fm, Context context,ArrayList<SearchCategoryResultData> itemData1, ArrayList<SearchCategoryResultData> itemData2, ArrayList<SearchCategoryResultData> itemData3, ArrayList<SearchCategoryResultData> itemData4, ArrayList<SearchCategoryResultData> itemData5) {
        super(fm);
        this.context = context;
        this.itemData1 = itemData1;
        this.itemData2 = itemData2;
        this.itemData3 = itemData3;
        this.itemData4 = itemData4;
        this.itemData5 = itemData5;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CategoryRecyclerView view1 = new CategoryRecyclerView(context,itemData1);
                return view1;
            case 1:
                CategoryRecyclerView view2 = new CategoryRecyclerView(context,itemData2);
                return view2;
            case 2:
                CategoryRecyclerView view3 = new CategoryRecyclerView(context,itemData3);
                return view3;
            case 3:
                CategoryRecyclerView view4 = new CategoryRecyclerView(context,itemData4);
                return view4;
            case 4:
                CategoryRecyclerView view5 = new CategoryRecyclerView(context,itemData5);
                return view5;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
