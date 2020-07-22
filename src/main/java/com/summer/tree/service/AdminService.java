package com.summer.tree.service;/*
@Author qqz
@create 2020-07-20  23:20
*/

import com.summer.tree.pojo.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Admin queryAdmin(String account,String password);
}
