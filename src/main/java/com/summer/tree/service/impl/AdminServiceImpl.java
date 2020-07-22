package com.summer.tree.service.impl;/*
@Author qqz
@create 2020-07-20  23:20
*/

import com.summer.tree.dao.AdminMapper;
import com.summer.tree.pojo.Admin;
import com.summer.tree.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin queryAdmin(String account , String password) {
        return adminMapper.SelectAdmin ( account, password );
    }
}
