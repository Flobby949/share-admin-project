package top.ssy.share.admin.query;

import top.ssy.share.admin.common.model.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ycshang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "管理员查询")
public class SysManagerQuery extends Query {
    @Schema(description = "用户名")
    private String username;

}
