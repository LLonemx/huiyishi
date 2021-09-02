package com.example.huiyishi.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.sqlcourse.sqlmeeting;
import com.example.huiyishi.sqlcourse.sqluser;

import java.util.List;

public class PersonDetailsAdapter extends BaseQuickAdapter<sqluser, BaseViewHolder> {
    Context context;
    public PersonDetailsAdapter(Context context, @Nullable List<sqluser> data) {
        super(R.layout.item_wd_person_details, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, sqluser item) {
        helper.setText(R.id.wd_person_user_id,item.getEmployeeid()+"");
        helper.setText(R.id.wd_person_username,item.getEmployeeName());
        helper.setText(R.id.wd_person_user_name,item.getUsername());
        helper.setText(R.id.wd_person_phone,item.getPhone());
        helper.setText(R.id.wd_person_mail,item.getEmail());
    }
}
