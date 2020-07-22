package com.summer.tree.dao;/*
@Author qqz
@create 2020-07-20  23:15
*/

import com.summer.tree.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    Admin SelectAdmin(@Param ( "account" ) String account,@Param ( "password" ) String password);
}
