package com.summer.tree.service.impl;/*
@Author qqz
@create 2020-07-15  14:39
*/

import com.summer.tree.dao.TreeinfoMapper;
import com.summer.tree.dao.UserpostMapper;
import com.summer.tree.pojo.Userpost;
import com.summer.tree.service.UserpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserpostServiceImpl implements UserpostService {
    @Autowired
    private TreeinfoMapper treeinfoMapper;
    @Autowired
    private UserpostMapper userpostMapper;
    @Override
    public String like(Userpost userpost , int treeid) {
        Userpost userpost1 = userpostMapper.SelectUserpostByPost ( userpost.getUserpost () );
        if (ObjectUtils.isEmpty ( userpost )){
            treeinfoMapper.UpdateLikeAdd ( treeid );
        }else{
            if (userpost.getStatus ()!="0")
                return "同一ip不能点赞多次";
        }
        return null;
    }

    @Override
    public String dislike(Userpost userpost , int treeid) {
        Userpost userpost1 = userpostMapper.SelectUserpostByPost ( userpost.getUserpost () );
        if (ObjectUtils.isEmpty ( userpost1 )){
            return "要先点赞才能取消点赞";
        }else {
            if (userpost1.getStatus ()!="1")
                return "不能取消点赞多次";
        }
        userpost.setStatus ( "0" );
        System.out.println (userpost);
        userpostMapper.Updatestatus ( userpost );
        treeinfoMapper.UpdateLikeDelete ( treeid );
        return null;
    }
}
