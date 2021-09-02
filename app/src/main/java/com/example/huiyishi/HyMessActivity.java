package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huiyishi.adapter.MeetingDetailsAdapter;
import com.example.huiyishi.api.MeetingDetailsApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.fragment.hyFragment;
import com.example.huiyishi.sqlcourse.sqlmeeting;
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
public class HyMessActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton button;;
    RecyclerView recyclerview;
    MeetingDetailsAdapter adapter;
    hyFragment fragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hy_message);
        recyclerview=findViewById(R.id.hy_meeting_details);
        button=findViewById(R.id.ms_fh);
        ArrayList<MeetingDetailsCourse> datas = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(HyMessActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        //创建适配器
        List<sqlmeeting> s=sqlmeeting.listAll(sqlmeeting.class);
        Intent intent=getIntent();
        String poss=intent.getStringExtra("pos");
        int pos=Integer.parseInt(poss);
        ArrayList<sqlmeeting> date=new ArrayList<>();
        date.add(s.get(pos));
        adapter = new MeetingDetailsAdapter(HyMessActivity.this,date);
        //给RecyclerView设置适配器
        recyclerview.setAdapter(adapter);
        button.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ms_fh:
                Intent intent = new Intent(HyMessActivity.this,
                        HuiYiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
