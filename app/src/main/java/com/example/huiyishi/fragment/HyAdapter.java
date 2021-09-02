package com.example.huiyishi.fragment;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.sqlcourse.sqlmeeting;

import java.util.List;

public class HyAdapter extends BaseQuickAdapter<sqlmeeting,BaseViewHolder >{
    Context context;
    public HyAdapter(Context context, @Nullable List<sqlmeeting> data) {
        super(R.layout.item_hy,data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, sqlmeeting item) {
        helper.setText(R.id.hy_name,item.getMeetingname());
        helper.setText(R.id.hy_time,item.getStarttime());
        helper.setText(R.id.hy_place,item.getRoomname());
        helper.addOnClickListener(R.id.hy_name);
        helper.addOnClickListener(R.id.hy_time);
        helper.addOnClickListener(R.id.hy_place);
        helper.addOnClickListener(R.id.hy_item);
    }
    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/

}
