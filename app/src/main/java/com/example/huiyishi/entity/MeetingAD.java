package com.example.huiyishi.entity;

import java.util.ArrayList;
import java.util.List;

public class MeetingAD {
    //会议ID
    public Integer meetingid;
    //会议名称
    public String meetingname;
    //房间号
    public Integer roomid;
    //谁预订的
    public Integer reservationistid;
    //参加人数
    public Integer numberofparticipants;
    //开始时间
    public String starttime;
    //结束时间
    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    public String endtime;
    //预约时间
    public String reservationtime;
    //取消时间
    public String canceledtime;
    //会议说明
    public String description;
    //状态（0启用  1已占用）
    public Integer status;
    //取消原因
    public String canceledreason;
    public String roomname;
    public List<Integer> list=new ArrayList<>();

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
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

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
