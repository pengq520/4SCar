package com.example.ios27.a4scar.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ios27.a4scar.model.Tb_maintain;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;


public class maintainDAO {
    private DBOpenHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public maintainDAO(Context context){// 定义构造函数

        helper=new DBOpenHelper(context);// 初始化DBOpenHelper对象
    }

    /**
     * 添加保养信息
     *
     * @param Tb_maintain
     */
    public void add(Tb_maintain Tb_maintain){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行添加保养信息操作
        db.execSQL("insert into Tb_maintain(_id,carID,user,phone,time,type,umaintain,money,mark) values(?,?,?,?,?,?,?,?,?)",
                new Object[]{Tb_maintain.getid(),Tb_maintain.getCarID(),Tb_maintain.getUser(),Tb_maintain.getPhone(),
                        Tb_maintain.getTime(),Tb_maintain.getType(),Tb_maintain.getUmaintain(),Tb_maintain.getMoney(),
                        Tb_maintain.getMark()});
    }

    /**
     * 更新保养信息
     *
     * @param Tb_maintain
     */
    public void update(Tb_maintain Tb_maintain){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改保养信息操作
        db.execSQL("update Tb_maintain set carID=?, user=?, phone=?, money =?,time =?,type =?,umaintain =?,mark =? where _id =?",
                new Object[]{Tb_maintain.getCarID(),Tb_maintain.getUser(),Tb_maintain.getPhone(),
                        Tb_maintain.getTime(),Tb_maintain.getType(),Tb_maintain.getUmaintain(),Tb_maintain.getMoney(),
                        Tb_maintain.getMark(),Tb_maintain.getid()});
    }

    /**
     * 查找保养信息
     *
     * @param id
     * @return
     */
    public Tb_maintain find(int id){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select _id,carID,user,phone,time,type,umaintain,money,mark from Tb_maintain where _id=?",
                new String[]{String.valueOf(id)});// 根据编号查找维修信息，并存储到Cursor类中
        if(cursor.moveToNext()){// 遍历查找到的保养信息

            // 将遍历到的保养信息存储到Tb_inaccount类中
            return new Tb_maintain(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("carID")),
                    cursor.getString(cursor.getColumnIndex("user")),
                    cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("umaintain")),cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("mark")));
        }
        return null;// 如果没有信息，则返回null
    }

    /**
     * 刪除保养信息
     *
     * @param ids
     */
    public void detele(Integer... ids){
        if(ids.length>0){// 判断是否存在要删除的id

            StringBuffer sb=new StringBuffer();// 创建StringBuffer对象
            for(int i=0;i<ids.length;i++){// 遍历要删除的id集合

                sb.append('?').append(',');// 将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length()-1);// 去掉最后一个“,“字符
            db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
            // 执行删除保养信息操作
            db.execSQL("delete from Tb_maintain where _id in("+sb+")",(Object[])ids);
        }
    }

    /**
     * 获取保养信息
     *
     * @param start
     *            起始位置
     * @param count
     *            每页显示数量
     * @return
     */
    public List<Tb_maintain> getScrollData(int start, int count){
        List<Tb_maintain> Tb_maintain=new ArrayList<Tb_maintain>();// 创建集合对象
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 获取所有保养信息
        Cursor cursor=db.rawQuery("select * from Tb_maintain limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while(cursor.moveToNext()){// 遍历所有的保养信息

            // 将遍历到的保养信息添加到集合
            Tb_maintain.add(new Tb_maintain(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("carID")),
                    cursor.getString(cursor.getColumnIndex("user")),
                    cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("umaintain")),cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("mark"))));
        }
        return Tb_maintain;// 返回集合
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount(){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor=db.rawQuery("select count(_id) from Tb_maintain",null);// 获取保养信息的记录数
        if(cursor.moveToNext()){// 判断Cursor中是否有数据

            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }

    /**
     * 获取收入最大编号
     *
     * @return
     */
    public int getMaxId(){
        db=helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor=db.rawQuery("select max(_id) from Tb_maintain",null);// 获取保养信息表中的最大编号
        while(cursor.moveToLast()){// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        return 0;// 如果没有数据，则返回0
    }
}
