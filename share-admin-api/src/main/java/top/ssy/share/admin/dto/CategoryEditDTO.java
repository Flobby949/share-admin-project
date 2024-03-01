package top.ssy.share.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 分类
 * @create : 2024-03-01 14:50
 **/

@Data
@Schema(name = "CategoryEditDTO", description = "分类编辑DTO")
public class CategoryEditDTO {
        @Schema(name = "pkId", description = "主键")
        private Integer pkId;
        @Schema(name = "title", description = "标题")
        private String title;
        @Schema(name = "type", description = "类型")
        private Integer type;
        @Schema(name = "description", description = "描述")
        private String description;
        @Schema(name = "deleteFlag", description = "删除标识")
        private Integer deleteFlag;
}
