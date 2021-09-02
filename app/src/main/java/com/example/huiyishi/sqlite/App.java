package com.example.huiyishi.sqlite;

import android.content.Context;
import android.database.Cursor;

import com.example.huiyishi.entity.UserCourse;
import com.example.huiyishi.sqlcourse.sqluser;
import com.orm.SugarApp;
import com.orm.SugarContext;

import java.util.List;

public class App extends SugarApp {
    Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }


    /*本项目仅供参考学习使用*/
    /*开发者联系方式qq：1459016889 林先生*/
    public List<sqluser> getUserall(){
        List<sqluser> users= sqluser.listAll(sqluser.class);
        return users;
    }

    public sqluser getUser(){
        sqluser sqlUser= sqluser.findById(sqluser.class,1);
        return sqlUser;
    }

    public void deleteall(){
        sqluser.deleteAll(sqluser.class);
    }

    public Boolean user(){

      try {
          Cursor cursor= sqluser.getCursor(sqluser.class,null,null,null,null,null);
          if(cursor.getCount()!=0){
              return true;
          }else {
              return false;
          }

      }catch (Exception e){

      }

        return false;
    }
}
