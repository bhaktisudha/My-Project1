package com.food.order.nexvent.Activity;




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.food.order.nexvent.Api.APIService;
import com.food.order.nexvent.Api.ApiAuthenticationClient;
import com.food.order.nexvent.Api.RetrofitClient;
import com.food.order.nexvent.Model.DynamicLoginResponse;
import com.food.order.nexvent.Model.Post;
import com.food.order.nexvent.R;
import com.food.order.nexvent.Util;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity  {

    @BindView(R.id.login)
    public Button button_login_login;

    @BindView(R.id.pwd)
    public EditText editText_login_password;

    @BindView(R.id.user)
    public EditText editText_login_username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login})
    public void onClick(View v){
        if(validateLoginDetails()){

            Retrofit retrofit= RetrofitClient.getInstance().getConnect(this,DynamicLoginResponse.class);
            if(retrofit==null) {
                Toast.makeText(this,"Please check your internet connection!",Toast.LENGTH_LONG).show();
                return;
            }
            Post post = new Post(editText_login_username.getText().toString(), editText_login_password.getText().toString());
            Util.getProgressDialog(this).show();
            retrofit.create(APIService.class).doLogin(post).enqueue(new Callback<DynamicLoginResponse>() {
                @Override
                public void onResponse(Call<DynamicLoginResponse> call, Response<DynamicLoginResponse> response) {
                    if(response.isSuccessful()){
                        if(response.body().loginSuccessResponse==null) {
                            Util.getProgressDialog(LoginActivity.this).dismiss();
                            Toast.makeText(LoginActivity.this, "Invalid username and password", Toast.LENGTH_LONG).show();
                        }else{
                            Util.getProgressDialog(LoginActivity.this).dismiss();
                            startActivity(new Intent(LoginActivity.this,RegistrationFormActivity.class));
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Something went wrong, Please try again later.",Toast.LENGTH_LONG).show();
                        Util.getProgressDialog(LoginActivity.this).dismiss();
                    }
                }

                @Override
                public void onFailure(Call<DynamicLoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Something went wrong, Please try again later.",Toast.LENGTH_LONG).show();
                    Util.getProgressDialog(LoginActivity.this).dismiss();
                }
            });



        }
    }

    private boolean validateLoginDetails() {
        Boolean isValid=true;

        if(editText_login_username.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter user name",Toast.LENGTH_LONG).show();
            isValid=false;
        }

        if(editText_login_password.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter user password",Toast.LENGTH_LONG).show();
            isValid=false;
        }

        return isValid;
    }


}

