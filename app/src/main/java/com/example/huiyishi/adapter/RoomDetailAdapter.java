package com.example.huiyishi.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.RoomCourse;

import java.util.List;

public class RoomDetailAdapter extends BaseQuickAdapter<RoomCourse, BaseViewHolder> {
    Context context;
    public RoomDetailAdapter(Context context, @Nullable List<RoomCourse> data) {
        super(R.layout.item_room_details, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomCourse item) {
      helper.setText(R.id.room_id,item.getRoomid().toString());
      helper.setText(R.id.room_name,item.getRoomname());
      helper.setText(R.id.room_capacity,item.getCapacity().toString());
      helper.setText(R.id.room_description,item.getDescription());
      helper.setText(R.id.room_place,item.getRoomplace());
      helper.setText(R.id.room_type,item.getRoomtype());
    }
}
