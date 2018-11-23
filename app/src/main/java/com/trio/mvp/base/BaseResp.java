package com.trio.mvp.base;

/**
 * Created by lixia on 2018/11/22.
 */

public class BaseResp<T> {

    public int ret;
    public String msg;
    public T data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
