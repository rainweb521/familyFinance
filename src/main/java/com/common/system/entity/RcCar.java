package com.common.system.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wcy
 * @Date: 2020/1/31
 */
@TableName("rc_bank")
public class RcCar  extends Model<RcCar> implements Serializable {
    private static final long serialVersionUID = -8597875106667295283L;
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private String brand;
    private String name;
    private Double balance;
    private String type;
    private String remark;
    private Integer status;
    private String status_str;
    private String card;
    private String scard;
    private Double sbalance;
    private String month;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_date;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatus_str() {
        return status_str;
    }

    public void setStatus_str(String status_str) {
        this.status_str = status_str;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getScard() {
        return scard;
    }

    public void setScard(String scard) {
        this.scard = scard;
    }

    public Double getSbalance() {
        return sbalance;
    }

    public void setSbalance(Double sbalance) {
        this.sbalance = sbalance;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
