package com.common.system.controller;

import com.common.system.entity.RcIncome;
import com.common.system.service.CourierService;
import com.common.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: wcy
 * @Date: 2020/1/31
 */
@Controller
@RequestMapping(value = "charts")
public class ChartsController extends BaseController {
    private static final String SYSTEM_ADMIN = "/system/admin/charts/";
    @Autowired
    private UserService userService;
    @Autowired
    private CourierService courierService;
    @RequestMapping(value = "delivery", method = RequestMethod.GET)
    public ModelAndView delivery(ModelAndView modelAndView) {
        Map<String,Object> monthmap = new HashMap<>();
        monthmap = getThreeMonth(monthmap);
        List<RcIncome> courierList = courierService.selectDate((String) monthmap.get("defaultEndDate"));
        monthmap = getStatusCount(monthmap, courierList,"data3");

        courierList = courierService.selectDate((String) monthmap.get("month1"));
        monthmap = getStatusCount(monthmap, courierList,"data2");

        courierList = courierService.selectDate((String) monthmap.get("month2"));
        monthmap = getStatusCount(monthmap, courierList,"data1");

        modelAndView.addObject("map",monthmap);
        modelAndView.setViewName(SYSTEM_ADMIN + "delivery");
        return modelAndView;
    }
    @RequestMapping(value = "delivery2", method = RequestMethod.GET)
    public ModelAndView delivery2(ModelAndView modelAndView) {
        Map<String,Object> monthmap = new HashMap<>();
        monthmap = getThreeMonth(monthmap);
        List<RcIncome> courierList = courierService.selectDate((String) monthmap.get("defaultEndDate"));
        monthmap = getStatusCount2(monthmap, courierList,"data3");

        courierList = courierService.selectDate((String) monthmap.get("month1"));
        monthmap = getStatusCount2(monthmap, courierList,"data2");

        courierList = courierService.selectDate((String) monthmap.get("month2"));
        monthmap = getStatusCount2(monthmap, courierList,"data1");

        modelAndView.addObject("map",monthmap);
        modelAndView.setViewName(SYSTEM_ADMIN + "delivery2");
        return modelAndView;
    }
    @RequestMapping(value = "revenue", method = RequestMethod.GET)
    public ModelAndView revenue(ModelAndView modelAndView) {

        Map<String,Object> monthmap = new HashMap<>();
        monthmap = getThreeMonth(monthmap);
        List<RcIncome> courierList = courierService.selectDate((String) monthmap.get("defaultEndDate"));

        monthmap = getStatusPrice(monthmap, courierList,"data3");

        courierList = courierService.selectDate((String) monthmap.get("month1"));
        monthmap = getStatusPrice(monthmap, courierList,"data2");

        courierList = courierService.selectDate((String) monthmap.get("month2"));
        monthmap = getStatusPrice(monthmap, courierList,"data1");


        modelAndView.addObject("map",monthmap);
        modelAndView.setViewName(SYSTEM_ADMIN + "revenue");
        return modelAndView;
    }

    private Map<String, Object> getThreeMonth(Map<String, Object> monthmap) {
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Date dBefore2 = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, -1);  //设置为前1月
        dBefore = calendar.getTime();   //得到前1月的时间
        Calendar calendar2 = Calendar.getInstance(); //得到日历
        calendar2.setTime(dNow);//把当前时间赋给日历
        calendar2.add(Calendar.MONTH, -2);  //设置为前1月
        dBefore2 = calendar2.getTime();   //得到前1月的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM"); //设置时间格式
        String month1 = sdf.format(dBefore);    //格式化前2月的时间
        String month2 = sdf.format(dBefore2);    //格式化前1月的时间
        String defaultEndDate = sdf.format(dNow); //格式化当前时间
        monthmap.put("month1", month1);
        monthmap.put("month2", month2);
        monthmap.put("month3", defaultEndDate);
        return monthmap;
    }

    private Map<String, Object> getStatusCount(Map<String, Object> monthmap, List<RcIncome> courierList, String name) {
        monthmap.put(name+"_1",courierList.stream().filter(item->item.getType().equals("饮食")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_2",courierList.stream().filter(item->item.getType().equals("交通")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_3",courierList.stream().filter(item->item.getType().equals("娱乐")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_4",courierList.stream().filter(item->item.getType().equals("购物")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_5",courierList.stream().filter(item->item.getType().equals("医疗")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_6",courierList.stream().filter(item->item.getType().equals("家居")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_7",courierList.stream().filter(item->item.getType().equals("生活")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_8",courierList.stream().filter(item->item.getType().equals("学习")).mapToDouble(RcIncome::getPrice).sum());
        return monthmap;
    }
    private Map<String, Object> getStatusCount2(Map<String, Object> monthmap, List<RcIncome> courierList, String name) {
        monthmap.put(name+"_1",courierList.stream().filter(item->item.getType().equals("薪水")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_2",courierList.stream().filter(item->item.getType().equals("奖金")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_3",courierList.stream().filter(item->item.getType().equals("投资")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_4",courierList.stream().filter(item->item.getType().equals("还款")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_5",courierList.stream().filter(item->item.getType().equals("彩票")).mapToDouble(RcIncome::getPrice).sum());
        monthmap.put(name+"_6",courierList.stream().filter(item->item.getType().equals("福利")).mapToDouble(RcIncome::getPrice).sum());
        return monthmap;
    }
    private Map<String, Object> getStatusPrice(Map<String, Object> monthmap, List<RcIncome> courierList, String name) {
        monthmap.put(name+"_1",courierList.stream().filter(item->item.getCategory().equals("支出")).mapToDouble(RcIncome::getPrice).sum()+"");
        monthmap.put(name+"_2",courierList.stream().filter(item->item.getCategory().equals("收入")).mapToDouble(RcIncome::getPrice).sum()+"");
        return monthmap;
    }
}
