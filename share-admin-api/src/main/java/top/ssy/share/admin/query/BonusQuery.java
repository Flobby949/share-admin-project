package top.ssy.share.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.ssy.share.admin.common.model.Query;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 积分日志查询
 * @create : 2024-03-02 14:00
 **/

@Data
@Schema(name = "BonusQuery", description = "积分日志查询")
public class BonusQuery extends Query {

    @Schema(name = "userId", description = "用户id")
    private Integer userId;
}
