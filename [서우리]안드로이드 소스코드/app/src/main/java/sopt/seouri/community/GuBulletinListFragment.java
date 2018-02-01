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
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.R;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.network.NetworkService;

import static sopt.seouri.MainActivity.fragmentManager;
import static sopt.seouri.MainActivity.toolbarText;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuBulletinListFragment extends Fragment {


    private Context context;
    private String location;

    BulletinItem bulletinItem, bulletinItem1, bulletinItem2;
    ArrayList<BulletinItem> bulletinItemArrayList;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView2;
    B_list_Adapter b_list_adapter;

    String toolname;

    ArrayList<BulletinPostData> Data;

    private NetworkService service;
    LinearLayout Write_img;

    LinearLayout Search_img;

    public GuBulletinListFragment() {
        // Required empty public constructor
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = recyclerView2.getChildAdapterPosition(v);
//            Toast.makeText(getActivity(),bulletinItemArrayList.get(position).getB_title().toString(),Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            BulletinDetail bulletinDetail = new BulletinDetail();
            bulletinDetail.setContext(context,Data.get(position));
            transaction.addToBackStack(null);
            transaction.replace(R.id.container, bulletinDetail);
            transaction.commit();

        }
    };

    public void setContext(Context context, String location) {
        this.context = context;
        this.location = location;


        if(location.equals("1")){
            toolname = "강남구 게시판";
        }
        if(location.equals("2")){
            toolname = "강동구 게시판";
        }
        if(location.equals("3")){
            toolname = "강북구 게시판";
        }
        if(location.equals("4")){
            toolname = "강서구 게시판";
        }
        if(location.equals("5")){
            toolname = "관악구 게시판";
        }
        if(location.equals("6")){
            toolname = "광진구 게시판";
        }
        if(location.equals("7")){
            toolname = "구로구 게시판";
        }
        if(location.equals("8")){
            toolname = "금천구 게시판";
        }
        if(location.equals("9")){
            toolname = "노원구 게시판";
        }
        if(location.equals("10")){
            toolname = "도봉구 게시판";
        }
        if(location.equals("11")){
            toolname = "동대문구 게시판";
        }
        if(location.equals("12")){
            toolname = "동작구 게시판";
        }
        if(location.equals("13")){
            toolname = "마포구 게시판";
        }
        if(location.equals("14")){
            toolname = "서대문구 게시판";
        }
        if(location.equals("15")){
            toolname = "서초구 게시판";
        }
        if(location.equals("16")){
            toolname = "성동구 게시판";
        }
        if(location.equals("17")){
            toolname = "성북구 게시판";
        }
        if(location.equals("18")){
            toolname = "송파구 게시판";
        }
        if(location.equals("19")){
            toolname = "양천구 게시판";
        }
        if(location.equals("20")){
            toolname = "영등포구 게시판";
        }
        if(location.equals("21")){
            toolname = "용산구 게시판";
        }
        if(location.equals("22")){
            toolname = "은평구 게시판";
        }
        if(location.equals("23")){
            toolname = "종로구 게시판";
        }
        if(location.equals("24")){
            toolname = "중구 게시판";
        }
        if(location.equals("25")){
            toolname = "중랑구 게시판";
        }
        if(location.equals("0"))
        {
            toolname = "서울시 게시판";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarText.setText("");
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarText.setText(toolname.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_gu_bulletin_list, container, false);

        service = ApplicationController.getInstance().getNetworkService();

        recyclerView2 = (RecyclerView) v.findViewById(R.id.guBulletin);
        Write_img = (LinearLayout) v.findViewById(R.id.write_img);
//        bulletinItemArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);             //리니어레이아웃의 형태이면 방향은 수직
        recyclerView2.setLayoutManager(layoutManager);

        Data = new ArrayList<BulletinPostData>();
        String a = "1";

        Call<FindBulletinResult> findbulletinresult = service.getFindBulletinResult(ApplicationController.serverToken,location);
        findbulletinresult.enqueue(new Callback<FindBulletinResult>() {
            @Override
            public void onResponse(Call<FindBulletinResult> call, Response<FindBulletinResult> response) {
                if (response.isSuccessful()) {
                    if (response.body().message.equals("Succeed in selecting posts"))
                    {
                            Data = response.body().posts;
                            b_list_adapter = new B_list_Adapter(Data, getContext(), mClickListener);
                            recyclerView2.setAdapter(b_list_adapter);
                            b_list_adapter.notifyDataSetChanged();
//                            //                        replyRecyclerViewAdapter = new ReplyRecyclerViewAdapter(itemdata,getApplicationContext(),postData,commentDatas);
//                            //                        recyclerView.setAdapter(replyRecyclerViewAdapter);
//                            //                        replyRecyclerViewAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "조회 실패", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<FindBulletinResult> call, Throwable t) {
                Toast.makeText(getContext(), "통신 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });

        Write_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                WriteFragment writeFragment = new WriteFragment();
                writeFragment.setContext(context,location);
                transaction.addToBackStack(null);
                transaction.replace(R.id.container, writeFragment);
                transaction.commit();
            }
        });

        Search_img = (LinearLayout) v.findViewById(R.id.Search_img);
        Search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                SearchBulletinFragment searchBulletinFragment = new SearchBulletinFragment();
                searchBulletinFragment.setContext(context);
                transaction.addToBackStack(null);
                transaction.replace(R.id.container,searchBulletinFragment);
                transaction.commit();
            }
        });

        return v;
    }

}
