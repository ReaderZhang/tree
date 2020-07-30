package com.summer.tree.dao;/*
@Author qqz
@create 2020-07-21  15:00
*/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.summer.tree.pojo.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper extends BaseMapper<News> {
    News SelectNewsById(Integer id);
    News SelectNewsByTitle(String title);
    void InsertNews(News news);
    List<News> SelectAllNews();
    void UpdateNewsStatus(News news);
    void UpdateNews(News news);
    List<News> SelectNewsByStatus(int status);
}
