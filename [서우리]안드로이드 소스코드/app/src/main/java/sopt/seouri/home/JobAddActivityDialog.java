package sopt.seouri.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.R;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.home.networkData.JobRegData;
import sopt.seouri.home.networkData.JobResult;
import sopt.seouri.network.NetworkService;

/**
 * Created by ash on 2017-08-27.
 */

public class JobAddActivityDialog extends AppCompatActivity{

    NetworkService service;
    EditText editTextName;
    EditText editTextPay;
    EditText editTextContent;
    TextView textViewOK;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.job_add_dialog);
        service = ApplicationController.getInstance().getNetworkService();
        editTextName = (EditText)findViewById(R.id.job_reg_name);
        editTextPay = (EditText)findViewById(R.id.job_reg_pay);
        editTextContent = (EditText)findViewById(R.id.job_reg_content);
        textViewOK = (TextView)findViewById(R.id.job_add_btn);
        textViewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<JobResult> jobResultCall = service.getJobResult(ApplicationController.serverToken,new JobRegData(editTextName.getText().toString(),"??","??",editTextPay.getText().toString(),"??"));
                jobResultCall.enqueue(new Callback<JobResult>() {
                    @Override
                    public void onResponse(Call<JobResult> call, Response<JobResult> response) {
                        if(response.isSuccessful()){
                            Toast toast = Toast.makeText(getApplicationContext(), "성공적으로 등록 되었습니다. 관리자 확인 후 등록 예정입니다.", Toast.LENGTH_LONG);
                            toast.show();
                            finish();
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JobResult> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(), "네트워크 상태를 확인하세요", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    }
                });
            }
        });

    }
}
