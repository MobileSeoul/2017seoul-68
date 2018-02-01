package sopt.seouri;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import sopt.seouri.community.CommunityFragment;
import sopt.seouri.home.HomeFragment;
import sopt.seouri.mypage.MyPageFragment;
import sopt.seouri.navi.NaviFragment;
import sopt.seouri.recommend.RecommendFragment;
import sopt.seouri.search.SearchFragment;
import sopt.seouri.search.category.CategoryFragment;
import sopt.seouri.search.searchvillage.SearchVillageName;

import static sopt.seouri.R.id.toolbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static FragmentManager fragmentManager;

    private Fragment fragmentHome;
    private SearchFragment fragmentSearch;
    private Fragment fragmentRecommend;
    private Fragment fragmentCommunity;
    private Fragment fragmentMyPage;
    private NaviFragment naviFragment;
    public static DrawerLayout drawer;
    public static Toolbar mToolbar;
    public static ImageView toolbarImage, sToolbarImage;
    public static TextView toolbarText;
    public static EditText sToolbarText;
    public static RelativeLayout sToolbarLayout;
    public static ImageView imageViewDdang;
    public static ActionBarDrawerToggle toggle;
    public static MainActivity mainActivity;

//    public static ArrayList<Fragment> fragmentStack;
    // 뒤로버튼 두번 터치시 종료 이벤트 관련 변수
    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        imageViewDdang = (ImageView)findViewById(R.id.toolbar_ddang);
        imageViewDdang.setVisibility(View.INVISIBLE);
         drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); //툴바 글씨 지우기
        toolbarImage = (ImageView)findViewById(R.id.toolbar_img);
        toolbarText = (TextView)findViewById(R.id.toolbar_text);

        // 검색화면 툴바
        sToolbarLayout = (RelativeLayout) findViewById(R.id.bar_search_layout);
        sToolbarImage = (ImageView) findViewById(R.id.bar_search_btn);
        sToolbarText = (EditText) findViewById(R.id.bar_search_text);
        sToolbarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                SearchVillageName name = new SearchVillageName();
                name.name = sToolbarText.getText().toString();
                transaction.replace(R.id.container,new CategoryFragment(getApplicationContext(), name));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        SearchPopupFragment searchPopupFragment = new SearchPopupFragment(getApplicationContext());
//        transaction.add(R.id.container, searchPopupFragment);
//        transaction.commit();

        fragmentHome = new HomeFragment();
        fragmentSearch = new SearchFragment();
        fragmentSearch.setContext(getApplicationContext());
        fragmentRecommend = new RecommendFragment();
        fragmentCommunity = new CommunityFragment();
        fragmentMyPage = new MyPageFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragmentHome);
//        transaction.addToBackStack(null);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer,  mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
       /* toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),, getApplicationContext().getTheme());
        toggle.setHomeAsUpIndicator(drawable);*/
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
//        fragmentStack = new ArrayList<>();

        naviFragment = (NaviFragment)getSupportFragmentManager().findFragmentById(R.id.navi_fragment);

    }
/*
    View.OnClickListener clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Log.d("ash","click??");
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction2 = fragmentManager.beginTransaction();
            for(int i=0; i<fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }
            transaction2.replace(R.id.container,fragmentSearch);
            transaction2.commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        }
    };*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // addToBackStack(null) 실행 시 쌓인 fragment 스택을 꺼내주는 함수
            if(fragmentManager.getBackStackEntryCount() > 0){
                fragmentManager.popBackStack();
            } else {
                if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                    backKeyPressedTime = System.currentTimeMillis();
                    toast = Toast.makeText(getApplicationContext(), "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                    this.finish();
                    toast.cancel();
                }
            }
        }
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        int id = item.getItemId();

        for(int i=0; i<fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
        sToolbarLayout.setVisibility(View.INVISIBLE);
        sToolbarImage.setVisibility(View.INVISIBLE);
        sToolbarText.setVisibility(View.INVISIBLE);

        if (id == R.id.nav_camera) {
            transaction.replace(R.id.container,fragmentHome);

//            fragmentStack.removeAll(fragmentStack);
        } else if (id == R.id.nav_gallery) {
            transaction.replace(R.id.container,fragmentSearch);
//            fragmentStack.removeAll(fragmentStack);
        } else if (id == R.id.nav_slideshow) {
            transaction.replace(R.id.container,fragmentRecommend);
//            fragmentStack.removeAll(fragmentStack);
        } else if (id == R.id.nav_manage) {
            transaction.replace(R.id.container,fragmentCommunity);
//            fragmentStack.removeAll(fragmentStack);

        } else if (id == R.id.nav_share) {
            transaction.replace(R.id.container,fragmentMyPage);
        } else if (id == R.id.nav_send) {

        }
//        transaction.addToBackStack(null);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;


    }
}
