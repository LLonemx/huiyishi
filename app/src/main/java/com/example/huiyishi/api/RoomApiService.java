package com.example.huiyishi.api;

import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.Loginjson;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.entity.RoomCourse;
import com.example.huiyishi.entity.UserCourse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RoomApiService {
    public  final  static String BaseUrl="http://192.168.3.15:8080/";



    //根据人数筛选会议室接口哦
    @GET("selectRoomBynumber")
    Observable<BaseModel<RoomCourse>> getRoom(@Query("number")Integer number);
   //根据会议室名字展示会议室信息
    @GET("/selectRoomByroomname")
    Observable<BaseModel<RoomCourse>> getRoomByroomname(@Query("roomname") String roomname);
   //根据会议室id，开始时间，结束时间判断是否有会议室冲突
    @GET("/selectMeetingByroomnameAndstarttimeAndendtime")
    Observable<BaseModel<MeetingDetailsCourse>> getMeetingbyst(@Query("roomid")Integer roomid,@Query("starttime")String starttime,@Query("endtime") String endtime);
}
