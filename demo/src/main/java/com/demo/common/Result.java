package com.demo.common;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private int code;
    private String msg;
    private Long total;
    private Object data;

    public static Result fall(){
        return result(400,"失败",0L,null);
    }
    public static Result suc(Object data,Long total){
        return result(200,"成功",total,data);
    }
    private static  Result result(int code,String msg,Long total,Object data){
        Result res = new Result();
        res.setData(data);
        res.setMsg(msg);
        res.setCode(code);
        res.setTotal(total);
        return res;
    }
}
