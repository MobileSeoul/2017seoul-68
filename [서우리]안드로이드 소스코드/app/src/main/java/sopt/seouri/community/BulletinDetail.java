package sopt.seouri.community;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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
public class BulletinDetail extends Fragment {

    Context context;
    String location;
    BulletinPostData postData;

    TextView D_writer;
    TextView D_date;
    TextView D_content;
    TextView D_reply_writer;
    TextView D_reply_content;

    TextView D_more_reply;

    ArrayList<ImageData> images;
    ArrayList<CommentsData> commentsDatas;
    ArrayList<postData> posts;

    ImageView imageView;

    ImageView contentimage;

    private NetworkService service2;

    BulletinDetailMoreReplyFragment bulletinDetailMoreReplyFragment;

    public void setContext(Context context, BulletinPostData postData) {
        this.context = context;
        this.postData = postData;
    }


    public BulletinDetail() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarText.setText("글 상세보기");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarText.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_bulletin_detail, container, false);

        //Toast.makeText(getContext(),postData.title.toString(),Toast.LENGTH_SHORT).show();

        service2 = ApplicationController.getInstance().getNetworkService();

        D_writer = (TextView)rootView.findViewById(R.id.D_writer);
        D_content = (TextView)rootView.findViewById(R.id.D_content);
        D_date = (TextView)rootView.findViewById(R.id.D_date);
        D_reply_content = (TextView)rootView.findViewById(R.id.D_reply_content);
        D_reply_writer = (TextView)rootView.findViewById(R.id.D_reply_writer);

        imageView = (ImageView)rootView.findViewById(R.id.qw);
        contentimage = (ImageView)rootView.findViewById(R.id.D_cimage);

        Glide.with(context).load(postData.profile).into(imageView);
        D_writer.setText(postData.getName());
        D_date.setText(postData.date);

        D_more_reply = (TextView)rootView.findViewById(R.id.D_more_reply);

//        Call<FindBulletinResult> getFindBulletinResult = service2.getFindBulletinResult("1");
//        getFindBulletinResult.enqueue(new Callback<FindBulletinResult>() {
//            @Override
//            public void onResponse(Call<FindBulletinResult> call, Response<FindBulletinResult> response) {
//                Toast.makeText(getContext(),"통신tdstssd",Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<FindBulletinResult> call, Throwable t) {
//                Toast.makeText(getContext(),"통신 실패",Toast.LENGTH_SHORT).show();
//
//            }
//        });

        posts = new ArrayList<>();
        commentsDatas = new ArrayList<>();
        images = new ArrayList<>();
        Call<FindBulletinDetailResult> getBulletinDetailResult = service2.getFindBulletinDetailResult(ApplicationController.serverToken,postData.postId);
        getBulletinDetailResult.enqueue(new Callback<FindBulletinDetailResult>() {
            @Override
            public void onResponse(Call<FindBulletinDetailResult> call, Response<FindBulletinDetailResult> response)
            {
                if(response.isSuccessful())
                {
                    images = response.body().images;
                    commentsDatas = response.body().comments;
                    posts = response.body().post;

                    D_content.setText(posts.get(0).content);

                    if(commentsDatas.size() == 0)
                    {
                        D_reply_writer.setText("댓글 없음");
                    }
                    if(commentsDatas.size() != 0) {
                        D_reply_writer.setText(commentsDatas.get(0).getName().toString());
                        D_reply_content.setText(commentsDatas.get(0).getContent().toString());
                    }
                    if(images.size() != 0) {
                        Glide.with(getContext()).load(images.get(0).image).into(contentimage);
                    }
                }
            }

            @Override
            public void onFailure(Call<FindBulletinDetailResult> call, Throwable t) {
                Toast.makeText(getContext(),"통신 실패",Toast.LENGTH_SHORT).show();

            }
        });

        bulletinDetailMoreReplyFragment = new BulletinDetailMoreReplyFragment();
        D_more_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                bulletinDetailMoreReplyFragment.setContext(getContext(),commentsDatas,postData,postData.profile);
                transaction.addToBackStack(null);
                transaction.replace(R.id.container,bulletinDetailMoreReplyFragment);
                transaction.commit();
            }
        });

        return rootView;
    }

}
