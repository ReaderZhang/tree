package com.summer.tree.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: tree_info过滤类
 * @Author: July
 * @Date: 2020-07-28 16:17
 **/
@Data
public class FilterDto {
    private String type;
    private String growthCondition;
    private String bust;
    private String distribution;
    //几个月以前
    private String month;
}
