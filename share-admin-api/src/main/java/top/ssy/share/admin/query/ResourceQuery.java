package top.ssy.share.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.ssy.share.admin.common.model.Query;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 资源查询
 * @create : 2024-03-01 15:26
 **/

@Data
@Schema(name = "resourceQuery", description = "资源查询")
public class ResourceQuery extends Query {
    @Schema(name = "title", description = "标题")
    private String title;
    @Schema(name = "author", description = "作者")
    private Integer author;
    @Schema(name = "isTop", description = "是否置顶")
    private Integer isTop;
    @Schema(name = "status", description = "状态")
    private Integer status;
}
