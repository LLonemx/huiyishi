package com.example.huiyishi.entity;

public class RoomCourse {
    //会议室ID
    private Integer roomid;
    //门牌号
    private Integer roomnum;
    //会议室名称
    private String roomname;
    //最多容纳人数
    private Integer capacity;
    //当前状态(0启用  1已占用)
    private Integer status;
    //备注
    private String description;
    //会议室地点
    private String roomplace;
    //会议室类型
    private String roomtype;

    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomplace() {
        return roomplace;
    }

    public void setRoomplace(String roomplace) {
        this.roomplace = roomplace;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }
}
