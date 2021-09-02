package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.huiyishi.adapter.RoomChoiceAdapter;
import com.example.huiyishi.api.RoomApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.entity.RoomCourse;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*本项目仅供参考学习使用*/
/*开发者联系方式qq：1459016889 林先生*/
public class RoomchoiceActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    private RoomChoiceAdapter adapterc;
    MaterialButton qubotton;
    private int pos = -1;
    MaterialButton dsbootton;
    int poss=0;
    String roomname=null;
   static String stime;
   static  String etime;
   static Integer roomid;
    HuiYiActivity huiYiActivity;
    int a=0;
    static  List<RoomCourse> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yy_room_choice);
        recyclerView=findViewById(R.id.rec_room);
        qubotton=findViewById(R.id.yy_room_choice_qr);
        dsbootton=findViewById(R.id.yy_room_choice_details);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,5);
        recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList<RoomCourse> datas = new ArrayList<>();
        adapterc=new RoomChoiceAdapter(this,datas);
        recyclerView.setAdapter(adapterc);
        Intent intent=getIntent();

        String people=intent.getStringExtra("people");
        String stattime=intent.getStringExtra("stattime");
        String endtime=intent.getStringExtra("endtime");
        Log.i("tag","aaaaasssssssssssss"+stattime);
        int peoplenumber=Integer.parseInt(people);
        getRoom(peoplenumber);
        qubotton.setOnClickListener(this);
        dsbootton.setOnClickListener(this);
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(pos !=position){
                    adapterc.clearChecked(position);
                    pos = position;
                    adapterc.notifyDataSetChanged();
                }
                poss=position;

                TextView textView=(TextView) view.findViewById(R.id.room_name);
                roomname=textView.getText().toString();
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yy_room_choice_qr:
                 Intent intent1=getIntent();

                Log.i("tag","aaaaasssssssssssss"+list.get(poss).getRoomid());
                 getMeetingData(list.get(poss).getRoomid(),stime,etime);

                break;
            case R.id.yy_room_choice_details:
                Intent intent = new Intent(RoomchoiceActivity.this,
                        RoomdetailsActivity.class);
                Log.i("tag","aaaaa"+roomname);
                intent.putExtra("roomname",roomname);
                startActivity(intent);
                break;
        }
    }

    private void getMeetingData(Integer roomid, String stattime, String endtime) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RoomApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RoomApiService api=retrofit.create(RoomApiService.class);
        stattime=stattime+":00";
        endtime=stattime+":00";
        api.getMeetingbyst(roomid,stattime,endtime).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<MeetingDetailsCourse>>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@NonNull BaseModel<MeetingDetailsCourse> roomCourseBaseModel) {
                                   if(roomCourseBaseModel.getCode()==200){
                                       Success();
                                   }else {
                                       Toast.makeText(RoomchoiceActivity.this,"该会议室冲突"+roomCourseBaseModel.getMessage(),Toast.LENGTH_LONG).show();
                                       field();
                                   }
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {

                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }


    public  void Success(){
        Intent intent1=new Intent();
        intent1.putExtra("roomname",roomname);
        setResult(RESULT_OK,intent1);
        finish();
    }

    public  void field(){
        Toast.makeText(RoomchoiceActivity.this,"该会议室冲突",Toast.LENGTH_LONG).show();
    }

    private void getRoom(Integer number) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RoomApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       RoomApiService api=retrofit.create(RoomApiService.class);
        api.getRoom(number).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<RoomCourse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseModel<RoomCourse> roomCourseBaseModel) {
                        if(roomCourseBaseModel.getCode()==200){
                               List<RoomCourse> courses=roomCourseBaseModel.getData();
                               list=courses;
                               adapterc.replaceData(courses);
                        }else {
                            Toast.makeText(RoomchoiceActivity.this,"没有容纳该会议人数的会议室",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
                    );
    }

    public String getRoomname(){
        return roomname;
    }

    public void seteee(String stime,String etime,Integer roomid){
        this.stime=stime;
        this.etime=etime;
        this.roomid=roomid;

    }

}
