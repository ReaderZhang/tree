package com.summer.tree.controller;/*
@Author qqz
@create 2020-07-20  23:21
*/

import com.summer.tree.Response.ResponseResult;
import com.summer.tree.pojo.Admin;
import com.summer.tree.service.AdminService;
import com.summer.tree.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/login/{account}/{password}")
    public ResponseResult login(@PathVariable("account") String account,@PathVariable("password") String password){
        log.info ( "account:{},password:{}",account,password );
        Admin admin = adminService.queryAdmin ( account , password );
        log.info ( admin.toString () );
        if (admin==null){
            return ResponseResult.Sucess ("账号或密码错误");
        }
        JwtUtil jwtUtil = new JwtUtil ();
        Map<String,Object> chaim = new HashMap<> ();
        chaim.put ( "account",account );
        String jwtToken = jwtUtil.encode ( account,30*60*1000,chaim );
        Map<String,Object> map = new HashMap<> ();
        map.put ( "info","登陆成功" );
        map.put ( "token",jwtToken );
        map.put ( "admin",admin );
        return  ResponseResult.Sucess (map);

    }
}
