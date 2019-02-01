package com.food.order.nexvent.Api;

import com.food.order.nexvent.Model.DynamicLoginResponse;
import com.food.order.nexvent.Model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("login")
    Call<DynamicLoginResponse> doLogin(@Body Post post);
}
