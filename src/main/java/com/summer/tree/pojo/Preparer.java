package com.summer.tree.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-07-28 10:16
 **/
@TableName("preparer")
@Data
public class Preparer {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String phone;

    private String email;

    private Long oldid ;

    private String createTime;
}

