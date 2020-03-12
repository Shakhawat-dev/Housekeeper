package com.example.housekeeper.api;

import com.example.housekeeper.model.ChecklistTypeResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HouseKeeperApi {

    @FormUrlEncoded
    @POST("auth")
    Call<ResponseBody> loginWithMobile(
            @Field("phoneNumber") String phoneNumber,
            @Field("language") String language
    );

    @FormUrlEncoded
    @POST("checkListType")
    Call<ChecklistTypeResponse> getCheckListTypes(
            @Field("lang") String language
    );
}
