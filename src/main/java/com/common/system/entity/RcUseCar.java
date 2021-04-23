package com.common.system.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * @Author: wcy
 * @Date: 2020/1/31
 */
@TableName("rc_usecar")
public class RcUseCar  extends Model<RcCar> implements Serializable {
    private static final long serialVersionUID = -8597875106667295283L;
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private String name;
    private String user_id;
    private String use_date;
    private String car_name;
    private String car_code;
    private String remark;

    public RcUseCar() {
    }

    public RcUseCar(String name, String user_id, String use_date, String car_name, String car_code, String remark) {
        this.name = name;
        this.user_id = user_id;
        this.use_date = use_date;
        this.car_name = car_name;
        this.car_code = car_code;
        this.remark = remark;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUse_date() {
        return use_date;
    }

    public void setUse_date(String use_date) {
        this.use_date = use_date;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
