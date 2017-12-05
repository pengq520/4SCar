package com.example.ios27.a4scar.model;

/**
 *Created by ios27 on 2017/11/22.
 */

public class Tb_maintaincontent //保养项目信息实体类
{
    private int _id; //存储保养项目编号
    private String content; //存储保养项目信息
    private double money; //存储价格信息
    private String mark; //存储备注信息

    public Tb_maintaincontent(){ //默认构造函数
        super();
    }

    //定义有参构造函数，用来初始化保养项目信息实体类中的各个字段
    public Tb_maintaincontent(int id, String content, double money, String mark){
        super();
        //赋值
        this._id=id;
        this.content=content;
        this.money=money;
        this.mark=mark;
    }

    public int getid(){ //设置便签编号的可读属性
        return _id;
    }
    public void setid(int id){ //设置便签编号的可写属性
        this._id=id;
    }

    public String getContent(){ //设置便签信息的可读属性
        return content;
    }
    public void setContent(String content){ //设置便签信息的可写属性
        this.content=content;
    }

    public String getMark(){ //设置便签信息的可读属性
        return mark;
    }
    public void setMark(String mark){ //设置便签信息的可写属性
        this.mark=mark;
    }

    public double getMoney(){ //设置便签信息的可读属性
        return money;
    }
    public void setMoney(double money){ //设置便签信息的可写属性
        this.money=money;
    }
}
