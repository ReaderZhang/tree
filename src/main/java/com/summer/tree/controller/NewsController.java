package com.summer.tree.controller;/*
@Author qqz
@create 2020-07-21  15:35
*/

import com.summer.tree.Response.ResponseResult;
import com.summer.tree.dto.NewsDto;
import com.summer.tree.pojo.News;
import com.summer.tree.service.NewsService;
import com.summer.tree.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;


    @GetMapping("/manager/news/{status}")
    public ResponseResult getnews(@PathVariable("status") int status) {
        List<News> news = new ArrayList<>();
        if (status == 0 || status == 1) {
            news = newsService.getNews(status);
        } else if (status == 2) {
            news = newsService.getAll();
        }
        return ResponseResult.Sucess(news);
    }


    @PostMapping("/manager/news/insert")
    public ResponseResult insert(NewsDto newsDto, @RequestParam(value = "image",required = false) List<MultipartFile> files,@RequestParam(value = "poster",required = false) List<MultipartFile> poster ){

        newsService.addNews(newsDto,files,poster);
        return ResponseResult.Sucess("插入成功");
    }

    @DeleteMapping("/manager/news/delete/{info}")
    public ResponseResult delete(@PathVariable("info") String info) {
        News news = new News();
        if (info.matches("[0-9]+"))
            news = newsService.queryANews2(Integer.valueOf(info));
        else
            news = newsService.queryANews1(info);
        newsService.removeNews(news);
        return ResponseResult.Sucess("删除成功");
    }

    @PutMapping("/manager/news/change")
    public ResponseResult change(@RequestBody NewsDto newsDto) {
        News news = new News();
        news = newsService.queryANews1(newsDto.getTitle());
        newsService.changeNews(news);
        return ResponseResult.Sucess("修改成功");
    }

}
