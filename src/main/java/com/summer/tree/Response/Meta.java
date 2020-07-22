package com.summer.tree.Response;/*
@Author qqz
@create 2020-02-11  4:03
*/

public class Meta {
    private String msg;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Meta(String msg , int status) {
        this.msg = msg;
        this.status = status;
    }
}
