package com.common.system.service;

import com.common.system.entity.RcIncome;
import com.common.system.entity.RcMoney;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: wcy
 * @Date: 2020/1/30
 */
public interface CourierService  {
    PageInfo<RcIncome> listForPage(Integer pageNum, Integer pageSize,
                                   String status,String date,String search,String type,String category);
    PageInfo<RcMoney> listForPage2(Integer pageNum, Integer pageSize);
    Result<RcIncome> getById(Integer id);

    Result<Integer> save(RcIncome rcCourier);

    Result<Integer> deleteById(Integer id);

    Result<Integer> update(RcIncome rcCourier);

    List<RcIncome> selectDate(String defaultEndDate);
}
