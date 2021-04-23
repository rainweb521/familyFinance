package com.common.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.common.system.entity.RcCar;
import com.common.system.entity.RcIncome;
import com.common.system.entity.RcMoney;
import com.common.system.mapper.RcCarMapper;
import com.common.system.mapper.RcCourierMapper;
import com.common.system.mapper.RcMoneyMapper;
import com.common.system.service.CourierService;
import com.common.system.shiro.ShiroKit;
import com.common.system.shiro.ShiroUser;
import com.common.system.util.MsgCode;
import com.common.system.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wcy
 * @Date: 2020/1/30
 */
@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    private RcCourierMapper rcCourierMapper;
    @Autowired
    private RcMoneyMapper rcMoneyMapper;
    @Autowired
    private RcCarMapper rcCarMapper;

    @Override
    public PageInfo<RcIncome> listForPage(Integer pageNum, Integer pageSize, String status,
                                          String date,String search,String type,String category) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<RcIncome> courierList;
        if ("2".equals(status)){
            ShiroUser user = (ShiroUser) ShiroKit.getSubject().getPrincipal();
            courierList = rcCourierMapper.selectList(new EntityWrapper<RcIncome>()
                    .eq("status",Integer.parseInt(status))
                    .eq("user_id",user.getId()));
        }else{
            courierList = rcCourierMapper.selectList(new EntityWrapper<RcIncome>()
                    .eq("status",Integer.parseInt(status))
                    .eq("category",category)
                    .like("type",type)
                    .like("add_date",date)
                    .like("title",search)
            );
        }
        return new PageInfo<>(courierList);
    }

    @Override
    public PageInfo<RcMoney> listForPage2(Integer pageNum, Integer pageSize ){
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        //累加银行存款
        double deposit = rcCarMapper.selectList(null).stream().filter(line->line.getStatus().equals(1)).mapToDouble(RcCar::getBalance).sum();
        //累加本年度贷款总额
        double loans = rcCarMapper.selectList(null).stream().filter(line -> line.getStatus().equals(2)).mapToDouble(line -> Double.parseDouble(line.getMonth()) * 12).sum();
        //统计大额支出
        double large = rcCourierMapper.selectList(null).stream().mapToDouble(RcIncome::getPrice).max().getAsDouble();

        /**
         * 算法描述：为计算用户可承受风险程度，先累加数据库中添加的存款数据，使用selectList遍历得到全部数据后，经过stream流式处理，使用mapToDouble来对所有余额进行累加聚和，从而得到用户全部存款总额，赋值deposit变量。
         * 而loans用来计算用户本年度需要偿还的贷款总额，需查询数据库中记录的贷款信息，经过流式计算对每月需还款金额进行提取，相乘得本年度贷款总额，再使用sum方法对元素累加，最后赋值于loans。
         * 为了判断用户消费习惯，还对用户消费记录进行分析，对数据库中对数据进行对比，从而得知该用户最大单笔消费金额，由此可知用户对大笔投资的可承受范围。并赋值于large。
         * 最后使用贷款总额/（存款+单笔最大消费金额）*100，用计算得到的值进行判断，当该值大于50%，仅推荐用户低风险理财产品，当处于35%-50%之间则推荐中等风险理财产品，当大于50%即可推荐高风险理财产品。
         */
        double all = loans /(deposit+ large) * 100;
        String str = "";
        if (all>=50){
            //低风险
            str = "低风险";
        }else if (all>=35&&all<50){
            //中低风险
            str = "中低风险";
        }else{
            //高风险
            str = "高风险";
        }
        List<RcMoney> courierList;
        courierList = rcMoneyMapper.selectList(null);
        String finalStr = str;
        courierList.forEach(line->{
            if (finalStr.equals(line.getRisk())){
                line.setRemark("非常推荐！");
            }else{
                line.setRemark("不推荐");
            }
        });
//        courierList = rcMoneyMapper.selectList(new EntityWrapper<RcMoney>()
//                    .eq("risk",str));

//        if ("2".equals(status)){
//            ShiroUser user = (ShiroUser) ShiroKit.getSubject().getPrincipal();
//            courierList = rcCourierMapper.selectList(new EntityWrapper<RcIncome>()
//                    .eq("status",Integer.parseInt(status))
//                    .eq("user_id",user.getId()));
//        }else{
//            courierList = rcCourierMapper.selectList(new EntityWrapper<RcIncome>()
//                    .eq("status",Integer.parseInt(status))
//                    .eq("category",category)
//                    .like("type",type)
//                    .like("add_date",date)
//                    .like("title",search)
//            );
//        }
        return new PageInfo<>(courierList);
    }

    @Override
    public Result<RcIncome> getById(Integer id) {

        Result<RcIncome> result = new Result<>();
        RcIncome rcCourier = rcCourierMapper.selectById(id);
        if (rcCourier != null){
            result.setData(rcCourier);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        }
        return result;
    }

    @Override
    public Result<Integer> save(RcIncome rcCourier) {
        Result<Integer> result = new Result<>();
        try {
            rcCourierMapper.insert(rcCourier);
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
            rcCourierMapper.deleteById(id);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result<Integer> update(RcIncome rcCourier) {
        Result<Integer> result = new Result<>();
        try {
            rcCourierMapper.updateById(rcCourier);
            result.setStatus(true);
            result.setCode(MsgCode.SUCCESS);
            result.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public List<RcIncome> selectDate(String defaultEndDate) {
        return rcCourierMapper.selectList(new EntityWrapper<RcIncome>()
               .like("add_date",defaultEndDate) );
    }
}
