package top.ssy.share.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.ssy.share.admin.entity.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-08 09:28
 **/

@Data
@Schema(name = "DashboardDataVO", description = "首页数据vo")
public class DashboardDataVO {

    @Schema(name = "userCount", description = "标签列表")
    private List<Tag> tagList;
    @Schema(name = "allUserCount", description = "用户总数")
    private Long allUserCount;
    @Schema(name = "todayUserCheckCount", description = "今日签到数")
    private Long todayUserCheckCount;
    @Schema(name = "resourceCount", description = "资源总数")
    private Long resourceCount;
    @Schema(name = "resourceUnAuditCount", description = "未审批资源总数")
    private Long resourceUnAuditCount;
    @Schema(name = "resourceCount", description = "资源map")
    private Map<String, Long> resourceCountMap;
}
