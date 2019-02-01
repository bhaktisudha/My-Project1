package com.food.order.nexvent.Api;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.food.order.nexvent.Model.DynamicLoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient extends Application {

    Context context;
    protected Retrofit retrofit;
    public static RetrofitClient retrofitClient;

    final String base_url = "https://nexvent-server.herokuapp.com/";

//    String encoding = com.oreilly.servlet.Base64Encoder.encode(username + ":" + password);

    public static RetrofitClient getInstance(){
        if(retrofitClient==null) retrofitClient=new RetrofitClient();
        return retrofitClient;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public <T> Retrofit getConnect(Context context, Class<T> type){

        this.context=context;

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(type, new DynamicJSONDeserializer(type.getSimpleName()))
                .create();

        //Check if internet is not available send null;
        if(!isInternetAvailable())return null;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

       OkHttpClient httpClient
                = new OkHttpClient.Builder().
                addInterceptor(new Interceptor(){

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                }).connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5,TimeUnit.MINUTES)
                .readTimeout(5,TimeUnit.MINUTES)
                .build();

//        httpClient.connectTimeoutMillis();

//        httpClient.setReadTimeout(60, TimeUnit.SECONDS);
//        httpClient.setConnectTimeout(60,TimeUnit.SECONDS);

        //Add common header :


        return retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();


    }
}
