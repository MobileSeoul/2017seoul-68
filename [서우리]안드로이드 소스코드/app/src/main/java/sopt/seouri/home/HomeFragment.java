package sopt.seouri.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dev.sacot41.scviewpager.DotsView;
import com.dev.sacot41.scviewpager.SCViewPager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.R;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.application.GPSTracker;
import sopt.seouri.home.networkData.JobinformationData;
import sopt.seouri.home.networkData.MainData;
import sopt.seouri.home.networkData.PosterData;
import sopt.seouri.network.NetworkService;
import sopt.seouri.search.detail.SearchDetailFragment;

import static sopt.seouri.MainActivity.fragmentManager;
import static sopt.seouri.MainActivity.toolbarImage;

/**
 * Created by ash on 2017-09-20.
 */

public class HomeFragment extends Fragment {
    /*뭘 의미하는지는 모르겠음*/
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String  mParam1;
    private String  mParam2;
    private SCViewPager mViewPager;
    private MPagerAdapter mPageAdapter;
    private DotsView mDotsView;
    private ArrayList<PosterData> pageDatas;
    private ImageView imageViewWeek1;
    private ImageView imageViewWeek2;
    private TextView textViewWeek1;
    private TextView textViewWeek2;
    private TextView textViewNotice1;
    private TextView textViewNotice2;
    private TextView textViewNotice3;
    private TextView textViewNotice4;
    private TextView textViewAround;
    private RelativeLayout relativeLayoutWeek1;
    private RelativeLayout relativeLayoutWeek2;
    RecyclerView recyclerView;
    JobListAdapter jobListAdapter;
    ArrayList<JobinformationData> datas;
    LinearLayoutManager linearLayoutManager;
    NetworkService service;
    Context context;
    TextView textViewJobBtn;
    double latitude;
    double longitude;

    //gps 부분
    GPSTracker gps = null;
    public Handler mHandler;

    public static int RENEW_GPS = 1;
    public static int SEND_PRINT = 2;

