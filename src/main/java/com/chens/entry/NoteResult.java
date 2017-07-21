package com.chens.entry;

import java.io.Serializable;

/**
 * 登入信息处理结果
 *
 */
public class NoteResult implements Serializable{


    public NoteResult() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "NoteResult{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", obj=" + obj +
                '}';
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private  String  msg;
    private  Integer status;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    private  Object  obj;

}
