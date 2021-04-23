package com.common.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.system.entity.RcCar;
import com.common.system.entity.RcIncome;
import com.common.system.entity.RcUseCar;
import com.common.system.mapper.RcCarMapper;
import com.common.system.mapper.RcUseCarMapper;
import com.common.system.service.CarService;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wcy
 * @Date: 2020/1/31
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private RcCarMapper rcCarMapper;
    @Autowired
    private RcUseCarMapper rcUseCarMapper;

    @Override
    public PageInfo<RcCar> listForPage(Integer pageNum, Integer pageSize, String status) {
        return  listForPage(pageNum,pageSize,status,"","");
    }
    @Override
    public PageInfo<RcCar> listForPage(Integer pageNum, Integer pageSize, String status,String bank,String card) {

        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<RcCar> courierList = rcCarMapper.selectList(new EntityWrapper<RcCar>()
                .eq("status",Integer.parseInt(status))
                .like("name",bank)
                .like("card",card)
        );
        return new PageInfo<>(courierList);
    }

    @Override
    public Result<RcCar> getById(Integer id) {
        Result<RcCar> result = new Result<>();
        RcCar rcCourier = rcCarMapper.selectById(id);
        if (rcCourier != null){
            result.setData(rcCourier);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        }
        return result;
    }

    @Override
    public Result<Integer> save(RcCar rcCourier) {
        Result<Integer> result = new Result<>();
        try {
            rcCarMapper.insert(rcCourier);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("操作失败");
        }
        return result;
    }

    @Override
    public Result<Integer> deleteById(Integer id) {
        Result<Integer> result = new Result<>();
        try {
            rcCarMapper.deleteById(id);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result<Integer> update(RcCar rcCourier) {
        Result<Integer> result = new Result<>();
        try {
            rcCarMapper.updateById(rcCourier);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public PageInfo<RcUseCar> uselistForPage(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<RcUseCar> courierList = rcUseCarMapper.selectList(null);
        return new PageInfo<>(courierList);
    }

    @Override
    public void addUseCar(RcUseCar rcUseCar) {
        rcUseCarMapper.insert(rcUseCar);
    }
}
