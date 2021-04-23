package com.common.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.common.system.entity.RcUser;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RcUserMapper extends BaseMapper<RcUser> {

    RcUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RcUser record);

    RcUser getUserByName(String username);

    List<RcUser> getAllotUserList();
}