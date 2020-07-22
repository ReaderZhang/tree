package com.summer.tree.config;/*
@Author qqz
@create 2020-02-11  0:19
*/

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MybatisConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer () {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase ( true );
            }
        };
    }
}
