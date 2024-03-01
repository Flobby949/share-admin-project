package top.ssy.share.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 分类返回vo
 * @create : 2024-03-01 14:51
 **/

@Data
@Schema(name = "CategoryVO", description = "分类返回vo")
public class CategoryVO {
    @Schema(name = "pkId", description = "主键")
    private Integer pkId;
    @Schema(name = "title", description = "标题")
    private String title;
    @Schema(name = "type", description = "类型")
    private Integer type;
    @Schema(name = "description", description = "描述")
    private String description;
    @Schema(name = "createTime", description = "创建时间")
    private LocalDateTime createTime;
}
