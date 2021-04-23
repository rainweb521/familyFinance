package com.common.system.service;

import com.common.system.entity.RcCar;
import com.common.system.entity.RcUseCar;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;

/**
 * @Author: wcy
 * @Date: 2020/1/31
 */
public interface CarService {
    PageInfo<RcCar> listForPage(Integer pageNum, Integer pageSize, String status);
    PageInfo<RcCar> listForPage(Integer pageNum, Integer pageSize, String status,String bank,String card);

    Result<RcCar> getById(Integer id);

    Result<Integer> save(RcCar rcCourier);

    Result<Integer> deleteById(Integer id);

    Result<Integer> update(RcCar rcCourier);

    PageInfo<RcUseCar> uselistForPage(Integer pageNum, Integer pageSize);

    void addUseCar(RcUseCar rcUseCar);
}
