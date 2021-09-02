package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.huiyishi.adapter.ChoiceUserAdapter;
import com.example.huiyishi.adapter.RoomChoiceAdapter;
import com.example.huiyishi.api.LoginApiService;
import com.example.huiyishi.api.UserMeeetingApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.ChoiceUserCourse;
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
public class ChoiceUserActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    ChoiceUserAdapter adapter;
    MaterialButton materialButton;
    public  static ArrayList<String> data;
    public static List<ChoiceUserCourse> list;
    public  static ArrayList<Integer> liss;
    public static ArrayList<String> date;
    int i=0;
    CheckBox checkBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.yy_meeting_user_choice);
        recyclerView=findViewById(R.id.meeting_user_choice);
        materialButton=findViewById(R.id.yy_meeting_user_choice_confirm);
        date=new ArrayList<>();
        liss=new ArrayList<>();
        data=new ArrayList<>();
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,5);
        recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList<ChoiceUserCourse> datas = new ArrayList<>();
        adapter=new ChoiceUserAdapter(this,datas);
        recyclerView.setAdapter(adapter);
        materialButton.setOnClickListener(this);
        getAllUserData();
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
           @Override
           public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
               CheckBox checkBox=(CheckBox) view.findViewById(R.id.user_choice);
               TextView textView=(TextView) view.findViewById(R.id.choice_user_name);
               checkBox.setChecked(!checkBox.isChecked());
               int itemId=view.getId();
               switch (itemId){
                   case R.id.item_meeting_user_choice:
                        if(checkBox.isChecked()){
                            liss.add(list.get(position).getEmployeeId());
                            data.add(textView.getText().toString());
                        }else {
                            data.remove(textView.getText().toString());
                            liss.remove(list.get(position).getEmployeeId());
                        }
               }
           }
       });








    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yy_meeting_user_choice_confirm:
                for (i=0;i<data.size();i++){
                    Log.i("tag","aaaaaaa"+data.get(i));
                }
                date=data;
                Intent intent=new Intent();
                intent.putExtra("data",date);
                intent.putExtra("dataid",liss);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }



    public  void getAllUserData(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(LoginApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserMeeetingApiService apiService=retrofit.create(UserMeeetingApiService.class);
        apiService.getUserAll().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<ChoiceUserCourse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseModel<ChoiceUserCourse> choiceUserCourseBaseModel) {
                        List<ChoiceUserCourse> choiceUserCourses=choiceUserCourseBaseModel.getData();
                        list=choiceUserCourses;
                        adapter.replaceData(choiceUserCourses);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
