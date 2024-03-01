package top.ssy.share.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.ssy.share.admin.common.model.Query;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 14:56
 **/

@Data
@Schema(name = "CategoryQuery", description = "分类查询")
public class CategoryQuery extends Query {
    @Schema(name = "标题")
    private String title;
}
