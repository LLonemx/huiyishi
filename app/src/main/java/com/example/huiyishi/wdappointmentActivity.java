package com.example.huiyishi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huiyishi.adapter.HistroyMeetingAdapter;
import com.example.huiyishi.adapter.wdapponintmentAdapter;
import com.example.huiyishi.api.MeetingDetailsApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.fragment.HyAdapter;
import com.example.huiyishi.sqlcourse.sqluser;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class wdappointmentActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerview;
    static List<MeetingDetailsCourse> list;
    wdapponintmentAdapter adapter;
    MaterialButton appointment_fh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_appointment);
        recyclerview=findViewById(R.id.appointment_recyclerview);
        appointment_fh=findViewById(R.id.wd_appointment_fh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new wdapponintmentAdapter(this, new ArrayList<>());
        //给RecyclerView设置适配器
        recyclerview.setAdapter(adapter);
        /*本项目仅供参考学习使用*/
        /*开发者联系方式qq：1459016889 林先生*/
        List<sqluser> ss=sqluser.listAll(sqluser.class);
        getappointmentData(ss.get(0).getEmployeeid());
        appointment_fh.setOnClickListener(this);
    }

    private void getappointmentData(int employeeid) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(MeetingDetailsApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MeetingDetailsApiService api=retrofit.create(MeetingDetailsApiService.class);
        api.getAppointment(employeeid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<MeetingDetailsCourse>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        Log.i("secondactivity","aaaaaaaaaaaaa错误"+d);
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull BaseModel<MeetingDetailsCourse> meetingDetailsCourseBaseModel) {
                        List<MeetingDetailsCourse> courses=meetingDetailsCourseBaseModel.getData();
                        Log.i("secondactivity","aaaaaaaaaaaaa错误11111");
                        adapter.replaceData(courses);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.i("secondactivity","aaaaaaaaaaaaa错误22"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("secondactivity","aaaaaaaaaaaaa错误33");
                    }
                });

    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.wd_appointment_fh:
              finish();
              break;
      }
    }
}
