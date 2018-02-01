package sopt.seouri.community;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
public class WriteFragment extends Fragment {
    private Context context;

    ImageView complete;
    EditText W_title;
    EditText W_content;

    GuBulletinListFragment guBulletinListFragment;

    ArrayList<BulletinPostData> postDatas;

    String w_title;
    String w_content;

    ImageView addImgBtn;

    NetworkService service;
    ////////////////////////////////////////////////////////////  글쓰기
    WriteData writeData;
    RequestBody title;
    RequestBody content;
    RequestBody userId;
    RequestBody location;

    // ArrayList<MultipartBody.Part> images;
    MultipartBody.Part images;
    //MultipartBody.Part images[];
//    String title;
//    String content;
//    String userId;
//    String location;

    BulletinAddPostData bulletinAddPostDataPostData;

    String slocation;

    final int REQ_CODE_SELECT_IMAGE = 100;
    String imgUrl = "";
    Uri data;
    private ProgressDialog mProgressDialog;
    TextView imgNameTextView;

    public WriteFragment() {
        // Required empty public constructor
    }

    public void setContext(Context context,String slocation) {
        this.context = context;
        this.slocation = slocation;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarText.setText("글쓰기");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toolbarText.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_write, container, false);
        //images = new ArrayList<MultipartBody.Part>();

        service = ApplicationController.getInstance().getNetworkService();

        W_title = (EditText) v.findViewById(R.id.W_title);
        W_content = (EditText) v.findViewById(R.id.W_content);
        addImgBtn = (ImageView) v.findViewById(R.id.vv);
        imgNameTextView = (TextView) v.findViewById(R.id.aa);

        guBulletinListFragment = new GuBulletinListFragment();

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("등록 중...");
        mProgressDialog.setIndeterminate(true);

        addImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });


        complete = (ImageView) v.findViewById(R.id.W_complete);
        complete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                w_content = W_content.getText().toString();
                w_title = W_title.getText().toString();

//                BulletinAddPostData bulletinAddPostData = new BulletinAddPostData();
//                bulletinAddPostData.userId = "1";
//                bulletinAddPostData.title = "게시글 제목";
//                bulletinAddPostData.setContent("게시글 내용");
//                bulletinAddPostData.location = "1";

                mProgressDialog.show();

                bulletinAddPostDataPostData = new BulletinAddPostData();
                bulletinAddPostDataPostData.title = W_title.getText().toString();
                bulletinAddPostDataPostData.setContent(W_content.getText().toString());
                bulletinAddPostDataPostData.userId = ApplicationController.memberId;
                bulletinAddPostDataPostData.location = slocation;

                title = RequestBody.create(MediaType.parse("multipart/form-data"), W_title.getText().toString());
                content = RequestBody.create(MediaType.parse("multipart/form-data"), W_content.getText().toString());
                userId = RequestBody.create(MediaType.parse("multipart/form-data"), ApplicationController.memberId);
                location = RequestBody.create(MediaType.parse("multipart/form-data"),slocation);


                if (imgUrl == "") {
                    images = null;
                } else {


                    /*
                    이미지를 리사이징하는 부분입니다.
                    리사이징하는 이유!! 안드로이드는 메모리에 민감하다고 세미나에서 말씀드렸습니다~
                    구글에서는 최소 16MByte로 정하고 있으나, 제조사 별로 또 디바이스별로 메모리의 크기는 다릅니다.
                    또한, 이미지를 서버에 업로드할 때 이미지의 크기가 크다면 시간이 오래 걸리고 데이터 소모가 큽니다!!
                     */
                    BitmapFactory.Options options = new BitmapFactory.Options();
//                       options.inSampleSize = 4; //얼마나 줄일지 설정하는 옵션 4--> 1/4로 줄이겠다

                    InputStream in = null; // here, you need to get your context.
                    try {
                        in = getActivity().getContentResolver().openInputStream(data);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                        /*inputstream 형태로 받은 이미지로 부터 비트맵을 만들어 바이트 단위로 압축
                        그이우 스트림 배열에 담아서 전송합니다.
                        */
                    Bitmap bitmap = BitmapFactory.decodeStream(in, null, options); // InputStream 으로부터 Bitmap 을 만들어 준다.
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                    // 압축 옵션( JPEG, PNG ) , 품질 설정 ( 0 - 100까지의 int형 ), 압축된 바이트 배열을 담을 스트림
                    RequestBody photoBody = RequestBody.create(MediaType.parse("images/jpg"), baos.toByteArray());
                    File photo = new File(imgUrl); // 가져온 파일의 이름을 알아내려고 사용합니다
                    images = MultipartBody.Part.createFormData("images", photo.getName(), photoBody);

                }


                Call<BulletinAddPostResult> bulletinAddPostResultCall = service.getBulletinAddPostResult(ApplicationController.serverToken, userId, title, content, images, location);
                bulletinAddPostResultCall.enqueue(new Callback<BulletinAddPostResult>() {
                    @Override
                    public void onResponse(Call<BulletinAddPostResult> call, Response<BulletinAddPostResult> response) {
                        if (response.isSuccessful()) {
                            if (response.body().message.equals("Succeed in inserting post.")) {
                                Toast.makeText(getContext(), "게시글 등록 성공", Toast.LENGTH_SHORT).show();

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                guBulletinListFragment.setContext(getContext(),slocation);
                                transaction.addToBackStack(null);
                                transaction.replace(R.id.container, guBulletinListFragment);
                                transaction.commit();

                                mProgressDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(getContext(), response.message() + "   dfdf", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<BulletinAddPostResult> call, Throwable t) {
                        Toast.makeText(getContext(), "통신 실패", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();

                    }
                });
            }
        });

        return v;
    }

    // 선택된 이미지 가져오기
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String name_Str = getImageNameToUri(data.getData());
                    imgNameTextView.setText(name_Str);
                    this.data = data.getData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                imgUrl = "";
                imgNameTextView.setText("");
            }
        }
    }

    // 선택된 이미지 파일명 가져오기
    public String getImageNameToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        imgUrl = imgPath;
        return imgName;
    }
}
