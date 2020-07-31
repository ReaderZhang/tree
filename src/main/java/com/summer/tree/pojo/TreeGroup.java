package com.summer.tree.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-07-30 15:50
 **/
@TableName
@Data
public class TreeGroup {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String number;
    private String address;
}
