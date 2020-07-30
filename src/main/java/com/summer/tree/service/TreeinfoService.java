package com.summer.tree.service;/*
@Author qqz
@create 2020-07-11  17:59
*/

import com.summer.tree.dto.FilterDto;
import com.summer.tree.pojo.Preparer;
import com.summer.tree.pojo.Treeinfo;
import com.summer.tree.vo.TreeinfoVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public interface TreeinfoService {
    List<Treeinfo> findTrees();
    Treeinfo findTreeByid(int treeid);
    List<Treeinfo> findTreesByMapinfo(String longitude,String latitude);
    void addTree(Treeinfo treeinfo, Preparer preparer);
    Integer findNum();


    List<Treeinfo> filter(FilterDto filterDto);

    List<TreeinfoVo> getCheckTree();

    void CheckPass(Long[] ids);

    void CheckDown(Long[] ids);
}

