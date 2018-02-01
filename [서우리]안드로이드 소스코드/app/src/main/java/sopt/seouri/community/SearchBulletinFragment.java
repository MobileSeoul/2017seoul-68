package sopt.seouri.community;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.R;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.community.for_search_recylcer.S_list_Adapter;
import sopt.seouri.network.NetworkService;

import static sopt.seouri.MainActivity.fragmentManager;
import static sopt.seouri.MainActivity.toolbarText;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBulletinFragment extends Fragment {

    Context context;

    ImageView search2_btn;
    EditText s_edit;
    ImageView ssimg;

    ArrayList<BulletinPostData> searchBulletinDatas;

    SendSearchBulletinData sendSearchBulletinData;

    private NetworkService service;

    public SearchBulletinFragment() {
        // Required empty public constructor
    }

    public void setContext(Context context){
        this.context = context;
    }

    RecyclerView S_recyclerview;
    LinearLayoutManager layoutManager2;
    S_list_Adapter s_list_adapter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarText.setText("");
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarText.setText("검색");
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = S_recyclerview.getChildAdapterPosition(v);
//            Toast.makeText(getActivity(),bulletinItemArrayList.get(position).getB_title().toString(),Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            BulletinDetail bulletinDetail = new BulletinDetail();
            bulletinDetail.setContext(context,searchBulletinDatas.get(position));
            transaction.replace(R.id.container, bulletinDetail);
            transaction.commit();

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_search_bulletin, container, false);

        service = ApplicationController.getInstance().getNetworkService();

        S_recyclerview = (RecyclerView)v.findViewById(R.id.Search_recyler);

        layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);             //리니어레이아웃의 형태이면 방향은 수직
        S_recyclerview.setLayoutManager(layoutManager2);

        search2_btn = (ImageView)v.findViewById(R.id.search2_btn);
        s_edit = (EditText)v.findViewById(R.id.S_edit);
        ssimg = (ImageView)v.findViewById(R.id.ssimg);

        searchBulletinDatas = new ArrayList<>();

        search2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ssimg.setVisibility(View.GONE);

                sendSearchBulletinData = new SendSearchBulletinData();
                sendSearchBulletinData.key = s_edit.getText().toString();

                Call<SearchBulletinResult> searchBulletinResultCall = service.getSearchBulletinResult(ApplicationController.serverToken,sendSearchBulletinData);
                searchBulletinResultCall.enqueue(new Callback<SearchBulletinResult>() {
                    @Override
                    public void onResponse(Call<SearchBulletinResult> call, Response<SearchBulletinResult> response) {
                        if(response.isSuccessful())
                        {
                            if(response.body().message.equals("Succeed in search"))
                            {
                                 searchBulletinDatas = response.body().searchRet;
                                if(searchBulletinDatas.size() !=0 ) {
                                    s_list_adapter = new S_list_Adapter(searchBulletinDatas, getContext(), mClickListener);
                                    S_recyclerview.setAdapter(s_list_adapter);
                                    s_list_adapter.notifyDataSetChanged();

                                }
                                else
                                {
                                    Toast.makeText(getActivity(),"일치하는 게시글 없음",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<SearchBulletinResult> call, Throwable t) {
                        Toast.makeText(getActivity(),"통신실패",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        return v;
    }

}
