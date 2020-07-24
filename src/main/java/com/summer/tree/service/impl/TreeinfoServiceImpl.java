package com.summer.tree.service.impl;/*
@Author qqz
@create 2020-07-11  18:07
*/

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.summer.tree.dao.TreeinfoMapper;
import com.summer.tree.pojo.Treeinfo;
import com.summer.tree.service.TreeinfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeinfoServiceImpl implements TreeinfoService {
    @Autowired
    TreeinfoMapper treeinfoMapper;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    //定义可上传的文件类型
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png");


    @Override
    public List<Treeinfo> findTrees() {
        return treeinfoMapper.SelectAllTree();
    }

    @Override
    public Treeinfo findTreeByid(int id) {
        return treeinfoMapper.SelectByTreeid(id);
    }

    @Override
    public List<Treeinfo> findTreesByMapinfo(String longitude, String latitude) {
        return treeinfoMapper.SelectTreesByLongitudeAndLatitude(longitude, latitude);
    }

    @Override
    public void addTree(Treeinfo treeinfo) {
        treeinfoMapper.InsertTree(treeinfo);
    }

    @Override
    public Integer findNum() {
        return treeinfoMapper.SelectCount();
    }

    @Override
    public List<String> uploadImage(List<MultipartFile> files) {
        List<String> urls = files.stream().map(file -> {
            String originalFilename = file.getOriginalFilename();

            //检验文件类型
            String contentType = file.getContentType();
            if (!CONTENT_TYPES.contains(contentType)) {
                return null;
            }

            try {
                //检验文件内容
                BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
                if (bufferedImage == null) {
                    return null;
                }

                //保存
                String ext = StringUtils.substringAfterLast(originalFilename, ".");
                StorePath storePath = this.fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

                //回显图片
                return "http://39.97.235.23:9999/" + storePath.getFullPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        return urls;
    }
}
