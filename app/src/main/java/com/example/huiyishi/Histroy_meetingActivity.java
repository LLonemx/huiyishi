package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.huiyishi.adapter.HistroyMeetingAdapter;
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

/*本项目仅供参考学习使用*/
/*开发者联系方式qq：1459016889 林先生*/
public class Histroy_meetingActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerview;
    static List<MeetingDetailsCourse> list;
    HistroyMeetingAdapter adapter;
    MaterialButton histroy_fh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_history_meeting);
        recyclerview=findViewById(R.id.wd_histroy_meeting_rec);
        histroy_fh=findViewById(R.id.histroy_meeting_fh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new HistroyMeetingAdapter(this, new ArrayList<>());
        //给RecyclerView设置适配器
        recyclerview.setAdapter(adapter);
        List<sqluser> ss=sqluser.listAll(sqluser.class);
        getHisMeetingData(ss.get(0).getEmployeeid());
        histroy_fh.setOnClickListener(this);

        recyclerview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                TextView view1=(TextView)view.findViewById(R.id.histroy_meeting_hy_name);
                int itemId=view.getId();
                switch (itemId){
                    case R.id.histroy_meeting_hy_item :
                        String meetingname=view1.getText().toString();
                        int meetingid=list.get(position).getMeetingid();
                        Intent intent = new Intent(Histroy_meetingActivity.this,
                                Histroy_meetdetailsActivity.class);
                        intent.putExtra("meetingid",meetingid+"");
                        startActivity(intent);
                        break;
                }
            }
        });
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.histroy_meeting_fh:
                finish();
                break;
        }

    }
    private void getHisMeetingData(Integer employeeid) {
    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl(MeetingDetailsApiService.BaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    MeetingDetailsApiService api=retrofit.create(MeetingDetailsApiService.class);
    api.getHistroyMeeting(employeeid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<BaseModel<MeetingDetailsCourse>>() {
                @Override
                public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                    Log.i("secondactivity","aaaaaaaaaaaaa错误"+d);
                }

                @Override
                public void onNext(@io.reactivex.annotations.NonNull BaseModel<MeetingDetailsCourse> meetingDetailsCourseBaseModel) {
                    List<MeetingDetailsCourse> courses=meetingDetailsCourseBaseModel.getData();
                    Log.i("secondactivity","aaaaaaaaaaaaa错误11111");
                    list=courses;
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
}
