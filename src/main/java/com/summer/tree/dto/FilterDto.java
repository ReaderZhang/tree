package com.summer.tree.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-07-28 16:17
 **/
@Data
public class FilterDto {
    private String type;
    private String growthCondition;
    private String bust;
    private String distribution;
}
