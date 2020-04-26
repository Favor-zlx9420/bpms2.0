package com.rong.common.module;

import com.rong.common.consts.CommonEnumContainer;
import com.rong.common.util.CommonUtil;

import java.beans.Transient;
import java.io.Serializable;

/**
 * creator : whh-lether
 * date    : 2019/6/20 9:12
 * desc    :
 **/
public final class Result<T> implements Serializable{
    private static final long serialVersionUID = 1L;
    private boolean succeed;
    private String code;
    private String message;
    private T content;

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public T getContent() {
        return content;
    }

    private Result<T> setSucceed(boolean succeed) {
        this.succeed = succeed;
        return this;
    }

    private Result<T> setCode(String code) {
        this.code = code;
        return this;
    }

    private Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    private Result<T> setContent(T content) {
        this.content = content;
        return this;
    }

    private Result() {}
    @Transient
    public final boolean isSucceed(){
        return succeed;
    }
    @Transient
    public final boolean sad(){
        return !succeed;
    }

    public static <T> Result<T> success(){
        return success(null);
    }
    public static <T> Result<T> success(T content){
        return success(content,CommonEnumContainer.ResultStatus.NORMAL.getDesc());
    }
    public  static <T> Result<T> success(T content,String message){
        Result<T> result = new Result<>();
        return result
                .setSucceed(true)
                .setCode(CommonEnumContainer.ResultStatus.NORMAL.getValue())
                .setMessage(message)
                .setContent(content);
    }

    /**
     * something is wrong
     * @param status
     * @return
     */
    public static Result miss(CommonEnumContainer.ResultStatus status){
        return miss(status,status.getDesc());
    }
    public static <T>  Result<T> miss(CommonEnumContainer.ResultStatus status,String message){
        return miss(status,message,null);
    }
    public static <T> Result<T> miss(CommonEnumContainer.ResultStatus status,String message,T content){
        Result<T> result = new Result<>();
        return result
                .setSucceed(false)
                .setCode(status.getValue())
                .setMessage(message)
                .setContent(content);
    }
}
