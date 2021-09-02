package com.example.huiyishi.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.sqlcourse.sqlmeeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingDetailsAdapter extends BaseQuickAdapter<sqlmeeting, BaseViewHolder> {
    Context context;
    public MeetingDetailsAdapter(Context context, @Nullable ArrayList<sqlmeeting> data) {
        super(R.layout.item_meeting_details, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, sqlmeeting item) {
        helper.setText(R.id.meeting_title,item.getMeetingname());
        helper.setText(R.id.meeting_introduce,item.getDescription());
        helper.setText(R.id.room_name,item.getRoomname());
        helper.setText(R.id.user_name,item.getReservationistname());
        helper.setText(R.id.meeting_start_time,item.getStarttime());
        helper.setText(R.id.meeting_end_time,item.getEndtime());
    }
}
