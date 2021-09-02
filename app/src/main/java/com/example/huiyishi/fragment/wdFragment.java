package com.example.huiyishi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.huiyishi.Histroy_meetingActivity;
import com.example.huiyishi.LoginActivity;
import com.example.huiyishi.PersonDetailsActivity;
import com.example.huiyishi.R;
import com.example.huiyishi.sqlcourse.sqlmeeting;
import com.example.huiyishi.sqlcourse.sqluser;
import com.example.huiyishi.sqlite.App;
import com.example.huiyishi.wdappointmentActivity;
import com.example.huiyishi.entity.UserCourse;
import com.orm.SugarContext;

import java.util.List;

public class wdFragment extends Fragment implements View.OnClickListener {
    ImageView user_img;
    TextView textViewUser;
    TextView textViewsj;
    UserCourse model=new UserCourse();
    LinearLayout wdappintment;
    TextView textView;
    LinearLayout histroymeeting;
    LinearLayout logout;
    LinearLayout person;
    App app;

LoginActivity loginActivity;
    @Nullable
    @Override
    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.wd_fragment,null,false);
        textViewUser=(TextView)view.findViewById(R.id.txt_head);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user_img=(ImageView)view.findViewById(R.id.user_tx);
        wdappintment=view.findViewById(R.id.wd_appintment);
        textViewsj=(TextView)view.findViewById(R.id.user_sj);
        textView=view.findViewById(R.id.user_sjh);
        logout=view.findViewById(R.id.wd_logout);
        person=view.findViewById(R.id.person_details);
        List<sqluser> zz=sqluser.listAll(sqluser.class);

        textViewUser.setText(zz.get(0).getEmployeeName());
        histroymeeting=view.findViewById(R.id.wd_history_meeting);
        textView.setText(zz.get(0).getPhone());
        histroymeeting.setOnClickListener(this);
        user_img.setOnClickListener(this);
        person.setOnClickListener(this);
        wdappintment.setOnClickListener(this);
        logout.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_tx:

                 break;
            case R.id.wd_appintment:
                Intent intent1=new Intent(getActivity(), wdappointmentActivity.class);
                startActivity(intent1);
                break;
            case R.id.wd_history_meeting:
                Intent intent2=new Intent(getActivity(), Histroy_meetingActivity.class);
                startActivity(intent2);
                break;
            case R.id.wd_logout:
                sqluser.deleteAll(sqluser.class);
                sqlmeeting.deleteAll(sqlmeeting.class);
                Intent intent3=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent3);
                break;
            case R.id.person_details:
                Intent intent=new Intent(getActivity(), PersonDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }


}

