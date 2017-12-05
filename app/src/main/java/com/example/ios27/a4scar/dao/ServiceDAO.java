package com.example.ios27.a4scar.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ios27.a4scar.model.tb_service;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;


public class ServiceDAO {
    private DBOpenHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public ServiceDAO(Context context){// 定义构造函数
        helper=new DBOpenHelper(context);// 初始化DBOpenHelper对象
    }

    /**
     * 添加维修信息
     *
     * @param tb_service
     */
    public void add(tb_service tb_service){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行添加维修信息操作
        db.execSQL("insert into tb_service(_id,carID,user,phone,time,type,uservice,money,mark) values(?,?,?,?,?,?,?,?,?)",
                new Object[]{tb_service.getid(),tb_service.getCarID(),tb_service.getUser(),tb_service.getPhone(),
                        tb_service.getTime(),tb_service.getType(),tb_service.getUservice(),tb_service.getMoney(),
                        tb_service.getMark()});
    }

    /**
     * 更新维修信息
     *
     * @param tb_service
     */
    public void update(tb_service tb_service){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改维修信息操作
        db.execSQL("update tb_service set carID=?, user=?, phone=?, money =?,time =?,type =?,uservice =?,mark =? where _id =?",
                new Object[]{tb_service.getCarID(),tb_service.getUser(),tb_service.getPhone(),
                tb_service.getTime(),tb_service.getType(),tb_service.getUservice(),tb_service.getMoney(),
                tb_service.getMark(),tb_service.getid()});
    }

    /**
     * 查找维修信息
     *
     * @param id
     * @return
     */
    public tb_service find(int id){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select _id,carID,user,phone,time,type,uservice,money,mark from tb_service where _id=?",new String[]{String.valueOf(id)});// 根据编号查找维修信息，并存储到Cursor类中
        if (cursor.moveToNext()){// 遍历查找到的维修信息

            // 将遍历到的支出信息存储到tb_service类中
            return new tb_service(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("carID")),cursor.getString(cursor.getColumnIndex("user")),
                    cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("uservice")),cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("mark")));
        }
        return null;// 如果没有信息，则返回null
    }

    /**
     * 刪除维修信息
     *
     * @param ids
     */
    public void detele(Integer... ids){
        if (ids.length>0){// 判断是否存在要删除的id

            StringBuffer sb=new StringBuffer();// 创建StringBuffer对象
            for(int i=0;i<ids.length; i++){// 遍历要删除的id集合

                sb.append('?').append(',');// 将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length()-1);// 去掉最后一个“,“字符
            db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
            // 执行删除维修记录信息操作
            db.execSQL("delete from tb_service where _id in("+sb+")",(Object[])ids);
        }
    }

    /**
     * 获取维修信息
     *
     * @param start 起始位置
     * @param count 每页显示数量
     * @return
     */
    public List<tb_service> getScrollData(int start, int count){
        List<tb_service> tb_service=new ArrayList<tb_service>();// 创建集合对象
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 获取所有维修信息
        Cursor cursor=db.rawQuery("select * from tb_service limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while(cursor.moveToNext()){// 遍历所有的维修信息

            // 将遍历到的维修信息添加到集合中
            tb_service.add(new tb_service(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("carID")),cursor.getString(cursor.getColumnIndex("user")),
                    cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("uservice")),cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("mark"))));
        }
        return tb_service;// 返回集合
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount(){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor=db.rawQuery("select count(_id) from tb_service",null);// 获取维修信息的记录数
        if(cursor.moveToNext()){// 判断Cursor中是否有数据

            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }

    /**
     * 获取维修最大编号
     *
     * @return
     */
    public int getMaxId(){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor=db.rawQuery("select max(_id) from tb_service",null);// 获取维修记录信息表中的最大编号
        while(cursor.moveToLast()){// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        return 0;// 如果没有数据，则返回0
    }
}
