package com.summer.tree.dao;/*
@Author qqz
@create 2020-07-11  17:16
*/

import com.summer.tree.pojo.Treeinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TreeinfoMapper {
    //获得树的数量
    int SelectCount();
    //获得所有树的信息
    List<Treeinfo> SelectAllTree();
    //根据树的id获得树的信息
    Treeinfo SelectByTreeid(int id);
    //根据对应经纬度进行模糊查询
    List<Treeinfo> SelectTreesByLongitudeAndLatitude(@Param ( "longitude" ) String longitude, @Param ( "latitude" ) String latitude);
    //插入信息
    void InsertTree(Treeinfo treeinfo);
    //点赞
    void UpdateLikeAdd(int id);
    //取消点赞
    void UpdateLikeDelete(int id);

}
