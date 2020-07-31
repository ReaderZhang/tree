package com.summer.tree.controller;/*
@Author qqz
@create 2020-07-11  18:28
*/

import com.summer.tree.Response.ResponseResult;
import com.summer.tree.dao.PreparerMapper;
import com.summer.tree.dto.FilterDto;
import com.summer.tree.pojo.Preparer;
import com.summer.tree.pojo.Treeinfo;
import com.summer.tree.service.TreeinfoService;
import com.summer.tree.util.FileUtil;
import com.summer.tree.vo.TreeinfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.summer.tree.util.ZxingUtil.getZxing;
import static java.lang.String.valueOf;

@CrossOrigin
@RestController
public class TreeinfoController {
    @Autowired
    private TreeinfoService treeinfoService;
    @Autowired
    private FileUtil fileUtil;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    @Value("${Zxingpath}")
    private String zxingpath;
    @Value("${Picturepath}")
    private String picture;
    @Value("${zxingurl}")
    private String zxingurl;
    @Value("${pictureurl}")
    private String pictureurl;

    //    @PostMapping(value = "/tree/upload")
    public ResponseResult upload(@RequestParam("file") @RequestBody MultipartFile[] uploadfiles, HttpSession session) {
        File targetFile = new File(picture);
        FileOutputStream out = null;
        File file = null;
        String[] strings = new String[uploadfiles.length];
        try {
            for (int i = 0; i < uploadfiles.length; i++) {
                file = new File(picture + uploadfiles[i].getOriginalFilename());
                String filename = uploadfiles[i].getOriginalFilename();
                String randomstamp = (valueOf((int) (Math.random() * 10))) + sdf.format(new Date()) + uploadfiles[i].getOriginalFilename();
                String realName = picture + randomstamp;
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                out = new FileOutputStream(realName);
                strings[i] = realName;
                out.write(uploadfiles[i].getBytes());
                out.close();
            }
            session.setAttribute("pictures", strings);
            return ResponseResult.Sucess("上传成功");
        } catch (IOException e) {
            return ResponseResult.Sucess(e.getMessage());
        }

//        File targetFile = new File ( picture );
//
//        File file = new File ( picture+uploadfile.getOriginalFilename () );
//        String filename = uploadfile.getOriginalFilename ();
//        String randomstamp = (String.valueOf ( (int)(Math.random ()*10)))+sdf.format ( new Date () )+uploadfile.getOriginalFilename ();
//        String realName = picture+randomstamp;
//        if(!targetFile.exists ()){
//            targetFile.mkdirs ();
//        }
//        try{
//            FileOutputStream out = new FileOutputStream ( realName );
//            session.setAttribute ( "picture",realName );
//            out.write ( uploadfile.getBytes () );
//            out.close ();
//            return ResponseResult.Sucess ("上传成功");
//        }catch (IOException e){
//            return ResponseResult.Fail (e.getMessage ());
//        }
    }

//    @PostMapping(value = "/tree/upload")
//    public ResponseResult updatePicture(@RequestParam("files") List<MultipartFile> files){
//        List<String> urls = this.treeinfoService.uploadImage(files);
//
//        if(CollectionUtils.isEmpty(urls)){
//            return ResponseResult.Fail();
//        }
//        return ResponseResult.Sucess();
//    }

    @GetMapping("/tree/findtree/{longitude}/{latitude}/fs")
    public ResponseResult findTree(@PathVariable("longitude") String longitude, @PathVariable("latitude") String latitude) {
        System.out.println("longitude.length==========>" + longitude.length() + "================>" + longitude);
        System.out.println("latitude.length==========>" + latitude.length() + "===============>" + latitude);
        longitude = longitude.substring ( 0,longitude.indexOf ( '.' )+3 );
        latitude = latitude.substring ( 0,latitude.indexOf ( '.' )+3 );
        System.out.println("longitude.length==========>" + longitude.length() + "================>" + longitude);
        System.out.println("latitude.length==========>" + latitude.length() + "===============>" + latitude);
        return ResponseResult.Sucess(treeinfoService.findTreesByMapinfo(longitude, latitude));
    }

