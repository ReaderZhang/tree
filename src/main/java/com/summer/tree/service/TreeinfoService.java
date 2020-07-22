package com.summer.tree.service;/*
@Author qqz
@create 2020-07-11  17:59
*/

import com.summer.tree.pojo.Treeinfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TreeinfoService {
    List<Treeinfo> findTrees();
    Treeinfo findTreeByid(int treeid);
    List<Treeinfo> findTreesByMapinfo(String longitude,String latitude);
    void addTree(Treeinfo treeinfo);
    Integer findNum();
}
