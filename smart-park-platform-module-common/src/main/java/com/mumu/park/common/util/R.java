package com.mumu.park.common.util;

import com.mumu.park.common.constants.CommonConstants;
import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {
    //所有Controller都返回 R<T>,前端统一解析格式

    private int code;
    private String msg;
    private T data;

    private R(){}

    public static <T> R<T> success(T data){
        R<T> r = new R<>();
        r.code = CommonConstants.SUCCESS;
        r.msg = "success";
        r.data = data;
        return r;
    }

    public static <T> R<T> success(){
        return success(null);
    }

    public static <T> R<T> error(String msg){
        R<T> r = new R<>();
        r.code = CommonConstants.FAIL;
        r.msg = msg;
        return r;
    }

    public static <T> R<T> error(int code, String msg){
        R<T> r = new R<>();
        r.code = code;
        r.msg = msg;
        return r;
    }
}
