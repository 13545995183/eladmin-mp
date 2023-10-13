package me.zhengjie.modules.system.domain.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultEntity<T> {

    private Integer code;
    private String msg;
    private T data;
    private Map map=new HashMap();

    public static <T> ResultEntity<T> success(T object,String msg){
        ResultEntity<T> resultEntity=new ResultEntity<>();
        resultEntity.data=object;
        resultEntity.code=200;
        resultEntity.msg=msg;
        return resultEntity;
    }
    public static <T> ResultEntity<T> success(String msg){
        ResultEntity<T> resultEntity=new ResultEntity<>();
        resultEntity.code=200;
        resultEntity.msg=msg;
        return resultEntity;
    }
    public static <T> ResultEntity<T> success(T object){
        ResultEntity<T> resultEntity=new ResultEntity<>();
        resultEntity.data=object;
        resultEntity.code=200;
        resultEntity.msg="成功";
        return resultEntity;
    }
    public static <T> ResultEntity<T> success(){
        ResultEntity<T> resultEntity=new ResultEntity<>();
        resultEntity.data=null;
        resultEntity.code=200;
        resultEntity.msg="成功";
        return resultEntity;
    }
    public static <T> ResultEntity<T> error(T object){
        ResultEntity<T> resultEntity=new ResultEntity<>();
        resultEntity.data=object;
        resultEntity.code=400;
        resultEntity.msg="失败";
        return resultEntity;
    }
    public static <T> ResultEntity<T> error(T object,String msg){
        ResultEntity<T> resultEntity=new ResultEntity<>();
        resultEntity.data=object;
        resultEntity.code=400;
        resultEntity.msg=msg;
        return resultEntity;
    }
    public static <T> ResultEntity<T> error(String msg){
        ResultEntity<T> resultEntity=new ResultEntity<>();
        resultEntity.code=400;
        resultEntity.msg=msg;
        resultEntity.data=null;
        return resultEntity;
    }
    public static <T> ResultEntity<T> error(){
        ResultEntity<T> resultEntity=new ResultEntity<>();
        resultEntity.code=400;
        resultEntity.msg="失败";
        resultEntity.data=null;
        return resultEntity;
    }
}
