package com.example.ios27.a4scar.model;

/**
 * Created by ios27 on 2017/11/22.
 */

public class Tb_flag //备忘录信息实体类
{
    private int _id; //存储备忘录编号
    private String flag; //存储备忘录信息

    public Tb_flag(){ //默认构造函数
        super();
    }

    //定义有参构造函数，用来初始化备忘录信息实体类中的各个字段
    public Tb_flag(int id,String flag){
        super();
        this._id=id; //为备忘录号赋值
        this.flag=flag; //为备忘录信息赋值
    }

    public int getid(){ //设置便签编号的可读属性
        return _id;
    }

    public void setid(int id){ //设置便签编号的可写属性
        this._id=id;
    }

    public String getFlag(){ //设置便签信息的可读属性
        return flag;
    }

    public void setFlag(String flag){ //设置便签信息的可写属性
        this.flag=flag;
    }
}
