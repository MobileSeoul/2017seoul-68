package sopt.seouri.ask;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.R;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.network.NetworkService;

import static sopt.seouri.MainActivity.fragmentManager;
import static sopt.seouri.MainActivity.imageViewDdang;
import static sopt.seouri.MainActivity.toolbarImage;
import static sopt.seouri.MainActivity.toolbarText;

/**
 * Created by ash on 2017-09-20.
 */

public class AskFragment extends Fragment {
    Context context;
    private RecyclerView recyclerView;
    AskListAdapter askListAdapter;
    ArrayList<MyQuestion> datas;
    LinearLayoutManager linearLayoutManager;
    NetworkService service;

    public AskFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new ArrayList<MyQuestion>();
        service = ApplicationController.getInstance().getNetworkService();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarText.setText("");
        imageViewDdang.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        toolbarImage.setVisibility(View.INVISIBLE);
        imageViewDdang.setVisibility(View.VISIBLE);
        toolbarText.setText("문의하기");
        imageViewDdang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                AskPostFragment askPostFragment = new AskPostFragment();
                askPostFragment.setContext(context);
                transaction.replace(R.id.container,askPostFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        Log.d("ash3734","here??!!!");
        Call<AskResult> askResultCall = service.getAskResult(ApplicationController.serverToken,ApplicationController.memberId);
        askResultCall.enqueue(new Callback<AskResult>() {
            @Override
            public void onResponse(Call<AskResult> call, Response<AskResult> response) {
                if (response.isSuccessful()){

                        datas = response.body().myquestion;
                        recyclerView.setHasFixedSize(true);
                        linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        askListAdapter = new AskListAdapter(datas,getContext());
                        recyclerView.setAdapter(askListAdapter);

                }else{
                    Toast toast = Toast.makeText(getContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AskResult> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "네트워크 상태를 확인하세요", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

      }

    @Override
    public void onResume() {
        super.onResume();
        service = ApplicationController.getInstance().getNetworkService();

        Call<AskResult> askResultCall = service.getAskResult(ApplicationController.serverToken,ApplicationController.memberId);
        askResultCall.enqueue(new Callback<AskResult>() {
            @Override
            public void onResponse(Call<AskResult> call, Response<AskResult> response) {
                if (response.isSuccessful()){

                    datas = response.body().myquestion;
                    recyclerView.setHasFixedSize(true);
                    linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    askListAdapter = new AskListAdapter(datas,getContext());
                    recyclerView.setAdapter(askListAdapter);

                }else{
                    Toast toast = Toast.makeText(getContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<AskResult> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "네트워크 상태를 확인하세요", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ask_fragment, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.ask_recyclerView);


        return rootView;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
