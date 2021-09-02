package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.huiyishi.api.LoginApiService;
import com.example.huiyishi.entity.Loginjson;
import com.example.huiyishi.entity.UserCourse;
import com.example.huiyishi.sqlcourse.sqlmeeting;
import com.example.huiyishi.sqlcourse.sqluser;
import com.example.huiyishi.sqlite.App;
import com.google.android.material.button.MaterialButton;
import com.orm.SugarContext;

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
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    static   private String username1;
    static    private String password1;
    static   private String user_name;
    static  private String depart;
    static  private String user_phone;
    static  private String user_mail;
    static  private  int departmentId;
    static  private  int employeeid;
    UserCourse course=new UserCourse();


App app;
  MaterialButton Button_fh;
  MaterialButton Buttonlogin;
  private String jsondata ;
  private String usernames,passwords;
   EditText username;
   EditText password;
    public LoginActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SugarContext.init(this);
        course=null;

        username = findViewById(R.id.user_sj);
        password = findViewById(R.id.user_password);
        Buttonlogin=findViewById(R.id.login);
        Button_fh=findViewById(R.id.fh);
        Buttonlogin.setOnClickListener(this);
        Button_fh.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fh:
                Intent intent=new Intent(LoginActivity.this,SignActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                String name =username.getText().toString();
                String passwords=password.getText().toString();
                    login(name,passwords);

                break;
        }
    }

    private void login(String username, String password) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(LoginApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginApiService apiService=retrofit.create(LoginApiService.class);
        apiService.getLogin(username,password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Loginjson<UserCourse> >() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Loginjson<UserCourse> userCourseLoginjson) {
                        course=userCourseLoginjson.getDatas();
                        if(userCourseLoginjson.getCode()==200){
                            Succecces();
                        }else {
                            fail();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    public  void Succecces(){

    Toast.makeText(LoginActivity.this, "登录成功"+course.getEmployeeName(), Toast.LENGTH_LONG).show();
    Intent intent = new Intent(LoginActivity.this,
            HuiYiActivity.class);
        List<sqluser> s=sqluser.listAll(sqluser.class);
        List<sqlmeeting> d=sqlmeeting.listAll(sqlmeeting.class);
        if(s.size()!=0){
            sqluser.deleteAll(sqluser.class);
        }
        if(d.size()!=0){
            sqlmeeting.deleteAll(sqlmeeting.class);
        }
       UserCourse course1=new UserCourse();
       username1=course.getUsername();
       password1=course.getPassword();
       user_name=course.getEmployeeName();
       depart=course.getDepartment();
      user_phone=course.getPhone();
      user_mail=course.getEmail();
        employeeid=course.getEmployeeId();
    intent.putExtra("username",username1);
    intent.putExtra("password",password1);
    intent.putExtra("user_name",user_name);
    intent.putExtra("depart",depart);
    intent.putExtra("user_phone",user_phone);
    intent.putExtra("user_mail",user_mail);
    intent.putExtra("employeeid",employeeid+"");
    startActivity(intent);

    }

    public  UserCourse getCourse(){
        return course;
    }
    public void fail(){
        Toast.makeText(LoginActivity.this,"登陆失败，账号或密码错误",Toast.LENGTH_LONG).show();
    }


}







