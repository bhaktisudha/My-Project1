package com.food.order.nexvent.Model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DynamicLoginResponse {

    @Nullable public LoginSuccessResponse loginSuccessResponse;
    @Nullable public LoginFailureResponse loginFailureResponse;
    public DynamicLoginResponse(@Nullable LoginSuccessResponse loginSuccessResponse,
                                @Nullable LoginFailureResponse loginFailureResponse) {
     this.loginFailureResponse = loginFailureResponse;
     this.loginSuccessResponse = loginSuccessResponse;
    }
}
