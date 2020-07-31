package com.summer.tree.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summer.tree.pojo.Tree;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-07-30 16:09
 **/
public interface TreeMapper extends BaseMapper<Tree> {

    @Update("update tree_info set zxingurl = #{url} where id = #{id}")
    void updateImage(@Param("url") String url, @Param("id") Long id);

}
