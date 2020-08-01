package com.summer.tree.service.impl;/*
@Author qqz
@create 2020-07-21  15:26
*/

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.summer.tree.dao.NewsMapper;
import com.summer.tree.dto.NewsDto;
import com.summer.tree.pojo.News;
import com.summer.tree.service.NewsService;
import com.summer.tree.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private FileUtil fileUtil;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public List<News> getAll() {
        return newsMapper.SelectAllNews ();
    }

    @Override
    public void addNews(NewsDto newsDto, List<MultipartFile> files, List<MultipartFile> poster) {
        String pictureUrl = "";
        News news = new News();
        if (files!=null && files.size()>0) {
            List<String> urls = fileUtil.uploadImage(files);
            pictureUrl = StringUtils.join(urls, ",");
            news.setImage(pictureUrl);
        }
        if (poster!=null && poster.size()>0) {
            List<String> posterUrl = fileUtil.uploadImage(poster);
            news.setPoster(pictureUrl);
        }
        news.setTitle(newsDto.getTitle());
        news.setContent(newsDto.getContent());
        news.setCreatedate(sdf.format(new Date()));
        newsMapper.insert(news);
    }

    @Override
    public void removeNews(News news) {

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

    @Override
    public List<News> getNews(int status) {
        return newsMapper.SelectNewsByStatus(status);
    }

}
