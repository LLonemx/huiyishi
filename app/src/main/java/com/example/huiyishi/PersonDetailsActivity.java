package com.example.huiyishi;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huiyishi.adapter.HistroyMeetingAdapter;
import com.example.huiyishi.adapter.MeetingDetailsAdapter;
import com.example.huiyishi.adapter.PersonDetailsAdapter;
import com.example.huiyishi.sqlcourse.sqluser;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/*本项目仅供参考学习使用*/
/*开发者联系方式qq：1459016889 林先生*/
public class PersonDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton button;;
    RecyclerView recyclerview;
    PersonDetailsAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_person_details);
        recyclerview=findViewById(R.id.wd_person_details_rec);
        button=findViewById(R.id.wd_person_details_fh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        List<sqluser> ss=sqluser.listAll(sqluser.class);
        adapter = new  PersonDetailsAdapter(this, ss);
        //给RecyclerView设置适配器
        recyclerview.setAdapter(adapter);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wd_person_details_fh:
                finish();
                break;
        }
    }
}
