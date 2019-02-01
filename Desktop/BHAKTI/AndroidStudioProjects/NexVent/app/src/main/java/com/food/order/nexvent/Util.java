package com.food.order.nexvent;

import android.app.ProgressDialog;
import android.content.Context;

public class Util {

    public static ProgressDialog progressDialog;

    public static ProgressDialog getProgressDialog(Context context){
        if(progressDialog==null)progressDialog = new ProgressDialog(context);
       return progressDialog;
    }
}
