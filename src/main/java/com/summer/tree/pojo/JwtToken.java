package com.summer.tree.pojo;/*
@Author qqz
@create 2020-07-20  19:05
*/

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
    private String jwt;
    public JwtToken(String jwt){
        this.jwt = jwt;
    }
    @Override
    public Object getPrincipal() {
        return jwt;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }
}
