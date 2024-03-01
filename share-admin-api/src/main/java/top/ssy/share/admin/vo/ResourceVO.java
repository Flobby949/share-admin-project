package top.ssy.share.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 资源返回vo
 * @create : 2024-03-01 15:22
 **/

@Data
@Schema(name = "ResourceVO", description = "资源返回vo")
public class ResourceVO {
    @Schema(name = "pkId", description = "主键")
    private Integer pkId;
    @Schema(name = "title", description = "标题")
    private String title;
    @Schema(name = "authorName", description = "作者id")
    private Integer author;
    @Schema(name = "authorName", description = "作者")
    private String authorName;
    @Schema(name = "diskType", description = "网盘分类")
    private String diskType;
    @Schema(name = "resType", description = "资源分类列表")
    private List<Integer> resType;
    @Schema(name = "tags", description = "标签列表")
    private List<Integer> tags;
    @Schema(name = "resTypeList", description = "资源分类列表")
    private List<String> resTypeList;
    @Schema(name = "tagList", description = "标签列表")
    private List<String> tagList;
    @Schema(name = "downloadUrl", description = "下载资源")
    private String downloadUrl;
    @Schema(name = "detail", description = "详情")
    private String detail;
    @Schema(name = "price", description = "价格")
    private Integer price;
    @Schema(name = "likeNum", description = "点赞数")
    private Integer likeNum;
    @Schema(name = "isTop", description = "是否置顶")
    private Integer isTop;
    @Schema(name = "status", description = "审核状态")
    private Integer status;
    @Schema(name = "remark", description = "审核描述")
    private Integer remark;
    @Schema(name = "createTime", description = "创建时间")
    private LocalDateTime createTime;
}
