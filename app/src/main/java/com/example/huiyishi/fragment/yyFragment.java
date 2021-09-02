package com.example.huiyishi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huiyishi.ApplyActivity;
import com.example.huiyishi.ChoiceUserActivity;
import com.example.huiyishi.R;
import com.example.huiyishi.RoomchoiceActivity;
import com.example.huiyishi.adapter.yyChoiceUserAdapter;
import com.example.huiyishi.api.MeetingDetailsApiService;
import com.example.huiyishi.dialog.DateTimePickDialogUtil;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.MeetingAD;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.sqlcourse.sqluser;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class yyFragment extends Fragment implements View.OnClickListener {
    MaterialButton button;
    RelativeLayout relativeLayout;
    RelativeLayout relativeLayoutuser;
    private EditText startDateTime;
    private EditText endDateTime;
    private EditText number;
    EditText meetingname;
    RoomchoiceActivity roomchoiceActivity=new RoomchoiceActivity();

    private String initStartDateTime = getCalendarTime(); // 初始化开始时间
    private String initEndDateTime = getCalendarTime(); // 初始化结束时间
    RecyclerView recyclerView;
    TextView roomnameview;
String roomname="请选择会议室";
TextView meetingView;
EditText meetingdescription;
    yyChoiceUserAdapter courseAdapter;
    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    ArrayList<Integer> liss=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.yy_fragment,null,false);
        roomnameview = view.findViewById(R.id.yy_room_name);
        meetingView=view.findViewById(R.id.yy_meeting_user);
        number=view.findViewById(R.id.people);
        recyclerView=view.findViewById(R.id.Choice_user_rec);
        meetingname=view.findViewById(R.id.yy_meeting_name);
        meetingdescription=view.findViewById(R.id.yy_meeting_description);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
        courseAdapter=new yyChoiceUserAdapter(getActivity().getApplicationContext(),new ArrayList<>());
        recyclerView.setAdapter(courseAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setVisibility(View.VISIBLE);



        relativeLayout=(RelativeLayout)getActivity().findViewById(R.id.room_choice);
        button=(MaterialButton) getActivity().findViewById(R.id.btn_confirm);
        relativeLayoutuser=(RelativeLayout)getActivity().findViewById(R.id.choice_user);
        startDateTime = (EditText)getActivity().findViewById(R.id.start_time);
        endDateTime = (EditText)getActivity().findViewById(R.id.end_time);




        startDateTime.setFocusable(false);
        endDateTime.setFocusable(false);
        button.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        relativeLayoutuser.setOnClickListener(this);
        startDateTime.setOnClickListener(this);
        endDateTime.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.room_choice:
                String stattime=startDateTime.getText().toString();
                String endtime=endDateTime.getText().toString();
                String people=number.getText().toString();
                if(stattime.length()!=0&&endtime.length()!=0&&people.length()!=0){

                            int res=stattime.compareTo(endtime);
                            if(res>=0){
                                Toast.makeText(getActivity(),"结束时间不能小于等于开始时间",Toast.LENGTH_LONG).show();
                            }else {
                                    Intent intent = new Intent(getActivity(),
                                            RoomchoiceActivity.class);
                                    intent.putExtra("starttime",stattime);
                                    intent.putExtra("endtime",endtime);
                                    intent.putExtra("people",people);
                                    roomchoiceActivity.seteee(stattime,endtime,Integer.parseInt(people));
                                    startActivityForResult(intent,1);
                                Log.i("secondactivity","aaaaaaaaaaaaa错误eeeeeeeeeeeee"+stattime);
                                }
                }else {
                    Toast.makeText(getActivity(),"结束时间与开始时间和人数不能为空"+people,Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_confirm:
                String sttime=startDateTime.getText().toString();
                String eetime=endDateTime.getText().toString();
                String pnum=number.getText().toString();
                String roomnamee=roomnameview.getText().toString();
                String meetingnamee=meetingname.getText().toString();
                String description=meetingdescription.getText().toString();
                List<sqluser> ss=sqluser.listAll(sqluser.class);
                if(sttime.length()!=0&&eetime.length()!=0&&pnum.length()!=0&&roomnamee.length()!=0&&meetingnamee.length()!=0&&liss.size()!=0&&description.length()!=0){
                    MeetingAD meeting=new MeetingAD();
                        meeting.setStarttime(sttime);
                        meeting.setEndtime(eetime);
                        meeting.setRoomname(roomnamee);
                        meeting.setMeetingname(meetingnamee);
                        meeting.setDescription(description);
                        meeting.setNumberofparticipants(Integer.parseInt(pnum));
                        meeting.setReservationistid(ss.get(0).getEmployeeid());
                        meeting.setList(liss);
                        InsterMeeting(meeting);
                }else {

                    Toast.makeText(getActivity(),"请填写所有信息或选择会议室与参会用户",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.choice_user:
                Intent f= new Intent(getContext(),
                        ChoiceUserActivity.class);
               startActivityForResult(f,2);
                break;
            case R.id.start_time:
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        getActivity(), initEndDateTime);
                Log.i("taaaa","aaaaaaa"+getCalendarTime());
                dateTimePicKDialog.dateTimePicKDialog(startDateTime);
                break;
            case R.id.end_time:
                DateTimePickDialogUtil dateTimePicKDialog1 = new DateTimePickDialogUtil(
                        getActivity(), initEndDateTime);
                dateTimePicKDialog1.dateTimePicKDialog(endDateTime);
                break;
        }
    }

    private void InsterMeeting(MeetingAD meetingAD) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(MeetingDetailsApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MeetingDetailsApiService api=retrofit.create(MeetingDetailsApiService.class);
        api.insterMeeting(meetingAD).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<MeetingDetailsCourse>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        Log.i("secondactivity","aaaaaaaaaaaaa错误"+d);
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull BaseModel<MeetingDetailsCourse> meetingDetailsCourseBaseModel) {

                        if(meetingDetailsCourseBaseModel.getCode()==200){
                            Successs();
                        }else {
                            field();
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.i("secondactivity","aaaaaaaaaaaaa错误22"+e.getLocalizedMessage()+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("secondactivity","aaaaaaaaaaaaa错误33");
                    }
                });

    }

    private void Successs() {

       Intent intent=new Intent(getActivity(), ApplyActivity.class);
       startActivity(intent);


    }

    private void field() {
        Toast.makeText(getActivity(),"失败",Toast.LENGTH_LONG).show();
    }


    private String  getCalendarTime(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR));//年
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);//月
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));//日
        String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));//24小时格式    HOUR(12小时格式)
        String  mMinute = String.valueOf(c.get(Calendar.MINUTE));//分
        String time=mYear+"年"+mMonth+"月"+mDay+"日"+" "+mHour+":"+mMinute;
        return  time;
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            switch (requestCode){
                case 1:
                    if(resultCode==RESULT_OK){ ;
                        String name=data.getStringExtra("roomname");
                        roomnameview.setText(name);
                    }
                    break;
                case 2:
                    if (resultCode==RESULT_OK){
                        ArrayList<String> data1=new ArrayList<>();
                        if(data1.size()!=0){
                            data1.clear();
                        }
                       data1=data.getStringArrayListExtra("data");
                       courseAdapter.replaceData(data1);
                       liss=data.getIntegerArrayListExtra("dataid");
                       Log.i("aaaaaaaaa","aaaaaaaaaaaaaaaacccc"+liss);
                    }
                    break;
            }
    }




public String Data(ArrayList<String> data){
        String date=new String();
    for(int i=0;i<data.size();i++) {
        if (date.length()==0){
            date=data.get(i);
        }
        date = date + "，" + data.get(i);
    }
    return  date;
}
}
