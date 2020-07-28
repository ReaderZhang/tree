package com.summer.tree.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summer.tree.pojo.Preparer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface PreparerMapper extends BaseMapper<Preparer> {

    @Insert("insert into preparer_tree (pid,tid) values( #{pid},#{tid} )")
    void insertPreparer_Tree(Long pid,  Long tid);
}
