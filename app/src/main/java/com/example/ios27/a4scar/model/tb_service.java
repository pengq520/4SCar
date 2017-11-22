package com.example.ios27.a4scar.model;

/**
 * Created by ios27 on 2017/11/22.
 */

public class tb_service //维修记录实体类
{
    private int _id; //存储维修编号
    private String carID;//存储车牌号
    private String user;//存储车主
    private String phone;//存储联系方式
    private String time; //存储维修时间
    private String type; //存储维修类别
    private String uservice; //存储维修人
    private double money; //存储维修金额
    private String mark; //存储维修备注

    public tb_service(){ //默认构造函数
        super();
    }

    //定义有参构造函数，用来初始化维修信息实体类中的各个字段
    public tb_service(int id,String carID, String time, String type,String phone, String user,String uservice, double money, String mark){
        super();
        //赋值
        this._id=id;
        this.carID=carID;
        this.time=time;
        this.user=user;
        this.phone=phone;
        this.money=money;
        this.uservice=uservice;
        this.type=type;
        this.mark=mark;
    }

    public int getid(){ //设置支出编号的可读属性
        return _id;
    }
    public void set_id(int _id){ //设置支出类别的可读属性
        this._id=_id;
    }

    public void setCarID(String carID){ //设置支出编号的可写属性
        this.carID=carID;
    }
    public String getCarID(){ //设置支出类别的可读属性
        return carID;
    }

    public String getTime(){ //设置支出时间的可读属性
        return time;
    }
    public void setTime(String time){ //设置支出时间的可写属性
        this.time=time;
    }

    public String getUser(){ //设置支出类别的可读属性
        return user;
    }
    public void setUser(String user){ //设置支出类别的可写属性
        this.user=user;
    }

    public String getPhone(){ //设置支出时间的可读属性
        return phone;
    }
    public void setPhone(String phone){ //设置支出类别的可写属性
        this.phone=phone;
    }

    public double getMoney(){ //设置支出金额的可读属性
        return money;
    }
    public void setMoney(double money){ //设置支出金额的可写属性
        this.money=money;
    }

    public String getUservice(){ //设置支出地点的可读属性
        return uservice;
    }
    public void setUservice(String uservice){ //设置支出地点的可写属性
        this.uservice=uservice;
    }

    public String getType(){ //设置支出类别的可读属性
        return type;
    }
    public void setType(String type){ //设置支出类别的可写属性
        this.type=type;
    }

    public String getMark(){ //设置支出备注的可读属性
        return mark;
    }
    public void setMark(String mark){ //设置支出备注的可写属性
        this.mark=mark;
    }
}
