package sopt.seouri.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import sopt.seouri.R;
import sopt.seouri.adapters.GridAdapter;
import sopt.seouri.search.category.CategoryFragment;

import static sopt.seouri.MainActivity.fragmentManager;
import static sopt.seouri.MainActivity.sToolbarImage;
import static sopt.seouri.MainActivity.sToolbarLayout;
import static sopt.seouri.MainActivity.sToolbarText;

/**
 * Created by ash on 2017-09-20.
 */

public class SearchFragment extends Fragment {
    private Context context;
    private GridView gridView;
    private TextView search_all_btn;
    private String[] countryList;

    public SearchFragment() {
    }

    public void setContext(Context context){
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryList = getResources().getStringArray(R.array.seoul);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.search_fragment, container, false);

    /*    sToolbarText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                SearchPopupFragment searchPopupFragment = new SearchPopupFragment(context);
                transaction.add(R.id.container, searchPopupFragment);
                transaction.commit();
            }
        });*/
        gridView = (GridView)layout.findViewById(R.id.search_location_gridview);
        gridView.setAdapter(new GridAdapter(context, countryList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toDetailFragment(String.valueOf(position));

            }
        });

        search_all_btn = (TextView)layout.findViewById(R.id.search_all_btn);
        search_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDetailFragment("25");
            }
        });

        return layout;
    }

    private void toDetailFragment(String position){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        CategoryFragment categoryFragment = new CategoryFragment();
        categoryFragment.setContext(context, position);
        transaction.replace(R.id.container,categoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        sToolbarLayout.setVisibility(View.VISIBLE);
        sToolbarImage.setVisibility(View.VISIBLE);
        sToolbarText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sToolbarLayout.setVisibility(View.INVISIBLE);
        sToolbarImage.setVisibility(View.INVISIBLE);
        sToolbarText.setVisibility(View.INVISIBLE);
    }
}
