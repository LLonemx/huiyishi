package com.example.huiyishi.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.ChoiceUserCourse;

import java.util.List;

public class yyChoiceUserAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    Context context;
    public yyChoiceUserAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.item_yy_choice_user, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.yy_choice_user_name,item);
        helper.addOnClickListener(R.id.Choice_user_rec);
    }
}
