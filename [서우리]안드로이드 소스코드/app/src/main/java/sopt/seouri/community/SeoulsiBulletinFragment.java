package sopt.seouri.community;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sopt.seouri.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeoulsiBulletinFragment extends Fragment {

    RecyclerView recyclerView;

    BulletinItem bulletinItem,bulletinItem1,bulletinItem2;
    ArrayList<BulletinPostData> bulletinItemArrayList;
    LinearLayoutManager layoutManager;

    B_list_Adapter b_list_adapter;

    public SeoulsiBulletinFragment() {
        // Required empty public constructor
    }

    private final View.OnClickListener mClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            int position = recyclerView.getChildAdapterPosition(v);


        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_seoulsi_bulletin, container, false);

        recyclerView = (RecyclerView)viewGroup.findViewById(R.id.seoulsiBulletin);

        bulletinItemArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);             //리니어레이아웃의 형태이면 방향은 수직
        recyclerView.setLayoutManager(layoutManager);

//        bulletinItem = new BulletinItem("작성자","제목","2015-05-01",50);
//        bulletinItem1 = new BulletinItem("작성자","제목","2015-05-01",50);
//        bulletinItem2 = new BulletinItem("작성자","제목","2015-05-01",50);
//        BulletinItem bulletinItem3 = new BulletinItem("작성자","제목","2015-05-01",50);
//
//        bulletinItemArrayList.add(bulletinItem);
//        bulletinItemArrayList.add(bulletinItem1);
//        bulletinItemArrayList.add(bulletinItem2);
//        bulletinItemArrayList.add(bulletinItem3);


        b_list_adapter = new B_list_Adapter(bulletinItemArrayList,getContext(),mClickListener);

        recyclerView.setAdapter(b_list_adapter);

        return viewGroup;
    }

}
