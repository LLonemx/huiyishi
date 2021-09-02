package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huiyishi.adapter.HisMeetDetailsAdapter;
import com.example.huiyishi.adapter.MeetingDetailsAdapter;
import com.example.huiyishi.api.MeetingDetailsApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.sqlcourse.sqlmeeting;
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

/*本项目仅供参考学习使用*/
/*开发者联系方式qq：1459016889 林先生*/
public class Histroy_meetdetailsActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton button;;
    RecyclerView recyclerview;
    HisMeetDetailsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_histroy_meeting_details);

        recyclerview=findViewById(R.id.wd_history_meeting_details);
        button=findViewById(R.id.wd_history_meeting_fh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new HisMeetDetailsAdapter(Histroy_meetdetailsActivity.this,new ArrayList<>());
        //给RecyclerView设置适配器
        recyclerview.setAdapter(adapter);
        Intent intent=getIntent();
        String meetingname=intent.getStringExtra("meetingid");
        int meetingid=0;
        meetingid=Integer.parseInt(meetingname);

        gethismeetingDate(meetingid);
        button.setOnClickListener(this);
    }

    private void gethismeetingDate(Integer meetingid) {

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(MeetingDetailsApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MeetingDetailsApiService api=retrofit.create(MeetingDetailsApiService.class);
        api.getHisMeetingBymeetingname(meetingid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
            case R.id.wd_history_meeting_fh:
                finish();
                break;
        }
    }
}
