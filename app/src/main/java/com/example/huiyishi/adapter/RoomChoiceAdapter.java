package com.example.huiyishi.adapter;

import android.content.Context;
import android.widget.CheckBox;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huiyishi.R;
import com.example.huiyishi.entity.RoomCourse;

import java.util.List;

public class RoomChoiceAdapter extends BaseQuickAdapter<RoomCourse, BaseViewHolder> {
    private static int choiceNum = -1;
    Context context;
    public RoomChoiceAdapter( Context context, @Nullable List<RoomCourse> data) {
        super(R.layout.item_yy_room_choice, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomCourse item) {
        helper.setText(R.id.room_name,item.getRoomname());
        helper.addOnClickListener(R.id.choice_room);
        CheckBox checkBox=helper.getView(R.id.room_choice_checbox);
        int position=helper.getAdapterPosition();
        checkBox.setTag(position);
        int tag=(int) checkBox.getTag();
        if(tag ==choiceNum){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }
    }
    public  void clearChecked(int position){
        choiceNum = position;
    }

}
