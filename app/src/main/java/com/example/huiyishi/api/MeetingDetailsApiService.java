package com.example.huiyishi.api;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.MeetingAD;
import com.example.huiyishi.entity.MeetingDetailsCourse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface MeetingDetailsApiService {
    public final static  String BaseUrl="http://192.168.3.15:8080/";
    @GET("api/meetingContraller/selectMeetingBymeetingname")
    Observable<BaseModel<MeetingDetailsCourse>>getMeetingDetailsCourse(@Query("meeting_title")String meeting_title);
//需要开会议接口
    @GET("selectMeetingByemployeeid")
    Observable<BaseModel<MeetingDetailsCourse>>getMeeting(@Query("employeeid")int employeeid);
    //历史会议
    @GET("selectMeetingByemployeeidhis")
    Observable<BaseModel<MeetingDetailsCourse>> getHistroyMeeting(@Query("employeeid")int employeeid);
    //历史会议详情，根据历史会议的会议名称获得
    @GET("selectMeetingBymeetingname")
    Observable<BaseModel<MeetingDetailsCourse>> getHisMeetingBymeetingname(@Query("meetingid")Integer meetingid);
    //根据用户id查询用户预约的会议
    @GET("getmeetingoByreservationistid")
    Observable<BaseModel<MeetingDetailsCourse>> getAppointment(@Query("employeeid")Integer employeeid);

    @POST("insterMeeting")
    Observable<BaseModel<MeetingDetailsCourse>> insterMeeting(@Body MeetingAD meeting);
}
