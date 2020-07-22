package com.summer.tree.service;/*
@Author qqz
@create 2020-07-21  15:19
*/

import com.summer.tree.pojo.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    List<News> queryNews();
    void addNews(News news);
    void removeNews(News news);
    void changeNews(News news);
    News queryANews1(String title);
    News queryANews2(Integer id);
}
