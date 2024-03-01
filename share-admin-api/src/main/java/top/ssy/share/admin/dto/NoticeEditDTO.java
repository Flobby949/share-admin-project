package top.ssy.share.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 14:37
 **/

@Data
@Schema(name = "NoticeEditDTO", description = "公告编辑DTO")
public class NoticeEditDTO {

    @Schema(name = "pkId", description = "主键")
    private Integer pkId;
    @Schema(name = "title", description = "标题")
    private String title;
    @Schema(name = "cover", description = "封面")
    private String cover;
    @Schema(name = "content", description = "内容")
    private String content;
    @Schema(name = "adminId", description = "管理员ID")
    private Integer adminId;
    @Schema(name = "isTop", description = "是否置顶")
    private Integer isTop;
    @Schema(name = "isSwiper", description = "是否轮播")
    private Integer isSwiper;
    @Schema(name = "deleteFlag", description = "删除标识")
    private Integer deleteFlag;
}
