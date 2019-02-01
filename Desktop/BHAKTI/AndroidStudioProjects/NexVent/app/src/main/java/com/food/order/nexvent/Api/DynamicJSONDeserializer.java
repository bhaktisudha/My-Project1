package com.food.order.nexvent.Api;

import com.food.order.nexvent.Model.DynamicLoginResponse;
import com.food.order.nexvent.Model.LoginFailureResponse;
import com.food.order.nexvent.Model.LoginSuccessResponse;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DynamicJSONDeserializer implements JsonDeserializer<Object> {

    private final String tag;

    DynamicJSONDeserializer(String tag){
        this.tag = tag;
    }
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (tag.equals(DynamicLoginResponse.class.getSimpleName())){
            // Based on the structure you check if the data is valid or not
            // Example for the above defined structures:
            // Get JsonObject
            final JsonObject jsonObject = json.getAsJsonObject();
            if (jsonObject.has("error")) {
                LoginFailureResponse errorMessage = new Gson().fromJson(jsonObject, LoginFailureResponse.class);
                return new DynamicLoginResponse(null,errorMessage);
            } else {
                LoginSuccessResponse successResponse = new Gson().fromJson(jsonObject,LoginSuccessResponse.class);
                return new DynamicLoginResponse(successResponse,null);
            }

        }
        return null;
    }
}
