package com.common.system.controller;

import com.common.system.entity.RcCar;
import com.common.system.entity.RcCar;
import com.common.system.entity.RcUseCar;
import com.common.system.entity.RcUser;
import com.common.system.service.CarService;
import com.common.system.service.UserService;
import com.common.system.util.MsgCode;
import com.common.system.util.PageBean;
import com.common.system.util.Result;
import com.github.pagehelper.PageInfo;
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
 * @Date: 2020/1/31
 */
@Controller
@RequestMapping(value = "car")
public class CarController extends BaseController {

    private static final String SYSTEM_ADMIN = "/system/admin/car/";
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
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
    @RequestMapping(value = "uselist", method = RequestMethod.GET)
    public ModelAndView uselist(ModelAndView modelAndView) {
        modelAndView.setViewName(SYSTEM_ADMIN + "uselist");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "page")
    public PageBean<RcCar> queryForPage(@RequestParam(value = "start", defaultValue = "1") int start,
                                        @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "bank", required = false) String bank,
                                        @RequestParam(value = "card", required = false) String card,
                                        @RequestParam(value = "status", required = false,defaultValue = "1") String status,
                                        HttpServletRequest request) {
        Map<String,String[]> params = request.getParameterMap();
        PageInfo<RcCar> pageInfo = carService.listForPage((start / pageSize) + 1, pageSize,status,bank,card);
        return new PageBean<RcCar>(pageInfo);
    }
    @ResponseBody
    @RequestMapping(value = "page2")
    public PageBean<RcCar> queryForPage2(@RequestParam(value = "start", defaultValue = "1") int start,
                                        @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "date", required = false) String date,
                                        @RequestParam(value = "search", required = false) String search,
                                        @RequestParam(value = "status", required = false,defaultValue = "1") String status,
                                        HttpServletRequest request) {
        Map<String,String[]> params = request.getParameterMap();
        PageInfo<RcCar> pageInfo = carService.listForPage((start / pageSize) + 1, pageSize, String.valueOf(2));
        return new PageBean<RcCar>(pageInfo);
    }
    @ResponseBody
    @RequestMapping(value = "usepage")
    public PageBean<RcUseCar> queryuseForPage(@RequestParam(value = "start", defaultValue = "1") int start,
                                              @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "date", required = false) String date,
                                              @RequestParam(value = "search", required = false) String search,
                                              HttpServletRequest request) {
        Map<String,String[]> params = request.getParameterMap();
        PageInfo<RcUseCar> pageInfo = carService.uselistForPage((start / pageSize) + 1, pageSize);
        return new PageBean<RcUseCar>(pageInfo);
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
    public Result save(RcCar rcCar) {
        rcCar.setCreate_date(new Date());
        rcCar.setUpdate_date(new Date());
//        rcCar.setStatus(1);
        Result<Integer> result = carService.save(rcCar);
        return result;
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable Integer id) {
        Result<Integer> result = carService.deleteById(id);
        return result;
    }
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer id, ModelAndView modelAndView) {
        Result<RcCar> result = carService.getById(id);
        modelAndView.addObject("bean", result.getData());
        if (result.getData().getStatus().equals(1)){
            modelAndView.setViewName(SYSTEM_ADMIN + "edit");
        }else {
            modelAndView.setViewName(SYSTEM_ADMIN + "edit2");
        }
        return modelAndView;
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(RcCar rcCar) {
        rcCar.setUpdate_date(new Date());
        Result<Integer> result = new Result<>();
        result = carService.update(rcCar);
        return result;
    }
    @RequestMapping(value = "use/{id}")
    public ModelAndView use(@PathVariable Integer id, ModelAndView modelAndView){
        modelAndView.addObject("id", id);
        List<RcUser> userList = userService.getAllotUserList();
        modelAndView.addObject("users", userList);
        modelAndView.setViewName(SYSTEM_ADMIN + "use");
        return modelAndView;
    }
//    @RequestMapping(value = "adduse", method = RequestMethod.POST)
//    @ResponseBody
//    public Result adduse(RcCar rcCar) {
//        RcCar rccar = carService.getById(id).getData();
//        RcUser user = userService.getById(userid).getData();
//        RcUseCar rcUseCar = new RcUseCar(user.getName(),user.getId().toString(),use_date,rccar.getName(),remark);
//        Result<Integer> result = new Result<>();
//        carService.addUseCar(rcUseCar);
//        result.setStatus(true);
//        result.setCode(MsgCode.SUCCESS);
//        result.setMsg("操作成功");
//        return result;
//    }

}
