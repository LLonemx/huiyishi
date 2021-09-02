package com.example.huiyishi.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.MeetingDetailsCourse;

import java.util.List;

public class HisMeetDetailsAdapter extends BaseQuickAdapter<MeetingDetailsCourse, BaseViewHolder> {
    Context context;
    public HisMeetDetailsAdapter(Context context, @Nullable List<MeetingDetailsCourse> data) {
        super(R.layout.item_wd_history_meeting_details, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeetingDetailsCourse item) {
        helper.setText(R.id.histroy_meeting_details_meetingid,item.getMeetingid().toString());
        helper.setText(R.id.histroy_meeting_details_meetingname,item.getMeetingname());
        helper.setText(R.id.histroy_meeting_details_room_name,item.getRoomname());
        helper.setText(R.id.histroy_meeting_details_user_name,item.getReservationistname());
        helper.setText(R.id.histroy_meeting_details_meeting_start_time,item.getStarttime());
        helper.setText(R.id.histroy_meeting_details_meeting_end_time,item.getEndtime());
        helper.setText(R.id.histroy_meeting_details_meeting_introduce,item.getDescription());
    }
}
