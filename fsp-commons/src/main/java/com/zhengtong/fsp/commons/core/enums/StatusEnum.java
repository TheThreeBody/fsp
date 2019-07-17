package com.zhengtong.fsp.commons.core.enums;

/**
 * Created by songhuiping on 2017/7/18.
 */
public enum StatusEnum {
    FAIL("0","失败"),
    SUCCESS("1","成功"),
    INITIALZATION("2","初始化")
    ;

    private String code;
    private String description;

    private StatusEnum(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
