package com.summer.tree.service;/*
@Author qqz
@create 2020-07-15  14:37
*/

import com.summer.tree.pojo.Userpost;
import org.springframework.stereotype.Service;

@Service
public interface UserpostService {
    String like(Userpost userpost,int treeid);
    String dislike(Userpost userpost,int treeid);
}
