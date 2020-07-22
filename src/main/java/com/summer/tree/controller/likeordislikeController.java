package com.summer.tree.controller;/*
@Author qqz
@create 2020-07-15  11:24
*/

import com.summer.tree.Response.ResponseResult;
import com.summer.tree.pojo.Userpost;
import com.summer.tree.service.UserpostService;
import com.summer.tree.util.IpUtil;
import com.summer.tree.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class likeordislikeController {
    @Autowired
    private UserpostService userpostService;
    @Resource
    private RedisUtil redisUtil;

    @PutMapping("/tree/like/{treeid}")
    public ResponseResult like(@PathVariable("treeid")int treeid, HttpServletRequest request){
        String ip = IpUtil.getCliectIp ( request );
        Userpost userpost = new Userpost ();
        userpost.setUserpost ( ip+"::"+treeid );
        String info = userpostService.like ( userpost,treeid );
        return ResponseResult.Sucess (info);
    }
    @PutMapping("/tree/dislike/{treeid}")
    public ResponseResult dislike(@PathVariable("treeid")int treeid,HttpServletRequest request){
        String ip = IpUtil.getCliectIp ( request );
        Userpost userpost = new Userpost ();
        userpost.setUserpost ( ip+"::"+treeid );
        String info = userpostService.dislike ( userpost,treeid );
        return ResponseResult.Sucess (info);
    }
}