    @PostMapping("/tree/insert")
    public ResponseResult addInfo(Treeinfo treeinfo, Preparer preparer, @RequestParam("files") List<MultipartFile> files) {
        String zxinglocalpath = "";
        String pictureUrl = "";
        String zxingname = "";
        String inserturl = "";
        List<String> urls = fileUtil.uploadImage(files);
        pictureUrl = StringUtils.join(urls, ",");
//        getZxing(inserturl,zxinglocalpath+".png");
        treeinfo.setPictureurl(pictureUrl);
        treeinfo.setZxingurl("");
        preparer.setCreateTime(new Date());
        treeinfoService.addTree(treeinfo,preparer);
        return ResponseResult.Sucess("登记成功");
    }

    @PostMapping("/tree/update")
    public ResponseResult update(Treeinfo treeinfo, Preparer preparer, @RequestParam("files") List<MultipartFile> files) {
        String pictureUrl = "";
        List<String> urls = fileUtil.uploadImage(files);
        pictureUrl = StringUtils.join(urls, ",");
        treeinfo.setPictureurl(pictureUrl);
        treeinfo.setZxingurl("");
        treeinfo.setStatus(1);
        preparer.setCreateTime(new Date());
        preparer.setOldid(treeinfo.getId());
        treeinfoService.addTree(treeinfo,preparer);
        return ResponseResult.Sucess("登记成功");
    }

    @PutMapping("/tree/dont-touch/{num}")
    public ResponseResult dont_touch(@PathVariable("num") Integer num) {

        int start = treeinfoService.findNum();
        String[] type = {"杨树", "柳树", "枫树"};
        String[] sci = {"小白杨", "小柳树", "小红枫"};
        for (; start < num; start++) {
            String newzxingurl = "http://czhhzc.cn/image/tree/zxing/";
            Treeinfo treeinfo = new Treeinfo();
            String longitude = null;
            String latitude = null;
            DecimalFormat df = new DecimalFormat("#.00");
            String treename = sci[start % 3] + valueOf(start);
            String sciname = type[start % 3] + valueOf(start);
            String length = valueOf(df.format(Math.random() * 1.8));
            String experience = "experience=>" + valueOf(start);
            String circu = "c" + start % 100;
            String email = "email=>" + valueOf(start);
            String zxinglocalpath = zxingpath;
            int age = (int) (Math.random() * 50);
            longitude = valueOf(df.format(Math.random() * 63 + 73.5));
            latitude = valueOf(df.format(Math.random() * 50 + 3.51));
            zxinglocalpath += start + ".png";
            getZxing(valueOf(start), zxinglocalpath);
            newzxingurl += start + ".png";

            treeinfo.setLongitude(longitude);
            treeinfo.setLatitude(latitude);
            treeinfo.setZxingurl(newzxingurl + ".png");
//            treeinfoService.addTree(treeinfo);
        }
        return ResponseResult.Sucess();
    }

    @GetMapping("/tree/getall")
    public ResponseResult getall() {
//        System.out.println (treeinfoService.findTrees ().size ());
        return ResponseResult.Sucess(treeinfoService.findTrees());
    }

    @PostMapping("tree/filter")
    public ResponseResult filter(@RequestBody FilterDto filterDto) {
        List<Treeinfo> treeinfos = treeinfoService.filter(filterDto);
        return ResponseResult.Sucess(treeinfos);
    }

    @GetMapping("tree/ischeck")
    public ResponseResult getCheckTree() {
        List<TreeinfoVo> treeinfos = treeinfoService.getCheckTree();
        return ResponseResult.Sucess(treeinfos);
    }

    /**
     * 审核通过
     * @param ids
     * @return
     */
    @PostMapping("tree/pass")
    public ResponseResult CheckPass(Long[] ids){
        treeinfoService.CheckPass(ids);
        return ResponseResult.Sucess("审核通过");
    }

    @PostMapping("tree/down")
    public ResponseResult Checkdown(Long[] ids){
        treeinfoService.CheckDown(ids);
        return ResponseResult.Sucess("审核删除");
    }

    @GetMapping("/treedetail/{id}")
    public ResponseResult getTreeById(@PathVariable("id")Long id){
        Treeinfo treeinfo = treeinfoService.findTreeByid(id);
        return ResponseResult.Sucess(treeinfo);
    }

    @GetMapping("/group/{number}")
    public ResponseResult getAreaByNum(@PathVariable("number")String number){
        List<String> pointList = treeinfoService.getAreaByNum(number);
        return ResponseResult.Sucess(pointList);
    }

    @GetMapping("/group/num")
    public ResponseResult getAllGroupNum(){
        List<String> groupNum = treeinfoService.getAllGroupNum();
        return ResponseResult.Sucess(groupNum);
    }


}
