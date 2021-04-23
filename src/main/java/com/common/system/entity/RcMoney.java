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
@TableName("rc_money")
public class RcMoney extends Model<RcMoney> implements Serializable {
    private static final long serialVersionUID = -8597875106667295283L;
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private String bank;
    private String name;
    private String risk;
    private String interest;
    private String length;
    private String content;
    private String start;
    private String type;
    private String buyinfo;
    private String buyinfo2;
    private String buyinfo3;
    private String remark;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuyinfo() {
        return buyinfo;
    }

    public void setBuyinfo(String buyinfo) {
        this.buyinfo = buyinfo;
    }

    public String getBuyinfo2() {
        return buyinfo2;
    }

    public void setBuyinfo2(String buyinfo2) {
        this.buyinfo2 = buyinfo2;
    }

    public String getBuyinfo3() {
        return buyinfo3;
    }

    public void setBuyinfo3(String buyinfo3) {
        this.buyinfo3 = buyinfo3;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
