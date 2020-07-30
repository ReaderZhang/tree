package com.summer.tree.pojo;/*
@Author qqz
@create 2020-07-21  14:54
*/

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("news")
public class News {
    private Integer id;
    private String title;
    private String content;
    private String createdate;
    private int status;
    private String image;
}
