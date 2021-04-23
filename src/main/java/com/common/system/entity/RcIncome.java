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
 * @Date: 2020/1/30
 */
@TableName("rc_income")
public class RcIncome extends Model<RcIncome> implements Serializable {
    private static final long serialVersionUID = -8597875106667295283L;
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private String type;
    private String category;
    private String title;
    private Double price;
    private Integer status;
    private String status_str;
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    private String add_date;
    private String update_date;
    private String remark;


    @Override
    public String toString() {
        return "RcCourier{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", status_str='" + status_str + '\'' +
                ", user_id=" + user_id +
                ", add_date=" + add_date +
                ", remark='" + remark + '\'' +
                '}';
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



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
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
