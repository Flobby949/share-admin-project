package top.ssy.share.admin.query;


import top.ssy.share.admin.common.model.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author ycshang
 */
@Data
@Schema(description = "字典查询")
public class DictQuery extends Query {
    @Schema(description = "字典名称")
    private String title;
}
