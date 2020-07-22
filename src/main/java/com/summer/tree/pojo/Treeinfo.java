package com.summer.tree.pojo;/*
@Author qqz
@create 2020-07-11  17:02
*/

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Treeinfo {
    private Long id;
    private String number;
    private String distribution;
    private String family;
    private String genus;
    private String species;
    private String alias;
    private String latin;
    private String place_name;
    private String specific_place_name;
    private String growth_field;
    private String longitude;
    private String latitude;
    private String ownership;
    private String type;
    private String planter;
    private String plant_time;
    private String estimate_age;
    private String real_age;
    private Double height;
    private String bust;
    private Double average;
    private Double ns;
    private Double ew;
    private String growth_condition;
    private String environmental;
    private String remarks;
    private String detail;
    private String keep_unit;
    private String protect;
    private String reason;
    private String environmental_factor;
    private String curing;
    private String inquiry_time;
    private Long likes;
    private String isdraw;
    private String pictureurl;
    private String zxingurl;
}
