package top.ssy.share.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-02 14:02
 **/

@Data
@Schema(name = "BonusLogVO", description = "积分日志返回vo")
public class BonusLogVO {
    @Schema(name = "pkId", description = "主键")
    private Integer pkId;
    @Schema(name = "userId", description = "用户id")
    private Integer userId;
    @Schema(name = "bonus", description = "积分")
    private Integer bonus;
    @Schema(name = "content", description = "描述")
    private String content;
    @Schema(name = "createTime", description = "创建时间")
    private String createTime;
}
