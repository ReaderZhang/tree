package com.summer.tree.util;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: July
 * @Date: 2020-07-29 18:13
 **/
@Component
public class FileUtil {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    //定义可上传的文件类型
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png");

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
                return storePath.getFullPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        return urls;
    }
}
