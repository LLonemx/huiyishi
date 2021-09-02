package com.example.huiyishi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.huiyishi.api.MeetingDetailsApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.fragment.hyFragment;
import com.example.huiyishi.fragment.wdFragment;
import com.example.huiyishi.fragment.yyFragment;
import com.example.huiyishi.sqlcourse.sqlmeeting;
import com.example.huiyishi.sqlcourse.sqluser;
import com.example.huiyishi.sqlite.App;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*本项目仅供参考学习使用*/
/*开发者联系方式qq：1459016889 林先生*/
public class HuiYiActivity extends AppCompatActivity implements View.OnClickListener {
    static String roomname;
    TextView textView;
    LinearLayout hy,yy,wd;
    ImageView hy_image,yy_image,wd_image;
    TextView hy_txt,yy_txt,wd_txt;
    hyFragment hy_fragment =null;
    yyFragment yy_fragment =null;
    wdFragment wd_fragment =null;
    int mCurrPosition=0;
    List<Fragment> fragmentList=new ArrayList<>();
    static List<MeetingDetailsCourse> list;
    Fragment mfragment;
    static String name;
    static String token;
    LoginActivity loginActivity;
    Context context;
    App app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        context=HuiYiActivity.this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_down);
        Intent intent= getIntent();
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");
        String user_name=intent.getStringExtra("user_name");
        String depart=intent.getStringExtra("depart");
        String user_phone=intent.getStringExtra("user_phone");
        String user_mail=intent.getStringExtra("user_mail");
        String eid=intent.getStringExtra("employeeid");
       int employeeid=0;
        List<sqluser> s=sqluser.listAll(sqluser.class);
        if(s.size()==0) {
            employeeid=Integer.parseInt(eid);
            sqluser sqluser = new sqluser(username, password, user_name, depart, user_phone, user_mail,employeeid);
            sqluser.save();
            Log.i("secondactivity","aaaaaaaaaaaaa错误wwwwww"+sqluser.getUsername());
            getData(employeeid);
        }else {
            List<sqluser> zz=sqluser.listAll(sqluser.class);
            getData(zz.get(0).getEmployeeid());
        }


        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                handler.postDelayed(this, 2000);
            }
        };



        hy=findViewById(R.id.hy);
        hy_image=findViewById(R.id.hy_image);
        hy_txt=findViewById(R.id.hy_text);
        hy_fragment =new hyFragment();
        fragmentList.add(hy_fragment);

        yy=findViewById(R.id.yy);
        yy_image=findViewById(R.id.yy_image);
        yy_txt=findViewById(R.id.yy_text);
        yy_fragment =new yyFragment();
        fragmentList.add(yy_fragment);

        wd=findViewById(R.id.wd);
        wd_image=findViewById(R.id.wd_image);
        wd_txt=findViewById(R.id.wd_text);
        wd_fragment =new wdFragment();
        fragmentList.add(wd_fragment);

        hy.setOnClickListener(this);
        yy.setOnClickListener(this);
        wd.setOnClickListener(this);
        laodindFragment(mCurrPosition);
        loadungView(mCurrPosition);


    }

    private void loadungView(int position) {
        hy_image.setSelected(false);
        yy_image.setSelected(false);
        wd_image.setSelected(false);

        hy_txt.setSelected(false);
        yy_txt.setSelected(false);
        wd_txt.setSelected(false);
         switch (position){
             case 0:
                 hy_image.setSelected(true);
                 hy_txt.setSelected(true);
                 break;
             case 1:
                 yy_image.setSelected(true);
                 yy_txt.setSelected(true);
                 break;
             case 2:
                 wd_image.setSelected(true);
                 wd_txt.setSelected(true);
                 break;
         }
    }

    private void laodindFragment(int position) {

        Fragment fragment=fragmentList.get(position);
        if (fragment==mfragment){
            return;
        }
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction bt=fm.beginTransaction();

        if (mfragment!=null){
            bt.replace(R.id.contar,fragment);
        }else {
            if (fragment.isAdded()) {
                bt.hide(mfragment).show(fragment);
            } else {
                bt.add(R.id.contar, fragment);
            }
        }
        mfragment=fragment;
        bt.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hy:
                mCurrPosition=0;
                break;
            case R.id.yy:
                mCurrPosition=1;
                break;
            case R.id.wd:
                mCurrPosition=2;
                break;
        }
        laodindFragment(mCurrPosition);
        loadungView(mCurrPosition);
    }



public  String getName(){
    return name;
    }




    public Fragment getfragment(){
        return yy_fragment;
    }



   public void setTextviewroom(String textviewroom){
        this.roomname=textviewroom;
   }




    private void getData(Integer key) {

                        Retrofit retrofit= new Retrofit.Builder()
                                .baseUrl(MeetingDetailsApiService.BaseUrl)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        // 步骤2：创建 网络请求接口 的实例
                        MeetingDetailsApiService api=retrofit.create(MeetingDetailsApiService.class);
                        // 步骤3：采用Observable<...>形式 对 网络请求 进行封装
                        Observable<BaseModel<MeetingDetailsCourse>> observable=api.getMeeting(key);
                        observable.repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                            // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                            // 以此决定是否重新订阅 & 发送原来的 Observable，即轮询
                            // 此处有2种情况：
                            // 1. 若返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable，即轮询结束
                            // 2. 若返回其余事件，则重新订阅 & 发送原来的 Observable，即继续轮询
                            @Override
                            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                                    @Override
                                    public ObservableSource<?> apply(@NonNull Object o) throws Exception {
                                        return Observable.just(1).delay(20000, TimeUnit.MILLISECONDS);
                                    }
                                });
                                }
                        }).subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<BaseModel<MeetingDetailsCourse>>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onNext(@NonNull BaseModel<MeetingDetailsCourse> meetingDetailsCourseBaseModel) {
                                        List<MeetingDetailsCourse> courses=meetingDetailsCourseBaseModel.getData();
                                        Log.i("secondactivity","aaaaaaaaaaaaa错误11111");
                                        list=courses;
                                        Boolean b=new Boolean(true);
                                        List<sqlmeeting> sqlmeetings=sqlmeeting.listAll(sqlmeeting.class);
                                        if(sqlmeetings.size()>0){
                                        for(int t=0;t<sqlmeetings.size();t++){
                                            int id=sqlmeetings.get(t).getMeetid();
                                            for(int d=0;d<list.size();d++){
                                                b=true;
                                                if(list.get(d).getMeetingid()==id){
                                                    b=false;
                                                }
                                            }
                                        }
                                        if(b=true){

                                        }
                                        }
                                        hy_fragment.save(courses);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.i("secondactivity","aaaaaaaaaaaaa错误11111");
                                    }
                                });

                    }
                    public void nontification(){
                        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
                        String title = "有新会议" ;
                        String content = "您有新的会议，请查看";
                        Intent intent = new Intent(this, HuiYiActivity.class);
                        PendingIntent pi= PendingIntent.getActivity(this, 0, intent, 0);

                        Notification n = new Notification.Builder(this)
                                .setContentTitle(title)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentText(content)
                                .setWhen(5000)
                                .setContentIntent(pi)
                                .build();
                        nm.notify(1, n);
                    }
}
