package com.example.huiyishi.api;

import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.Employee;
import com.example.huiyishi.entity.Loginjson;
import com.example.huiyishi.entity.UserCourse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApiService {
    public  final  static String BaseUrl="http://192.168.3.15:8080/";
    @GET("androidlogin")
    Observable<Loginjson<UserCourse>> getLogin(@Query("username") String username, @Query("psd") String password); // string类型参数);

    @POST("androidsign")
    Observable<BaseModel<Employee>> getSign(@Body Employee employee);
}
