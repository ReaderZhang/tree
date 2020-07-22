package com.summer.tree;

import com.summer.tree.dao.TreeinfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TreeApplicationTests {
    @Autowired
    private TreeinfoMapper treeinfoMapper;

    @Test
    void contextLoads() {
        System.out.println (treeinfoMapper.SelectByTreeid ( 1 ));
    }

}
