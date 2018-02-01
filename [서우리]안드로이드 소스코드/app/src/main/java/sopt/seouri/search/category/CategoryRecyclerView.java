package sopt.seouri.search.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import sopt.seouri.R;
import sopt.seouri.adapters.RecyclerCategoryAdapter;
import sopt.seouri.network.NetworkService;
import sopt.seouri.search.detail.SearchDetailFragment;

import static sopt.seouri.MainActivity.fragmentManager;

/**
 * Created by 김지원 on 2017-10-30.
 */

public class CategoryRecyclerView extends Fragment{
    Context context;
    ArrayList<SearchCategoryResultData> itemData;
    RecyclerView recyclerView;
    NetworkService service;

    public CategoryRecyclerView() {
    }

    @SuppressLint("ValidFragment")
    public CategoryRecyclerView(Context context, ArrayList<SearchCategoryResultData> itemData){
        this.context = context;
        this.itemData = itemData;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.category_viewpager,container,false);
        recyclerView = (RecyclerView)layout.findViewById(R.id.category_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerCategoryAdapter adapter = new RecyclerCategoryAdapter(itemData, clickListener, context);
        recyclerView.setAdapter(adapter);

        return layout;
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position;
            position = recyclerView.getChildPosition(v);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            SearchDetailFragment searchDetailFragment = new SearchDetailFragment(context, itemData.get(position).villageEnterpriseId);

            transaction.replace(R.id.container,searchDetailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
//            Toast.makeText(context,""+(position+1)+"번째 item클릭,"+itemData.get(position).villageEnterpriseId,Toast.LENGTH_SHORT).show();
        }
    };
}
