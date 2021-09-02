package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huiyishi.adapter.RoomChoiceAdapter;
import com.example.huiyishi.adapter.RoomDetailAdapter;
import com.example.huiyishi.api.RoomApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.Loginjson;
import com.example.huiyishi.entity.RoomCourse;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
public class RoomdetailsActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton fh;
  RecyclerView recyclerView;
  RoomDetailAdapter adapterc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_details);
        recyclerView=findViewById(R.id.room_details_rec);
        fh=findViewById(R.id.room_details_fh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(RoomdetailsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<RoomCourse> datas = new ArrayList<>();
        adapterc=new RoomDetailAdapter(this,datas);
        recyclerView.setAdapter(adapterc);
        Intent intent=getIntent();
        String roomname=intent.getStringExtra("roomname");
        getRoomData(roomname);
        fh.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.room_details_fh:
                finish();
                break;
        }
    }


    private void getRoomData(String roomname) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(RoomApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RoomApiService api=retrofit.create(RoomApiService.class);
        api.getRoomByroomname(roomname).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<RoomCourse>>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@NonNull BaseModel<RoomCourse> roomCourseBaseModel) {
                                          List<RoomCourse> courses=roomCourseBaseModel.getData();
                                          adapterc.replaceData(courses);
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
}
