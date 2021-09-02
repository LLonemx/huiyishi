package com.example.huiyishi.api;

import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.ChoiceUserCourse;
import com.example.huiyishi.entity.UserMeetingCourse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface UserMeeetingApiService {

 public final  static String Baseurl="http://192.168.3.15:8080/";

    @GET("getAllUser")
        Observable<BaseModel<ChoiceUserCourse>> getUserAll();
}
