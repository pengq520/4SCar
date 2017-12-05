package com.example.ios27.a4scar.dao;

/**
 * Created by ios27 on 2017/11/22.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper{
    private static final int VERSION=1; //定义数据库版本号
    private static final String DBNAME="account.db"; //定义数据库名

    public DBOpenHelper(Context context){ //定义构造函数

        super(context,DBNAME,null,VERSION ); //重写基类的构造函数
    }

    @Override
    public void onCreate(SQLiteDatabase db){ //创建数据库
        db.execSQL("create table tb_service (_id integer primary key,carID varchar(20),user varchar(20),phone varchar(20),time varchar(10),"
                +  "type varchar(10),uservice varchar(20),money decimal,mark varchar(200))" ); //创建维修信息表
        db.execSQL("create table tb_maintain (_id integer primary key,carID varchar(20),user varchar(20),phone varchar(20),time varchar(10),"
                +  "type varchar(10),umaintain varchar(20),money decimal,mark varchar(200))" ); //创建保养信息表
        db.execSQL("create table tb_pwd (password varchar(20))" ); //创建密码表
        db.execSQL("create table tb_flag (_id integer primary key,flag varchar(200))" ); //创建备忘录表
        db.execSQL("create table tb_servicecontent (_id integer primary key,content varchar(50),money decimal,mark varchar(200))" ); //创建维修项目表
        db.execSQL("create table tb_maintaincontent (_id integer primary key,maintain varchar(50),money decimal,mark varchar(200))" ); //创建保养项目表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){ //覆写基类的onUpgrade方法，以便数据库版本更新

    }
}
