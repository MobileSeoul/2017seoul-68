package sopt.seouri.search.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import sopt.seouri.R;
import sopt.seouri.adapters.SearchPopupAdapter;

/**
 * Created by 김지원 on 2017-10-27.
 */

public class SearchPopupFragment extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<SearchPopupResultData> searchPopupitemDatas;

    public SearchPopupFragment() {
    }
    @SuppressLint("ValidFragment")
    public SearchPopupFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.search_popup_fragment,container,false);
        recyclerView = (RecyclerView)layout.findViewById(R.id.search_bar_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        searchPopupitemDatas = new ArrayList<>();
        final SearchPopupAdapter adapter = new SearchPopupAdapter(searchPopupitemDatas,context);
        recyclerView.setAdapter(adapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "아이템 클릭", Toast.LENGTH_SHORT).show();
            }
        });
/*
        sToolbarText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                searchPopupitemDatas.clear();
//                for(int i=0; i<popupDatas.size(); i++){
//                    if(sToolbarText.getText().toString().equals(popupDatas.get(i))){
//                        searchPopupitemDatas.add(popupDatas.get(i));
//                    }
//                }
                searchPopupitemDatas.add(popupDatas.get(0));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
//        recyclerView = (RecyclerView)layout.findViewById(R.id.search_detail_recycler_picture);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
//        itemDatas = new SearchDetailResultData();
        return layout;
    }
}
