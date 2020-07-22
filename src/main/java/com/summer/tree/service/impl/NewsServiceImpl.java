package com.summer.tree.service.impl;/*
@Author qqz
@create 2020-07-21  15:26
*/

import com.summer.tree.dao.NewsMapper;
import com.summer.tree.pojo.News;
import com.summer.tree.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public List<News> queryNews() {
        return newsMapper.SelectAllNews ();
    }

    @Override
    public void addNews(News news) {
        newsMapper.InsertNews ( news);
    }

    @Override
    public void removeNews(News news) {
        newsMapper.UpdateNewsStatus ( news );
    }

    @Override
    public void changeNews(News news) {
        newsMapper.UpdateNews ( news );
    }

    @Override
    public News queryANews1(String title) {
        return newsMapper.SelectNewsByTitle ( title );
    }

    @Override
    public News queryANews2(Integer id) {
        return newsMapper.SelectNewsById ( id );
    }
}
