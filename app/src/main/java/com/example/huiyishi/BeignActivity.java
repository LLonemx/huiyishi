package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.huiyishi.api.LoginApiService;
import com.example.huiyishi.entity.Loginjson;
import com.example.huiyishi.entity.UserCourse;
import com.example.huiyishi.sqlcourse.sqluser;
import com.example.huiyishi.sqlite.App;

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
public class BeignActivity extends AppCompatActivity {
    static UserCourse course;
    boolean aBoolean;
    static   private String username1;
    static    private String password1;
    static   private String user_name;
    static  private String depart;
    static  private String user_phone;
    static  private String user_mail;
    App app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        List<sqluser> ss=sqluser.listAll(sqluser.class);
        if(ss.size()==0){
            Intent intent = new Intent(BeignActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        else {
            login1(ss.get(0).getUsername(),ss.get(0).getPassword());
        }
    }
    private void login1(String username, String password) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(LoginApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginApiService apiService=retrofit.create(LoginApiService.class);
        apiService.getLogin(username,password).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Loginjson<UserCourse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Loginjson<UserCourse> userCourseLoginjson) {
                        course=userCourseLoginjson.getDatas();
                        if(course!=null){
                            Succecces();
                        }else {
                            fail();
                            sqluser.deleteAll(sqluser.class);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        fail();
                        sqluser.deleteAll(sqluser.class);
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    public  void Succecces(){
        Toast.makeText(BeignActivity.this,"加载成功",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(BeignActivity.this,
                HuiYiActivity.class);
        startActivity(intent);
    }
    public void fail(){
        Toast.makeText(BeignActivity.this,"加载失败请重新登录",Toast.LENGTH_LONG).show();
        sqluser.deleteAll(sqluser.class);
        Intent intent = new Intent(BeignActivity.this,
                LoginActivity.class);
        startActivity(intent);
    }


}
