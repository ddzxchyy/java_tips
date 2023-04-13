package cn.behappy.javatips.common.core.consts;

import lombok.Getter;

@Getter
public enum ResponseErrorEnum {

    UNKNOWN_ERROR(500_000),

    NULL_POINT(500_001),

    SQL_ERROR(500_002),

    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(500_003),

    AUTHORIZATION_ERROR(500_401);


    private final Integer code;

    ResponseErrorEnum(Integer code) {
        this.code = code;
    }
}
