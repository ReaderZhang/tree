package com.summer.tree.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summer.tree.pojo.TreeGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TreeGroupMapper extends BaseMapper<TreeGroup> {

    @Select("select points from tree_group where number = #{number}")
    String SelectPointsByNum(@Param("number") String number);

    @Select("select number from tree_group")
    List<String> SelectAllNum();
}
