package com.example.huiyishi.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.MeetingDetailsCourse;

import java.util.List;

public class wdapponintmentAdapter extends BaseQuickAdapter<MeetingDetailsCourse, BaseViewHolder> {
    Context context;
    public wdapponintmentAdapter(Context context, @Nullable List<MeetingDetailsCourse> data) {
        super(R.layout.item_wd_appointment, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MeetingDetailsCourse item) {
        helper.setText(R.id.appointment_hy_name,item.getMeetingname());
        helper.setText(R.id.appointment_hy_time,item.getStarttime());
        helper.setText(R.id.appointment_hy_place,item.getRoomname());
    }
}
