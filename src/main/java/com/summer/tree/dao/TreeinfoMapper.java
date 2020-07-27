package com.summer.tree.dao;/*
@Author qqz
@create 2020-07-11  17:16
*/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summer.tree.pojo.Treeinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface TreeinfoMapper extends BaseMapper<Treeinfo> {
    //获得树的数量
    int SelectCount();
    //获得所有树的信息
    List<Treeinfo> SelectAllTree();
    //根据树的id获得树的信息
    Treeinfo SelectByTreeid(int id);
    //根据对应经纬度进行模糊查询
    @Select("select * from tree_info where longitude = #{longitude} and latitude = #{latitude}")
    List<Treeinfo> SelectTreesByLongitudeAndLatitude(@Param ( "longitude" ) String longitude, @Param ( "latitude" ) String latitude);
    //点赞
    void UpdateLikeAdd(int id);
    //取消点赞
    void UpdateLikeDelete(int id);

}
