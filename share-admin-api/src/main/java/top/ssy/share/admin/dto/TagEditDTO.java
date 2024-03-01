package top.ssy.share.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 标签新增修改dto
 * @create : 2024-03-01 15:56
 **/

@Data
@Schema(description = "标签新增修改dto")
public class TagEditDTO {
    @Schema(name = "pkId", description = "主键")
    private Integer pkId;
    @Schema(name = "title", description = "标题")
    private String title;
    @Schema(name = "description", description = "描述")
    private String description;
    @Schema(name = "isHot", description = "是否热门")
    private Integer isHot;
    @Schema(name = "deleteFlag", description = "删除标识")
    private Integer deleteFlag;
}
