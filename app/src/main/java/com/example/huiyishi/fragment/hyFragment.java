package com.example.huiyishi.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.huiyishi.HuiYiActivity;
import com.example.huiyishi.HyMessActivity;
import com.example.huiyishi.LoginActivity;
import com.example.huiyishi.R;
import com.example.huiyishi.api.MeetingDetailsApiService;
import com.example.huiyishi.entity.BaseModel;
import com.example.huiyishi.entity.MeetingDetailsCourse;
import com.example.huiyishi.sqlcourse.sqlmeeting;
import com.example.huiyishi.sqlcourse.sqluser;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.core.content.ContextCompat.getSystemService;

public class hyFragment extends Fragment {
    RecyclerView recyclerview;
    static List<MeetingDetailsCourse> list;
    HyAdapter adapter;
    HuiYiActivity huiYiActivity;
    LoginActivity loginActivity;
    TextView textView;
    static int pos;
    @Nullable
    @Override
    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=LayoutInflater.from(getContext()).inflate(R.layout.hy_frgment,null,false);
        textView=view.findViewById(R.id.hy_NOmeeting);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerview=(RecyclerView)getActivity().findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        //创建适配器
        List<sqlmeeting> sqlmeetings=sqlmeeting.listAll(sqlmeeting.class);

        if(sqlmeetings.size()!=0){
            textView.setText("");
        }
        adapter = new HyAdapter(getActivity(), sqlmeetings);
        //给RecyclerView设置适配器
        recyclerview.setAdapter(adapter);

        recyclerview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                TextView view1=(TextView)view.findViewById(R.id.hy_name);
                pos= position;
                int itemId=view.getId();
                switch (itemId){
                    case R.id.hy_item :
                        Intent intent = new Intent(getActivity(),
                                HyMessActivity.class);
                        intent.putExtra("pos",position+"");
                        startActivity(intent);
                        break;
                }
            }
        });
    }





    public void save(List<MeetingDetailsCourse> list){
        List<sqlmeeting> sqlmeetings=sqlmeeting.listAll(sqlmeeting.class);
        Boolean b=new Boolean(true);
        if(sqlmeetings.size()>0){

            sqlmeeting.deleteAll(sqlmeeting.class);
            List<sqlmeeting> sqlmeetings1=new ArrayList<>();
            for (int i=0;i<list.size();i++){
               sqlmeetings1.add(new sqlmeeting(list.get(i).getMeetingid(),list.get(i).getMeetingname()
                       ,list.get(i).getNumberofparticipants(),list.get(i).getStarttime(),list.get(i).getEndtime()
                       ,list.get(i).getDescription(),list.get(i).getRoomname(),list.get(i).getReservationistname()));
            }
            SugarRecord.saveInTx(sqlmeetings1);
            List<sqlmeeting> sqlmeetings2=sqlmeeting.listAll(sqlmeeting.class);
            adapter.replaceData(sqlmeetings2);
            Log.i("secondactivity","aaaaaaaaaaaaa错误wwwwww"+sqlmeetings1.size());

        }else {
            List<sqlmeeting> list1=new ArrayList<>();
            for (int i=0;i<list.size();i++){
                list1.add(new sqlmeeting(list.get(i).getMeetingid(),list.get(i).getMeetingname()
                        ,list.get(i).getNumberofparticipants(),list.get(i).getStarttime(),list.get(i).getEndtime()
                        ,list.get(i).getDescription(),list.get(i).getRoomname(),list.get(i).getReservationistname()));

            }
            SugarRecord.saveInTx(list1);
            List<sqlmeeting> sqlmeetings2=sqlmeeting.listAll(sqlmeeting.class);
            adapter.replaceData(sqlmeetings2);

        }

        }
public void setList(List<MeetingDetailsCourse> courseLIst){
        this.list=courseLIst;
}

public int setPos(){
        return pos;
}
}
