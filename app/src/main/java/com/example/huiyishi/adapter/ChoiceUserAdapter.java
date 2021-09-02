package com.example.huiyishi.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.ChoiceUserCourse;
import com.example.huiyishi.entity.MeetingDetailsCourse;

import java.util.List;

public class ChoiceUserAdapter  extends BaseQuickAdapter<ChoiceUserCourse,BaseViewHolder> {
    Context context;
    public ChoiceUserAdapter(Context context, @Nullable List<ChoiceUserCourse> data) {
        super(R.layout.item_yy_meeting_choice_user, data);
        this.context=context;

    }

    @Override
    protected void convert(BaseViewHolder helper, ChoiceUserCourse item) {
        helper.setText(R.id.choice_user_name,item.getEmployeeName());
        helper.addOnClickListener(R.id.item_meeting_user_choice);

    }



}
