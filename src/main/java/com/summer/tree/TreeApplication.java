package com.summer.tree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.summer.tree.dao")
public class TreeApplication {
    public static void main(String[] args) {
        SpringApplication.run ( TreeApplication.class , args );
    }

}
