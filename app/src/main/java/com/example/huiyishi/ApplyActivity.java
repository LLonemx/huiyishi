package com.example.huiyishi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

/*本项目仅供参考学习使用*/
/*开发者联系方式qq：1459016889 林先生*/
public class ApplyActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yy_apply);
        button=findViewById(R.id.yy_qr);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yy_qr:
                Intent intent=new Intent(this,HuiYiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
