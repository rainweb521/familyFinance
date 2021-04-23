package com.common.system.controller;

import com.common.system.entity.RcIncome;
import com.common.system.entity.RcMoney;
import com.common.system.entity.RcUser;
import com.common.system.mapper.RcMoneyMapper;
import com.common.system.service.CourierService;
import com.common.system.service.UserService;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: wcy
 * @Date: 2020/1/30
 */
@Controller
@RequestMapping(value = "courier")
public class CourierController extends BaseController {

    private static final String SYSTEM_ADMIN = "/system/admin/courier/";
    @Autowired
    private CourierService courierService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.setViewName(SYSTEM_ADMIN + "list");
        return modelAndView;
    }
    @RequestMapping(value = "list2", method = RequestMethod.GET)
    public ModelAndView list2(ModelAndView modelAndView) {
        modelAndView.setViewName(SYSTEM_ADMIN + "list2");
        return modelAndView;
    }
    @RequestMapping(value = "allotlist", method = RequestMethod.GET)
    public ModelAndView allotlist(ModelAndView modelAndView) {
        modelAndView.setViewName(SYSTEM_ADMIN + "allotlist");
        return modelAndView;
    }
    @RequestMapping(value = "brokenlist", method = RequestMethod.GET)
    public ModelAndView brokenlist(ModelAndView modelAndView) {
        modelAndView.setViewName(SYSTEM_ADMIN + "brokenlist");
        return modelAndView;
    }
    @RequestMapping(value = "deliverylist", method = RequestMethod.GET)
    public ModelAndView deliverylist(ModelAndView modelAndView) {
        modelAndView.setViewName(SYSTEM_ADMIN + "deliverylist");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<RcIncome> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start,
                                           @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                           @RequestParam(value = "add_date", required = false) String add_date,
                                           @RequestParam(value = "search", required = false) String search,
                                           @RequestParam(value = "type", required = false) String type,
                                           @RequestParam(value = "status", required = false,defaultValue = "1") String status,
                                           HttpServletRequest request) {
        Map<String,String[]> params = request.getParameterMap();
        PageInfo<RcIncome> pageInfo = courierService.listForPage((start / pageSize) + 1, pageSize,status,add_date,search,type,"支出");
        return new PageBean<RcIncome>(pageInfo);
    }
    @ResponseBody
    @RequestMapping(value = "page2")
    public PageBean<RcIncome> queryForPage2(@RequestParam(value = "start", defaultValue = "1") int start,
                                           @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                           @RequestParam(value = "add_date", required = false) String add_date,
                                           @RequestParam(value = "search", required = false) String search,
                                           @RequestParam(value = "type", required = false) String type,
                                           @RequestParam(value = "status", required = false,defaultValue = "1") String status,
                                           HttpServletRequest request) {
        Map<String,String[]> params = request.getParameterMap();
        PageInfo<RcIncome> pageInfo = courierService.listForPage((start / pageSize) + 1, pageSize,status,add_date,search,type,"收入");
        return new PageBean<RcIncome>(pageInfo);
    }
    @ResponseBody
    @RequestMapping(value = "page3")
    public PageBean<RcMoney> queryForPage3(@RequestParam(value = "start", defaultValue = "1") int start,
                                           @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                           @RequestParam(value = "add_date", required = false) String add_date,
                                           @RequestParam(value = "search", required = false) String search,
                                           @RequestParam(value = "type", required = false) String type,
                                           @RequestParam(value = "status", required = false,defaultValue = "1") String status,
                                           HttpServletRequest request) {
        Map<String,String[]> params = request.getParameterMap();
        PageInfo<RcMoney> pageInfo = courierService.listForPage2((start / pageSize) + 1, pageSize);
        return new PageBean<RcMoney>(pageInfo);
    }
    @RequestMapping(value = "allot/{id}")
    public ModelAndView allot(@PathVariable Integer id, ModelAndView modelAndView){
        modelAndView.addObject("id", id);
        List<RcUser> userList = userService.getAllotUserList();
        modelAndView.addObject("users", userList);
        modelAndView.setViewName(SYSTEM_ADMIN + "allot");
        return modelAndView;
    }
    @RequestMapping(value = "addallot")
    @ResponseBody
    public Result addallot(Integer id, String userid) {
        RcIncome courier = courierService.getById(id).getData();
        courier.setUser_id(Integer.valueOf(userid));
        courier.setStatus(2);
        courier.setStatus_str("配送");
        courierService.update(courier);
        Result result = new Result();
        result.setStatus(true);
        result.setCode(MsgCode.SUCCESS);
        return result;
    }
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Integer id, ModelAndView modelAndView) {
        Result<RcIncome> result = courierService.getById(id);
        modelAndView.addObject("bean", result.getData());
        modelAndView.setViewName(SYSTEM_ADMIN + "view");
        return modelAndView;
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName(SYSTEM_ADMIN + "add");
        return modelAndView;
    }
    @RequestMapping(value = "add2", method = RequestMethod.GET)
    public ModelAndView add2(ModelAndView modelAndView) {
        modelAndView.setViewName(SYSTEM_ADMIN + "add2");
        return modelAndView;
    }
    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(RcIncome rcCourier) {
        rcCourier.setAdd_date(DateUtil.format(new Date(),"yyy-MM-dd"));
        rcCourier.setStatus(1);
        rcCourier.setStatus_str("");
        Result<Integer> result = courierService.save(rcCourier);
        return result;
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        Result<Integer> result = courierService.deleteById(id);
        return result;
    }
    @RequestMapping(value = "delivery/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delivery(@PathVariable Integer id) {
        Result<RcIncome> result = courierService.getById(id);
        RcIncome courier = result.getData();
        courier.setStatus(3);
        courier.setStatus_str("");
        courier.setUpdate_date(DateUtil.format(new Date(),"yyy-MM-dd"));
        return courierService.update(courier);
    }
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id, ModelAndView modelAndView) {
        Result<RcIncome> result = courierService.getById(id);
        modelAndView.addObject("bean", result.getData());
        if (result.getData().getCategory().equals("支出")){
            modelAndView.setViewName(SYSTEM_ADMIN + "edit");
        }else {
            modelAndView.setViewName(SYSTEM_ADMIN + "edit2");
        }
        return modelAndView;
    }
    @RequestMapping(value = "broken/{id}", method = RequestMethod.GET)
    public ModelAndView broken(@PathVariable Integer id, ModelAndView modelAndView) {
        Result<RcIncome> result = courierService.getById(id);
        modelAndView.addObject("bean", result.getData());
        modelAndView.setViewName(SYSTEM_ADMIN + "broken");
        return modelAndView;
    }

    @RequestMapping(value = "addbroken", method = RequestMethod.POST)
    @ResponseBody
    public Result addbroken(Integer id,String remark) {
        RcIncome rcCourier = courierService.getById(id).getData();
        rcCourier.setStatus(0);
        rcCourier.setStatus_str("");
        rcCourier.setUpdate_date(DateUtil.format(new Date(),"yyy-MM-dd"));
        rcCourier.setRemark(remark);
        Result<Integer> result = new Result<>();
        result = courierService.update(rcCourier);
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(RcIncome rcCourier) {
        Result<Integer> result = new Result<>();
        result = courierService.update(rcCourier);
        return result;
    }
    @RequestMapping(value = "update2", method = RequestMethod.POST)
    @ResponseBody
    public Result update2(RcIncome rcCourier) {
        Result<Integer> result = new Result<>();
        result = courierService.update(rcCourier);
        return result;
    }
}
