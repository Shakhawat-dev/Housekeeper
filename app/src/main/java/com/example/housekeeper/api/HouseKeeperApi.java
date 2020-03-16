package com.example.housekeeper.api;

import com.example.housekeeper.model.CheckedListResponse;
import com.example.housekeeper.model.ChecklistTypeResponse;
import com.example.housekeeper.model.StatusResponse;
import com.example.housekeeper.model.TaskListResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HouseKeeperApi {

    @FormUrlEncoded
    @POST("auth")
    Call<ResponseBody> loginWithMobile(
            @Field("phoneNumber") String phoneNumber,
            @Field("language") String language
    );

    @FormUrlEncoded
    @POST("tasklist")
    Call<TaskListResponse> getTaskLists(
            @Field("phoneNumber") String phoneNumber,
            @Field("language") String language,
            @Field("authToken") String authToken,
            @Field("currentDate") String currentDate,
            @Field("hotelId") String hotelId
    );

    @FormUrlEncoded
    @POST("statusUpdate")
    Call<ResponseBody> updateTaskDetails(
            @Field("phoneNumber") String phoneNumber,
            @Field("language") String language,
            @Field("authToken") String authToken,
            @Field("progressStatusKey") String spinnerSelected,
            @Field("taskId") String taskId,
            @Field("remark") String hotelId
    );

    @FormUrlEncoded
    @POST("initialization")
    Call<StatusResponse> getStatusList(
            @Field("phoneNumber") String phoneNumber,
            @Field("language") String language,
            @Field("authToken") String authToken,
            @Field("currentDate") String currentDate,
            @Field("hotelId") String hotelId
    );

    @GET("checkListType")
    Call<ChecklistTypeResponse> getCheckListTypes(
            @Query("lang") String language
    );

    @GET("checkedList")
    Call<CheckedListResponse> getCheckedList(
            @Query("organizationId") String hotelId,
            @Query("locationType") String checklistType,
            @Query("lang") String language
    );

    @FormUrlEncoded
    @POST("defectsSet")
    Call<ResponseBody> updateDefect(
            @Field("caption") String caption,
            @Field("housekeeper") Integer housekeeper,
            @Field("checkedTime") String checkedTime,
            @Field("priority") Integer priority,
            @Field("notifyDepartment") String notifyDepartment,
            @Field("isNotified") Boolean isNotified,
            @Field("organizationId") String organizationId,
            @Field("room") Integer room,
            @Field("remark") String remark,
            @Field("lang") String lang
    );
}
