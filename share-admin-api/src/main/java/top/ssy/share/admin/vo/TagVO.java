package top.ssy.share.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 15:58
 **/

@Data
@Schema(description = "标签返回vo")
public class TagVO {
    @Schema(name = "pkId", description = "主键")
    private Integer pkId;
    @Schema(name = "title", description = "标题")
    private String title;
    @Schema(name = "description", description = "描述")
    private String description;
    @Schema(name = "isHot", description = "是否热门")
    private Integer isHot;
    @Schema(name = "createTime", description = "创建时间")
    private LocalDateTime createTime;
}
