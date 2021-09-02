package com.example.huiyishi.entity;

import java.util.Date;
import java.util.List;

public class MeetingDetailsCourse {
    //会议ID
    private Integer meetingid;
    //会议名称
    private String meetingname;
    //房间号
    private Integer roomid;
    //谁预订的
    private Integer reservationistid;
    //参加人数
    private Integer numberofparticipants;
    //开始时间
    private String starttime;
    //结束时间
    private String endtime;
    //预约时间
    private String reservationtime;
    //取消时间
    private String canceledtime;
    //会议说明
    private String description;
    //状态（0启用  1已占用）
    private Integer status;
    //取消原因
    private String canceledreason;

    private String roomname;

    private String reservationistname;


    public String getReservationistname() {
        return reservationistname;
    }

    public void setReservationistname(String reservationistname) {
        this.reservationistname = reservationistname;
    }

    public Integer getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(Integer meetingid) {
        this.meetingid = meetingid;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getReservationistid() {
        return reservationistid;
    }

    public void setReservationistid(Integer reservationistid) {
        this.reservationistid = reservationistid;
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

    public String getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(String reservationtime) {
        this.reservationtime = reservationtime;
    }

    public String getCanceledtime() {
        return canceledtime;
    }

    public void setCanceledtime(String canceledtime) {
        this.canceledtime = canceledtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCanceledreason() {
        return canceledreason;
    }

    public void setCanceledreason(String canceledreason) {
        this.canceledreason = canceledreason;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
}
