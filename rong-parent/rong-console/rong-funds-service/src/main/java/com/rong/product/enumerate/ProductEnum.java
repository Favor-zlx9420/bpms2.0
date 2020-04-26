package com.rong.product.enumerate;

/**
 * @Package: com.rong.product.enumerate
 * @Author: LQW
 * @Date: 2020/4/22
 * @Description:产品枚举
 */
public enum ProductEnum {

    /**
     * 公募
     */
    publicPlacement(1, "公募型"),
    /**
     * 私募
     */
    privatePlacement(2, "私募型");


    private Integer code;

    private String promptInformation;


    ProductEnum() {
    }

    ProductEnum(Integer code, String promptInformation) {
        this.code = code;
        this.promptInformation = promptInformation;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getPromptInformation() {
        return promptInformation;
    }

    public void setPromptInformation(String promptInformation) {
        this.promptInformation = promptInformation;
    }

    public static ProductEnum valueOf(Integer code) {
        for (ProductEnum enumValue : ProductEnum.values()) {
            if (code.equals(enumValue.code)) {
                return enumValue;
            }
        }
        return null;
    }
}
