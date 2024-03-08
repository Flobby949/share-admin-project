package top.ssy.share.admin.enums;

import lombok.Getter;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 积分操作枚举
 * @create : 2024-03-02 13:54
 **/

@Getter
public enum BonusActionEnum {

    RESOURCE_AUDIT_PASS(10, "resourceAuditPass", "资源审核通过"),

    RESOURCE_EXCHANGE(0, "resourceExchange", "资源兑换"),

    DAILY_SIGN(5, "dailySign", "每日签到"),

    RESOURCE_BE_EXCHANGED(5, "resourceBeExchanged", "资源被兑换"),
    ;

    private final Integer bonus;
    private final String type;
    private final String desc;

    BonusActionEnum(Integer bonus, String type, String desc) {
        this.bonus = bonus;
        this.type = type;
        this.desc = desc;
    }
}
