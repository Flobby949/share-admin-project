package top.ssy.share.admin.enums;

import lombok.Getter;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 删除标识枚举
 * @create : 2024-03-02 12:22
 **/

@Getter
public enum DeleteFlagEnum {
    /**
     * 未删除
     */
    NOT_DELETE(0, "未删除"),
    /**
     * 已删除
     */
    DELETED(1, "已删除");

    private final Integer code;
    private final String desc;

    DeleteFlagEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
