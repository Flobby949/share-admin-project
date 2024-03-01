package top.ssy.share.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.ssy.share.admin.common.model.Query;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 公告查询
 * @create : 2024-03-01 14:28
 **/

@Data
@Schema(name = "NoticeQuery", description = "公告查询")
public class NoticeQuery extends Query {
    @Schema(name = "标题")
    private String title;
    @Schema(name = "是否置顶")
    private Integer isTop;
    @Schema(name = "是否轮播")
    private Integer isSwiper;
}
