package top.ssy.share.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.ssy.share.admin.common.model.Query;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 标签查询
 * @create : 2024-03-01 15:58
 **/

@Data
@Schema(description = "标签查询")
public class TagQuery extends Query {
    @Schema(name = "标题")
    private String title;
    @Schema(name = "isHot", description = "是否热门")
    private Integer isHot;
}
