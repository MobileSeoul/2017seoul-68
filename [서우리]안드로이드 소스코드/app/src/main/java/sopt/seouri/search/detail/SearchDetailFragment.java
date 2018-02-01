package sopt.seouri.search.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.R;
import sopt.seouri.adapters.RecyclerDetailAdapter;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.network.NetworkService;

import static sopt.seouri.MainActivity.toolbarText;
import static sopt.seouri.R.id.map;
import static sopt.seouri.application.ApplicationController.serverToken;

/**
 * Created by 김지원 on 2017-10-06.
 */

public class SearchDetailFragment extends Fragment{
    private Context context;
    private String villageEnterpriseId;
    private NetworkService service;
    private TextView intro, newsName, news, homepage, phone, address;
    private ImageView image, coupon;
    private RecyclerView recyclerView;
    private SearchDetailResultData itemDatas;
    private MapView mapView;
    private LinearLayout mapLayout;

    public SearchDetailFragment() {
    }
    @SuppressLint("ValidFragment")
    public SearchDetailFragment(Context context, String villageEnterpriseId) {
        this.context = context;
        this.villageEnterpriseId = villageEnterpriseId;
    }

    public void setContext(Context context, String villageEnterpriseId){
        this.context = context;
        this.villageEnterpriseId = villageEnterpriseId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        service = ApplicationController.getInstance().getNetworkService();
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.search_detail,container,false);

        intro = (TextView)layout.findViewById(R.id.search_detail_intro);
        newsName = (TextView)layout.findViewById(R.id.search_detail_news_name);
        news = (TextView)layout.findViewById(R.id.search_detail_news);
        homepage = (TextView)layout.findViewById(R.id.search_detail_homepage);
        phone = (TextView)layout.findViewById(R.id.search_detail_phone);
        address = (TextView)layout.findViewById(R.id.search_detail_address);

        image = (ImageView)layout.findViewById(R.id.search_detail_image);
        coupon = (ImageView)layout.findViewById(R.id.search_detail_coupon);

        recyclerView = (RecyclerView)layout.findViewById(R.id.search_detail_recycler_picture);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        itemDatas = new SearchDetailResultData();

        mapView = (MapView)layout.findViewById(map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

//        mapLayout = (LinearLayout)layout.findViewById(R.id.map_layout);
//        mapLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        mapLayout.getParent().requestDisallowInterceptTouchEvent(true);
//                        //버튼 다운시 동작
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        mapLayout.getParent().requestDisallowInterceptTouchEvent(false);
//                        //버튼에서 터치 업시 동작
//                        break;
//                }
//                return false;
//            }
//        });
        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        mapView.requestDisallowInterceptTouchEvent(true);
//                        //버튼 다운시 동작
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        mapView.requestDisallowInterceptTouchEvent(false);
//                        //버튼에서 터치 업시 동작
//                        break;
//                }
                mapView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


//        mapView.getMapAsync(new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(GoogleMap googleMap) {
//                            LatLng latLng = new LatLng(37.56,126.97);
//
//                            MarkerOptions markerOptions = new MarkerOptions();
//                            markerOptions.position(latLng);
//                            markerOptions.title("ㅌㅌ");
//                            markerOptions.snippet("ㅇㅇ");
//                            googleMap.addMarker(markerOptions);
//
//                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
//                        }
//                    });

        Call<SearchDetailResult> searchDetailResultCall = service.getSearchDetailResult(serverToken,villageEnterpriseId);
        searchDetailResultCall.enqueue(new Callback<SearchDetailResult>() {
            @Override
            public void onResponse(Call<SearchDetailResult> call, Response<SearchDetailResult> response) {
                if(response.isSuccessful()){
                    if(response.body().message.equals("Succeed in selecting Specific VillageEnterprise"))
                    itemDatas = response.body().specificVe;
                    toolbarText.setText(itemDatas.name);
                    Glide.with(context).load(itemDatas.photo).into(image);
                    intro.setText(itemDatas.detail);
                    newsName.setText(itemDatas.name +" 소식");
                    for(int i=0; i<itemDatas.article.size(); i++){
                        news.append(itemDatas.article.get(i).title +"\n");
                        news.append(itemDatas.article.get(i).url +"\n");
                    }
                    news.setText(itemDatas.article.get(0).title);
                    homepage.setText(itemDatas.url);
                    phone.setText(itemDatas.phone);
                    address.setText(itemDatas.address);
                    Glide.with(context).load(itemDatas.coupon).into(coupon);
                    RecyclerDetailAdapter adapter = new RecyclerDetailAdapter(itemDatas.images, context);
                    recyclerView.setAdapter(adapter);

                    final LatLng location = new LatLng((double)Double.parseDouble(itemDatas.lat), (double)Double.parseDouble(itemDatas.lng));
                    mapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(location);
                            markerOptions.title(itemDatas.name);
                            markerOptions.snippet(itemDatas.address);
                            googleMap.addMarker(markerOptions);

                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
                        }
                    });
                }else {
                    Log.d("DetailFragment 통신에러",""+"");
                }
            }

            @Override
            public void onFailure(Call<SearchDetailResult> call, Throwable t) {
                Log.d("SearchDetailFragment","통신 실패");
            }
        });

        return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        toolbarText.setVisibility(View.VISIBLE);
//        sToolbarLayout.setVisibility(View.INVISIBLE);
//        sToolbarImage.setVisibility(View.INVISIBLE);
//        sToolbarText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarText.setVisibility(View.INVISIBLE);
//        sToolbarLayout.setVisibility(View.VISIBLE);
//        sToolbarImage.setVisibility(View.VISIBLE);
//        sToolbarText.setVisibility(View.VISIBLE);
    }
}
