package com.summer.tree.service.impl;/*
@Author qqz
@create 2020-07-11  18:07
*/

import com.summer.tree.dao.TreeinfoMapper;
import com.summer.tree.pojo.Treeinfo;
import com.summer.tree.service.TreeinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeinfoServiceImpl implements TreeinfoService {
    @Autowired
    TreeinfoMapper treeinfoMapper;
    @Override
    public List<Treeinfo> findTrees() {
        return treeinfoMapper.SelectAllTree ();
    }

    @Override
    public Treeinfo findTreeByid(int id) {
        return treeinfoMapper.SelectByTreeid ( id );
    }

    @Override
    public List<Treeinfo> findTreesByMapinfo(String longitude , String latitude) {
        return treeinfoMapper.SelectTreesByLongitudeAndLatitude ( longitude, latitude );
    }

    @Override
    public void addTree(Treeinfo treeinfo) {
        treeinfoMapper.InsertTree ( treeinfo );
    }

    @Override
    public Integer findNum() {
        return treeinfoMapper.SelectCount ();
    }
}
