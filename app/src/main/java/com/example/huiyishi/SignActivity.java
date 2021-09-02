package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.huiyishi.api.LoginApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.Employee;
import com.example.huiyishi.entity.Loginjson;
import com.example.huiyishi.entity.UserCourse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText username;
    TextInputEditText user_name;
    TextInputEditText password;
    TextInputEditText passwords;
    TextInputEditText phone;
    TextInputEditText mail;
    MaterialButton sign;
    @Override
    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        username=findViewById(R.id.sign_username);
        user_name=findViewById(R.id.sign_user_name);
        password=findViewById(R.id.sign_user_password);
        passwords=findViewById(R.id.sign_password_2);
        phone=findViewById(R.id.sign_user_phone);
        mail=findViewById(R.id.sign_user_mail);
        sign=findViewById(R.id.sign_sign);
        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_sign:
                Toast.makeText(SignActivity.this,"执行",Toast.LENGTH_LONG).show();
                String usernamee=username.getText().toString();
                String user_namee=user_name.getText().toString();
                String passwordd=password.getText().toString();
                String passwordss=passwords.getText().toString();
                String phonee=phone.getText().toString();
                String maill=mail.getText().toString();
                if(usernamee.length()!=0&&user_namee.length()!=0&&passwordd.length()!=0&&passwordss.length()!=0&&
                phonee.length()!=0&&maill.length()!=0){
                    if(passwordd.equals(passwordss)){
                        Employee employee=new Employee();
                        employee.setUsername(usernamee);
                        employee.setEmployeeName(user_namee);
                        employee.setPassword(passwordd);
                        employee.setPhone(phonee);
                        employee.setEmail(maill);
                        signdata(employee);

                    }else {
                        Toast.makeText(SignActivity.this,"输入的两次密码不相同",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(SignActivity.this,"请填完所有空",Toast.LENGTH_LONG).show();
                }

        }
    }

    private void signdata(Employee employee) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(LoginApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginApiService apiService=retrofit.create(LoginApiService.class);
        apiService.getSign(employee).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<Employee>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseModel<Employee> employeeBaseModel) {
                                 if(employeeBaseModel.getCode()==200){
                                     Success();
                                 }else {
                                     field();
                                 }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("secondactivity","aaaaaaaaaaaaa错误11111安分守法"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void Success(){
        Toast.makeText(SignActivity.this,"注册成功，请等待审核",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(SignActivity.this,LoginActivity.class);
        startActivity(intent);

    }


    public void field(){
        Toast.makeText(SignActivity.this,"注册失败,用户名被占用",Toast.LENGTH_LONG).show();
    }
}
