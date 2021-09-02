package com.example.huiyishi.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.MeetingDetailsCourse;

import java.util.List;

public class HistroyMeetingAdapter extends BaseQuickAdapter<MeetingDetailsCourse, BaseViewHolder> {
    Context context;
    public HistroyMeetingAdapter( Context context, @Nullable List<MeetingDetailsCourse> data) {
        super(R.layout.item_history_meeting, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MeetingDetailsCourse item) {
        helper.setText(R.id.histroy_meeting_hy_name,item.getMeetingname());
        helper.setText(R.id.histroy_meeting_hy_time,item.getStarttime());
        helper.setText(R.id.histroy_meeting_hy_place,item.getRoomname());
        helper.addOnClickListener(R.id.histroy_meeting_hy_name);
        helper.addOnClickListener(R.id.histroy_meeting_hy_time);
        helper.addOnClickListener(R.id.histroy_meeting_hy_place);
        helper.addOnClickListener(R.id.histroy_meeting_hy_item);
    }
}
