package sopt.seouri.recommend;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import sopt.seouri.R;

import static sopt.seouri.MainActivity.fragmentManager;
import static sopt.seouri.MainActivity.toolbarImage;
import static sopt.seouri.MainActivity.toolbarText;

/**
 * Created by ash on 2017-09-20.
 */

public class RecommendFragment extends Fragment {
    /*뭘 의미하는지는 모르겠음*/
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String  mParam1;
    private String  mParam2;
    Context context;
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private RelativeLayout relativeLayout3;
    private RelativeLayout relativeLayout4;
    private RelativeLayout relativeLayout5;


    public RecommendFragment() {
    }
    /*뭘 의미하는지는 모르겠음*/
    public static RecommendFragment newInstance(String param1, String param2){
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2,param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarText.setText("");
    }

    @Override
    public void onStart() {
        super.onStart();
        toolbarImage.setVisibility(View.INVISIBLE);
        toolbarText.setText("마을기업 추천코스");
      relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                RecommendData recommendData = new RecommendData("북촌 공예마을 협동 조합","북촌공예마을협동조합은 색동복주머니, 면사포, 대모나전국당초문모자합, 한국단청 침구세트, 백자각호 등 한국 전통의 물건을 생산하는 북촌공예마을 사람들이 모인 협동조합입니다. ..."
                        ,"통인 시장","통인시장은 통곡마을과 인왕산을 합쳐서 부른 동명에서 유래하였습니다. 2005년 인정시장으로 정식등록되었고, 2010년 서울시선정 <서울시 문화시장>으로 선정되었습니다. 2011년 ...","북초 한옥 체험","북촌은 경복궁과 창덕궁, 종묘 사이에 위치한 곳으로 전통 한옥이 밀집한 서울의 대표적인 전통 주거 지역이며, 많은 사적들과 문화재, 민속 자료가 있어 도심 속의 거리 박물관이라 ...",R.drawable.bookchon_text,R.drawable.bookchon,R.drawable.book
                ,94,93,96);
                RecommendDetailFragment recommendDetailFragment = new RecommendDetailFragment(recommendData);
                recommendDetailFragment.setContext(context);
                transaction.replace(R.id.container,recommendDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                RecommendData recommendData = new RecommendData("꿈 더하기 베이커리","‘꿈더하기’는 영등포 장애인부모회가 발달장애아이들을 위해 만든 희망의 일터입니다. 꿈더하기지원센터는 서울시 주민참여예산으로 설립되었으며 직업교육 프로그램을 확대·운영할 ..."
                        ,"사랑 뻥뻥 사랑","사랑뻥뻥사랑은 새마을 지역 공동 봉사단체에 기초를 두고 빵을 팔기 위해 고용하는 거싱 아니라 일자리를 창출하기 위해 빵을 파는 마을 기업입니다. 2011. 7. 13일 영등포구에서 ...","협동 조합 노느 메기","영등포구 당산동에 위치한 재활용가게인 햇살나무에는 중고 책들과 추운 겨울에 입기 좋은 옷들로 가득 차 있습니다. 계산대에는 EM비누들이 진열되어 있고 그 위 쪽 벽에는 후원자들...",R.drawable.eat_text,R.drawable.muckbang,R.drawable.muk,
                80,83,82);
                RecommendDetailFragment recommendDetailFragment = new RecommendDetailFragment(recommendData);
                recommendDetailFragment.setContext(context);
                transaction.replace(R.id.container,recommendDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                RecommendData recommendData = new RecommendData("노나매기 단체급식","동작구에서는 대부분의 공부방에서 재정과 인력 부족으로 질 낮은 급식이 제공되고 있었습니다. 이러한 문제점을 해소하기 위해 동작구 공부방협의회 정기운영위원회에서 급식 지원의 ..."
                        ,"에너지 슈퍼마켓","성대골 주민들이 만든 마을기업 ‘마을닷살림협동조합’이 운영하는 에너지 슈퍼마켓은 전국에서 유일하게 에너지를 전면에 내걸고 물품을 판매하는 곳입니다. 23㎡ 규모의 작은 매장에...","참 손길 협동조합","참손길공동체 협동조합은 시각장애인 전문안마사가 모여 이룬 자생적 협동조합입니다. 그들은 협동조합을 통해 스스로 시각장애인 전문안마사로서 전문성을 키우기 위해 노력합니다. ...\n",R.drawable.healing_text,R.drawable.heal,R.drawable.heal_bg,50,48,52);
                RecommendDetailFragment recommendDetailFragment = new RecommendDetailFragment(recommendData);
                recommendDetailFragment.setContext(context);
                transaction.replace(R.id.container,recommendDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        relativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                RecommendData recommendData = new RecommendData("나눔 카페 앤 가게","원두커피판매, 한국인과 동포들에게 필요한 각종 쌍방향 정보 및 배웁니다. (컴퓨터교육, 중국어, 문화체험, 한국역사교육, 출입국관련 업무안내 등) 사회적기업 커피볶에서 제공받는 ...\n"
                        ,"문화 예술 협동조합","2008년 '배꼽 빠지는 도서관'에서 시작된 '곁애'는 마음의 상처를 깨끗이 치료합니다고 해 '마데카솔공장'이라고도 불립니다. 그 이유는 방황하는 청소년을 문학과 예술을 통해 치유하...","","",R.drawable.coffee_text,R.drawable.cogge,R.drawable.cof,
                27,26,0);
                RecommendDetailFragment recommendDetailFragment = new RecommendDetailFragment(recommendData);
                recommendDetailFragment.setContext(context);
                transaction.replace(R.id.container,recommendDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        relativeLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                RecommendData recommendData = new RecommendData("국악 나루","국악나루가 긴 준비를 마치고 예술가와 시민이 함께하는 문화 예술 협동조합으로거듭 납니다.\n" +
                        "1997년 자주문화 운동 민족문화지킴이 천하대장군, 2007년 더많은 이들과 함께 하고자 ...\n"
                        ,"홍스 공방","홍스공방은 2009년부터 프랑스식 가죽공예 교육을 하고 있는 가죽공예 공방 입니다. 홍스공방은 '화이'와 '신의 손'등 영화와 드라마의 소품 제작에도 참여했으며 외국인 관광안내사이트...\n ","놀자 씨씨"," 지역문화 생태계 활성화를 위한 예비사회적기업 ‘놀자씨씨’ 분명 카페는 아닌 것 같은데 동네 학생들과 주부들이 자유롭게 드나들며 커피를 마시고, 밤에는 삼삼오오 사람들이 모여 ...",R.drawable.drink_text,R.drawable.drink,R.drawable.guk,
                        4,3,5);
                RecommendDetailFragment recommendDetailFragment = new RecommendDetailFragment(recommendData);
                recommendDetailFragment.setContext(context);
                transaction.replace(R.id.container,recommendDetailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.recommend_fragment, container, false);
        relativeLayout1 = (RelativeLayout)rootView.findViewById(R.id.reco_1);
        relativeLayout2 = (RelativeLayout)rootView.findViewById(R.id.reco_2);
        relativeLayout3 = (RelativeLayout)rootView.findViewById(R.id.reco_3);
        relativeLayout4 = (RelativeLayout)rootView.findViewById(R.id.reco_4);
        relativeLayout5 = (RelativeLayout)rootView.findViewById(R.id.reco_5);

        return rootView;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
