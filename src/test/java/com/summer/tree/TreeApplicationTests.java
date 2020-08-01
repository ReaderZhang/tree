//package com.summer.tree;
//
//import cn.hutool.core.date.DateField;
//import cn.hutool.core.date.DateTime;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.github.tobato.fastdfs.domain.StorePath;
//import com.github.tobato.fastdfs.service.FastFileStorageClient;
//import com.google.zxing.WriterException;
//import com.summer.tree.dao.TreeGroupMapper;
//import com.summer.tree.dao.TreeMapper;
//import com.summer.tree.dao.TreeinfoMapper;
//import com.summer.tree.pojo.Tree;
//import com.summer.tree.pojo.TreeGroup;
//import com.summer.tree.pojo.Treeinfo;
//import com.summer.tree.util.FileUtil;
//import com.summer.tree.util.GPSUtil;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.*;
//
//@SpringBootTest
//class TreeApplicationTests {
//
//    @Autowired
//    private TreeMapper treeMapper;
//    @Autowired
//    private FastFileStorageClient fastFileStorageClient;
//    @Autowired
//    private TreeGroupMapper treeGroupMapper;
//    @Autowired
//    private TreeinfoMapper treeinfoMapper;


//    @Test
//    public void test() throws IOException {
//        File html = new File("C:\\Users\\PC\\Desktop\\群落范例\\“湛江市博物馆群落”文件夹\\1.html");
//        Document doc = Jsoup.parse(html, "UTF-8");
//        Element body = doc.body();
//        Elements elements = body.getElementsByClass("基本文本框架");
//
////        for (int i =12 ;i<elements.size();i++) {
//        Tree tree = new Tree();
//        Element element = elements.get(12);
//        Elements ps = element.getAllElements();
//        tree.setNumber(ps.get(1).text().split("：")[1]);
//        tree.setName(ps.get(2).text().split("：")[1]);
//        tree.setAge(ps.get(3).text().split("：")[1]);
//
//        System.out.println(element.text());
//        Element picEle = element;
//        List<String> paths = new ArrayList<>();
//        while (true) {
//            picEle = picEle.nextElementSibling();
//            if (picEle == null) {
//                break;
//            }
//            String src = picEle.select("._idGenObjectAttribute-1").attr("src");
//
//            if (!src.equals("")) {
//                File file = new File("C:\\Users\\PC\\Desktop\\群落范例\\“湛江市博物馆群落”文件夹\\" + src);
//                String ext = StringUtils.substringAfterLast(src, ".");
//                FileInputStream fileInputStream = new FileInputStream(file);
//                StorePath storePath = fastFileStorageClient.uploadFile(fileInputStream, file.length(), ext, null);
//                String path = storePath.getFullPath();
//                paths.add(path);
//            } else {
//                break;
//            }
//        }
//        String pathResult = StringUtils.join(paths, ",");
//        tree.setImage(pathResult);
//        treeMapper.insert(tree);
//    }

    //    }
//    @Test
//    public void getQR() throws IOException {
//        String name = UUID.randomUUID().toString().substring(0, 6) + ".jpg";
//        String code = "C:\\Users\\PC\\Desktop\\code2\\";
//        String picUrl = "http://czhhzc.cn:9000/treedetail/";
//        for (int i = 800; i < 863; i++) {
//            String path = picUrl + i;
//            ZXingUtil2.generateQRImage(path, name, "jpg");
//            File file = new File(code + name);
//            FileInputStream inputStream = new FileInputStream(file);
//            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), "jpg", null);
//            String url = storePath.getFullPath();
//            Long id = Long.parseLong(String.valueOf(i));
//            treeMapper.updateImage(url, id);
//        }
//    }
//}

