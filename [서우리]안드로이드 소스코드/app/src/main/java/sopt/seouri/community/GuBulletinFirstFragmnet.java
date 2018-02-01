package sopt.seouri.community;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import sopt.seouri.R;

import static sopt.seouri.MainActivity.fragmentManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuBulletinFirstFragmnet extends Fragment {


    public GuBulletinFirstFragmnet() {
        // Required empty public constructor
    }

    LinearLayout dobong;
    GuBulletinListFragment guBulletinListFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_gu_bulletin_first_fragmnet, container, false);

        guBulletinListFragment = new GuBulletinListFragment();

        dobong = (LinearLayout)viewGroup.findViewById(R.id.dobong);
        dobong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentChanged(1);
            }
        });

        return viewGroup;
    }
    public void onFragmentChanged(int index)
    {
        if(index == 1)
        {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container,guBulletinListFragment);///gubulletinmain
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
