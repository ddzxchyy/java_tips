package cn.behappy.javatips.common.core.util;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

import static cn.behappy.javatips.common.core.consts.ResponseConstants.*;


/**
 * 响应信息主体
 * 返回值 data 必须是 json 格式
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public static <T> R<T> ok() {
        //        apiResult.setData(EmptyJSON.getInstance());
        return restResult(null, SUCCESS, SUCCESS_MESSAGE);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, SUCCESS_MESSAGE);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> error() {
        //        apiResult.setData(EmptyJSON.getInstance());
        return restResult(null, FAILED, FAILED_MESSAGE);
    }

    public static <T> R<T> error(String msg) {
        R<T> apiResult = error();
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> R<T> error(T data) {
        return restResult(data, FAILED, FAILED_MESSAGE);
    }

    public static <T> R<T> error(T data, String msg) {
        return restResult(data, FAILED, msg);
    }

    public static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}