package com.summer.tree.pojo;/*
@Author qqz
@create 2020-07-11  17:02
*/

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tree_info")
public class Treeinfo {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 编号
     */
    private String number;

    /**
     * 树木类型
     */
    private int type;

    /**
     * 分布区
     */
    private String distribution;

    /**
     * 科
     */
    private String family;

    /**
     * 属
     */
    private String genus;

    /**
     * 种
     */
    private String species;

    /**
     * 俗名
     */
    private String alias;

    /**
     * 拉丁名
     */
    private String latin;

    /**
     * 地名
     */
    private String placeName;

    /**
     * 具体地名
     */
    private String specificPlaceName;

    /**
     * 生长场所
     */
    private String growthField;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 权属
     */
    private String ownership;

    /**
     * 名木类型
     */
    private String form;

    /**
     * 栽培人
     */
    private String planter;

    /**
     * 栽培时间
     */
    private String plantTime;

    /**
     * 真实树龄
     */
    private String realAge;

    /**
     * 估测树龄
     */
    private String estimateAge;

    /**
     * 树高
     */
    private Double height;

    /**
     * 胸围/胸径
     */
    private String bust;

    /**
     * 平均冠幅
     */
    private Double average;

    /**
     * 南北冠幅
     */
    private Double ns;

    /**
     * 东西冠幅
     */
    private Double ew;

    /**
     * 生长势
     */
    private String growthCondition;

    /**
     * 生长环境
     */
    private String environmental;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 古树历史、名木介绍
     */
    private String detail;

    /**
     * 管护单位
     */
    private String keepUnit;

    /**
     * 管护人
     */
    private String keeper;

    /**
     * 地上保护情况
     */
    private String protect;

    /**
     * 养护复壮现状
     */
    private String curing;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 调查日期
     */
    private String inquiryTime;

    private String reason;

    private String environmentalFactor;

    /**
     * 是否为审核中
     */
    private int status;
    /**
     * 是否为游客画
     */
    private String isdraw;
    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 图片url
     */
    private String pictureurl;

    /**
     * 二维码url
     */
    private String zxingurl;
}
