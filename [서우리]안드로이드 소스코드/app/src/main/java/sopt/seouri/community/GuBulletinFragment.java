package sopt.seouri.community;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import sopt.seouri.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuBulletinFragment extends Fragment {

    GridView gridView2;

    public GuBulletinFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LinearLayout guView = (LinearLayout) inflater.inflate(R.layout.fragment_gu_bulletin, container, false);


        return guView;
    }
}
