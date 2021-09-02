package com.example.huiyishi.sqlcourse;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;

public class sqlmeeting extends SugarRecord implements Serializable {

   @Unique
    private Integer meetid;
    //会议名称
    private String meetingname;
    //参加人数
    private Integer numberofparticipants;
    //开始时间
    private String starttime;
    //结束时间
    private String endtime;
    //会议说明
    private String description;
    //会议室名称
    private String roomname;
    //发起者姓名
    private String reservationistname;


    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    public sqlmeeting(){}

    public sqlmeeting(Integer meetid,String meetingname, Integer numberofparticipants,String starttime,String endtime,String description,String roomname,String reservationistname){
        this.meetid=meetid;
        this.meetingname=meetingname;
        this.numberofparticipants=numberofparticipants;
        this.starttime=starttime;
        this.endtime=endtime;
        this.description=description;
        this.roomname=roomname;
        this.reservationistname=reservationistname;
    }


    public Integer getMeetid() {
        return meetid;
    }

    public void setMeetid(Integer meetid) {
        this.meetid = meetid;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public Integer getNumberofparticipants() {
        return numberofparticipants;
    }

    public void setNumberofparticipants(Integer numberofparticipants) {
        this.numberofparticipants = numberofparticipants;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getReservationistname() {
        return reservationistname;
    }

    public void setReservationistname(String reservationistname) {
        this.reservationistname = reservationistname;
    }
}
