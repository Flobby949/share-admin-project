package top.ssy.share.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单筛选项
 *
 * @Author ycshang
 * @Date 2023-06-02 14:44
 */
@Data
@Schema(description = "菜单筛选项")
public class SysMenuQuery {
    @Schema(description = "菜单名称")
    private String title;
}
