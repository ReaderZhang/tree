package com.summer.tree.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.summer.tree.util.GPSUtil;
import lombok.Data;

/**
 * @Description: 群落中的树
 * @Author: July
 * @Date: 2020-07-30 15:48
 **/
@TableName
@Data
public class Tree {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long groupId;
    private String number;
    private String name;
    private String age;
    private String image;
}
