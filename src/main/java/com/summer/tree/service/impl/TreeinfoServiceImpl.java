package com.summer.tree.service.impl;/*
@Author qqz
@create 2020-07-11  18:07
*/

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.summer.tree.dao.PreparerMapper;
import com.summer.tree.dao.TreeGroupMapper;
import com.summer.tree.dao.TreeinfoMapper;
import com.summer.tree.dto.FilterDto;
import com.summer.tree.pojo.Preparer;
import com.summer.tree.pojo.Treeinfo;
import com.summer.tree.service.TreeinfoService;
import com.summer.tree.util.ZXingUtil;
import com.summer.tree.vo.TreeinfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TreeinfoServiceImpl implements TreeinfoService {
    @Autowired
    TreeinfoMapper treeinfoMapper;
    @Autowired
    PreparerMapper preparerMapper;
    @Autowired
    private TreeGroupMapper treeGroupMapper;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @Override
    public List<Treeinfo> findTrees() {
        return treeinfoMapper.SelectAllTree();
    }

    @Override
    public Treeinfo findTreeByid(Long id) {
        return treeinfoMapper.SelectByTreeid(id);
    }

    @Override
    public List<Treeinfo> findTreesByMapinfo(String longitude, String latitude) {
        return treeinfoMapper.SelectTreesByLongitudeAndLatitude(longitude, latitude);
    }

    @Override
    @Transactional
    public void addTree(Treeinfo treeinfo, Preparer preparer) {
        treeinfoMapper.insert(treeinfo);
        preparerMapper.insert(preparer);
        preparerMapper.insertPreparer_Tree(preparer.getId(), treeinfo.getId());

    }

    @Override
    public Integer findNum() {
        return treeinfoMapper.SelectCount();
    }


    @Override
    public List<Treeinfo> filter(FilterDto filterDto) {
        QueryWrapper<Treeinfo> wrapper = new QueryWrapper<>();
        String type = filterDto.getType();
        if (StringUtils.isNotEmpty(type)) {
            wrapper.lambda().eq(Treeinfo::getType, Integer.parseInt(type));
        }
        String growthCondition = filterDto.getGrowthCondition();
        if (StringUtils.isNotBlank(growthCondition)) {
            wrapper.lambda().eq(Treeinfo::getGrowthCondition, growthCondition);
        }
        String bust = filterDto.getBust();
        if (StringUtils.isNotBlank(bust)) {
            if (bust.contains("-")) {
                //获取树胸径范围
                String[] split = bust.split("-");
                wrapper.lambda().ge(Treeinfo::getBust, split[0]);
                wrapper.lambda().lt(Treeinfo::getBust, split[1]);
            } else {
                wrapper.lambda().ge(Treeinfo::getBust, bust);
            }
        }
        String distribution = filterDto.getDistribution();
        if (StringUtils.isNotBlank(distribution)) {
            wrapper.lambda().eq(Treeinfo::getDistribution, distribution);
        }
        String month = filterDto.getMonth();
        if (StringUtils.isNotBlank(month)){
            int offset = Integer.parseInt("-" + month);
            DateTime dateTime = DateUtil.offset(new Date(), DateField.MONTH,offset);
            String dateString = sdf.format(dateTime);
            wrapper.lambda().ge(Treeinfo::getCreateTime,dateString);
        }
        wrapper.lambda().eq(Treeinfo::getStatus, 2);
        List<Treeinfo> treeinfos = treeinfoMapper.selectList(wrapper);
        return treeinfos;
    }

    @Override
    public List<TreeinfoVo> getCheckTree() {
        return treeinfoMapper.SelectAllCheckTree();
    }


    /**
     * 审核通过
     * @param ids
     */
    @Override
    @Transactional
    public void CheckPass(Long[] ids) {
        for (Long id : ids) {
            String name = UUID.randomUUID().toString().substring(0, 6) + ".jpg";
            String content = "http://czhhzc.cn:9000/treedetail/"+id;
            ZXingUtil.generateQRImage(name, content, "jpg");
            File file = new File("/usr/local/javaresource/" + name);
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), "jpg", null);
            String url = storePath.getFullPath();
            treeinfoMapper.CheckPass(id,url);
            //查看有无oldId 该树是新增还是修改
            Long oldId = treeinfoMapper.CheckStatus(id);
            if (oldId != null) {
                treeinfoMapper.deleteById(oldId);
            }
        }
    }

    @Override
    public void CheckDown(Long[] ids) {
        treeinfoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<String> getAreaByNum(String number) {

        String point = treeGroupMapper.SelectPointsByNum(number);
        String[] points = point.split("&");
        List<String> pointList = Arrays.asList(points);
        pointList.forEach(System.out::println);
        return pointList;
    }

    public List<String> getAllGroupNum() {
        return treeGroupMapper.SelectAllNum();
    }

}
