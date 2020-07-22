package com.summer.tree.controller;/*
@Author qqz
@create 2020-07-21  15:35
*/

import com.summer.tree.Response.ResponseResult;
import com.summer.tree.dto.NewsDto;
import com.summer.tree.pojo.News;
import com.summer.tree.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class NewsController {
    private SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
    @Autowired
    private NewsService newsService;
    @GetMapping("/manager/news/getAll")
    public ResponseResult getAll(){
        return ResponseResult.Sucess (newsService.queryNews ());
    }
    @PutMapping("/manager/news/insert")
    public ResponseResult insert(@RequestBody NewsDto newsDto){
        News news = new News ();
        news.setTitle ( newsDto.getTitle () );
        news.setContent ( newsDto.getContent () );
        news.setCreatedate ( sdf.format ( new Date () ) );
        newsService.addNews ( news );
        return  ResponseResult.Sucess ("插入成功");
    }
    @DeleteMapping("/manager/news/delete/{info}")
    public ResponseResult delete(@PathVariable("info") String info){
        News news = new News ();
        if (info.matches ( "[0-9]+" ))
            news = newsService.queryANews2 ( Integer.valueOf ( info ) );
        else
            news = newsService.queryANews1 ( info );
        newsService.removeNews ( news );
        return ResponseResult.Sucess ("删除成功");
    }
    @PutMapping("/manager/news/change")
    public ResponseResult change(@RequestBody NewsDto newsDto){
        News news = new News ();
        news = newsService.queryANews1 ( newsDto.getTitle () );
        newsService.changeNews ( news );
        return ResponseResult.Sucess ("修改成功");
    }

}