    public HomeFragment() {
    }
    public void setContext(Context context){
        this.context = context;
    }
    /*뭘 의미하는지는 모르겠음*/
    public static HomeFragment newInstance(String param1,String param2){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2,param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( getActivity(), new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    0 );
        }
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==RENEW_GPS){
                    makeNewGpsService();
                }
                if(msg.what==SEND_PRINT){
                    logPrint((String)msg.obj);
                }
            }
        };

    }
    public void makeNewGpsService(){
        if(gps == null) {
            gps = new GPSTracker(getActivity(),mHandler);
        }else{
            gps.Update();
        }

    }
    //// TODO: 2017-10-27 gps 날리기
    public void logPrint(String str){
        //editText.append(getTimeStr()+" "+str+"\n");
        Log.d("ash","gps "+str);
//        Toast toast = Toast.makeText(getContext(), getTimeStr()+" "+str+"\n", Toast.LENGTH_LONG);
  //      toast.show();

    }
    public String getTimeStr(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("MM/dd HH:mm:ss");
        return sdfNow.format(date);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarImage.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        pageDatas = new ArrayList<PosterData>();
        setContext(getContext());
        if(gps == null) {
            gps = new GPSTracker(getActivity(),mHandler);
        }else{
            gps.Update();
        }

        // check if GPS enabled
        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            // \n is for new line
            //Toast.makeText(getActivity(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        service = ApplicationController.getInstance().getNetworkService();
        textViewJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),JobAddActivityDialog.class);
                startActivity(intent);
            }
        });
        String token = ApplicationController.serverToken;
        Log.d("ash","serverToken "+token);
        //// TODO: 2017-10-18 gps 센서 이용하여 데이터 넣기
        Call<MainResult> mainResultCall = service.getMainResult(token,new MainData(latitude,longitude));
        mainResultCall.enqueue(new Callback<MainResult>() {
            @Override
            public void onResponse(Call<MainResult> call, final Response<MainResult> response) {
                if(response.isSuccessful()){
                    if(response.body().message.equals("Succeed in home")){
                        pageDatas=response.body().poster;
                        mPageAdapter = new MPagerAdapter(getChildFragmentManager(),pageDatas);
                        mPageAdapter.setNumberOfPage(pageDatas.size());
                        mPageAdapter.setFragmentBackgroundColor(R.color.theme_100);
                        mViewPager.setAdapter(mPageAdapter);

                        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            }

                            @Override
                            public void onPageSelected(int position) {
                                mDotsView.selectDot(position);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {
                            }
                        });
                        Glide.with(getActivity()).load(response.body().weekvillageEnterprise.get(0).photo).into(imageViewWeek1);
                        textViewWeek1.setText(response.body().weekvillageEnterprise.get(0).name);
                        Glide.with(getActivity()).load(response.body().weekvillageEnterprise.get(1).photo).into(imageViewWeek2);
                        textViewWeek2.setText(response.body().weekvillageEnterprise.get(1).name);

                        Linkify.TransformFilter transform = new Linkify.TransformFilter() {
                            @Override
                            public String transformUrl(Matcher match, String url) {
                                return "";
                            }
                        };

                        int flags = Pattern.CASE_INSENSITIVE;
                        Pattern p;
                        if(response.body().villageinformation.get(0).comment.length()>20){
                            textViewNotice1.setText(response.body().villageinformation.get(0).comment.substring(0,20)+" [...]");
                            p= Pattern.compile(response.body().villageinformation.get(0).comment.substring(0,20), flags);
                        }
                        else{
                             p= Pattern.compile(response.body().villageinformation.get(0).comment, flags);
                            textViewNotice1.setText(response.body().villageinformation.get(0).comment);
                        }
                        Linkify.addLinks(textViewNotice1, p,response.body().villageinformation.get(0).inforUrl,null,transform);

                        if(response.body().villageinformation.get(1).comment.length()>20){
                            textViewNotice2.setText(response.body().villageinformation.get(1).comment.substring(0,20)+" [...]");
                            p= Pattern.compile(response.body().villageinformation.get(1).comment.substring(0,20), flags);
                        }
                        else{
                            p= Pattern.compile(response.body().villageinformation.get(1).comment, flags);
                            textViewNotice2.setText(response.body().villageinformation.get(1).comment);
                        }
                        Linkify.addLinks(textViewNotice2, p,response.body().villageinformation.get(1).inforUrl,null,transform);

                        if(response.body().villageinformation.get(2).comment.length()>20){
                            textViewNotice3.setText(response.body().villageinformation.get(2).comment.substring(0,20)+" [...]");
                            p= Pattern.compile(response.body().villageinformation.get(2).comment.substring(0,20), flags);
                        }
                        else{
                            p= Pattern.compile(response.body().villageinformation.get(2).comment, flags);
                            textViewNotice3.setText(response.body().villageinformation.get(2).comment);
                        }
                        Linkify.addLinks(textViewNotice3, p,response.body().villageinformation.get(2).inforUrl,null,transform);

                        if(response.body().villageinformation.get(3).comment.length()>20){
                            textViewNotice4.setText(response.body().villageinformation.get(3).comment.substring(0,20)+" [...]");
                            p= Pattern.compile(response.body().villageinformation.get(3).comment.substring(0,20), flags);
                        }
                        else{
                            p= Pattern.compile(response.body().villageinformation.get(3).comment, flags);
                            textViewNotice4.setText(response.body().villageinformation.get(3).comment);
                        }
                        Linkify.addLinks(textViewNotice4, p,response.body().villageinformation.get(3).inforUrl,null,transform);
                        textViewAround.setText(response.body().distanceRec.name);
                        textViewAround.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                SearchDetailFragment searchDetailFragment = new SearchDetailFragment();
                                searchDetailFragment.setContext(context,String.valueOf(response.body().distanceRec.villageEnterpriseId));
                                transaction.replace(R.id.container,searchDetailFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        });
                        datas = response.body().jobinformation;
                        recyclerView.setHasFixedSize(true);
                        linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        jobListAdapter = new JobListAdapter(datas,getContext());
                        recyclerView.setAdapter(jobListAdapter);

                        relativeLayoutWeek1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                SearchDetailFragment searchDetailFragment = new SearchDetailFragment();
                                searchDetailFragment.setContext(context,String.valueOf(response.body().weekvillageEnterprise.get(0).villageEnterpriseId));
                                transaction.replace(R.id.container,searchDetailFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        });
                        relativeLayoutWeek2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                SearchDetailFragment searchDetailFragment = new SearchDetailFragment();
                                searchDetailFragment.setContext(context,String.valueOf(response.body().weekvillageEnterprise.get(1).villageEnterpriseId));
                                transaction.replace(R.id.container,searchDetailFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        });
                    }else{
                        Toast toast = Toast.makeText(getContext(), "서버 오류 입니다.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getContext(), response.message(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<MainResult> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "네트워크 상태를 확인하세요", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);
        mViewPager = (SCViewPager)rootView.findViewById(R.id.viewpager_main_activity);
        mDotsView = (DotsView)rootView.findViewById(R.id.dotsview_main);
        mDotsView.setDotRessource(R.drawable.yellowdot, R.drawable.whitedot);
        mDotsView.setNumberOfPage(3);
        textViewAround = (TextView)rootView.findViewById(R.id.home_around_com);
        relativeLayoutWeek1 = (RelativeLayout)rootView.findViewById(R.id.home_week_com1);
        relativeLayoutWeek2 = (RelativeLayout)rootView.findViewById(R.id.home_week_com2);
        imageViewWeek1 = (ImageView)rootView.findViewById(R.id.main_week_seouri_imge_1);
        imageViewWeek2 = (ImageView)rootView.findViewById(R.id.main_week_seouri_imge_2);
        textViewWeek1= (TextView) rootView.findViewById(R.id.main_week_seouri_text_1);
        textViewWeek2= (TextView) rootView.findViewById(R.id.main_week_seouri_text_2);
        textViewNotice1= (TextView) rootView.findViewById(R.id.main_notice_1);
        textViewNotice2= (TextView) rootView.findViewById(R.id.main_notice_2);
        textViewNotice3= (TextView) rootView.findViewById(R.id.main_notice_3);
        textViewNotice4= (TextView) rootView.findViewById(R.id.main_notice_4);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.home_job_list);
        textViewJobBtn = (TextView)rootView.findViewById(R.id.home_job_regisgter);
        //textViewJob1= (TextView) rootView.findViewById(R.id.main_job_1);
        //textViewJob2= (TextView) rootView.findViewById(R.id.main_job_2);
        return rootView;
    }
}
