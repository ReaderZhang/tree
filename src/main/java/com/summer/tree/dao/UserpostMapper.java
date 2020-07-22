package com.summer.tree.dao;/*
@Author qqz
@create 2020-07-15  14:25
*/

import com.summer.tree.pojo.Userpost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserpostMapper {
    List<Userpost> SelectUserpost();
    void InsertUserpost(Userpost userpost);
    void Updatestatus(Userpost userpost);
    Userpost SelectUserpostByPost(String userpost);
}
