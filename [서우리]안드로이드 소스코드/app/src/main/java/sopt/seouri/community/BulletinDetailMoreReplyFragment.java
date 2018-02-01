package sopt.seouri.community;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.R;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.network.NetworkService;

import static sopt.seouri.MainActivity.toolbarText;
import static sopt.seouri.application.ApplicationController.serverToken;

/**
 * A simple {@link Fragment} subclass.
 */
public class BulletinDetailMoreReplyFragment extends Fragment {

    Context context;
    ArrayList<CommentsData> commentsDataArrayList;
    String profile;
    BulletinPostData postData;

    ListView listView;
    ImageView addreply;
    EditText addreply_edit;

    ListViewAdapter mMyAdapter;

    NetworkService service;

    BulletinAddCommentData bulletinAddCommentData;

    ImageView R_proimg;

    ArrayList<CommentsData> commentsDatas;


    public BulletinDetailMoreReplyFragment() {
        // Required empty public constructor
    }

    public void setContext(Context context, ArrayList<CommentsData> cData,BulletinPostData postData,String profile) {
        this.context = context;
        this.commentsDataArrayList = cData;
        this.profile = profile;
        this.postData = postData;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarText.setText("댓글");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarText.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_bulletin_detail_more_reply, container, false);

        listView = (ListView)v.findViewById(R.id.reply_list);
        addreply = (ImageView)v.findViewById(R.id.R_add_reply_img);
        addreply_edit = (EditText)v.findViewById(R.id.R_add_reply_edit);


        R_proimg = (ImageView)v.findViewById(R.id.DR_proimg);
        Glide.with(getContext()).load(profile).into(R_proimg);
        service = ApplicationController.getInstance().getNetworkService();

        mMyAdapter = new ListViewAdapter(commentsDataArrayList,getActivity());
        listView.setAdapter(mMyAdapter);

        addreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String str3 = addreply_edit.getText().toString();
                bulletinAddCommentData = new BulletinAddCommentData();

                bulletinAddCommentData.userId = Integer.parseInt(ApplicationController.memberId);
                bulletinAddCommentData.postId = postData.postId;
                bulletinAddCommentData.setContent(str3);

                Call<BulletinAddCommentResult> bulletinAddCommentResultCall = service.getBulletinAddCommentResult(serverToken,bulletinAddCommentData);
                bulletinAddCommentResultCall.enqueue(new Callback<BulletinAddCommentResult>() {
                    @Override
                    public void onResponse(Call<BulletinAddCommentResult> call, Response<BulletinAddCommentResult> response)
                    {
                        if(response.isSuccessful())
                        {

                            Toast.makeText(getContext()," 댓글 등록 성공",Toast.LENGTH_SHORT).show();
                            addreply_edit.setText("");

                            Call<FindBulletinDetailResult> getBulletinDetailResult = service.getFindBulletinDetailResult(serverToken,postData.postId);
                            getBulletinDetailResult.enqueue(new Callback<FindBulletinDetailResult>() {
                                @Override
                                public void onResponse(Call<FindBulletinDetailResult> call, Response<FindBulletinDetailResult> response)
                                {
                                    if(response.isSuccessful())
                                    {
                                        commentsDatas = response.body().comments;
                                        if(commentsDatas.size() > 0) {
                                            commentsDataArrayList.add(commentsDatas.get(commentsDatas.size() - 1));
                                        }
                                        mMyAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onFailure(Call<FindBulletinDetailResult> call, Throwable t) {
                                    Toast.makeText(getContext(),"통신 실패",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(getContext(),response.message().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<BulletinAddCommentResult> call, Throwable t) {
                        Toast.makeText(getContext()," 통신 실패",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        return v;
    }
}
