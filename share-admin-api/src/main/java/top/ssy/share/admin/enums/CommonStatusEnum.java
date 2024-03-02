package top.ssy.share.admin.enums;

import lombok.Getter;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 基础状态枚举
 * @create : 2024-03-02 14:42
 **/

@Getter
public enum CommonStatusEnum {

    NO(0, "否"),
    YES(1, "是"),
    ;

    private final Integer status;
    private final String desc;

    CommonStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
