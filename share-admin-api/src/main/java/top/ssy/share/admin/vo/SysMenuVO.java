package top.ssy.share.admin.vo;

import top.ssy.share.admin.common.model.TreeNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * 菜单管理
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单")
public class SysMenuVO extends TreeNode<SysMenuVO> {

    @Schema(description = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    @Schema(description = "菜单标题")
    @NotBlank(message = "菜单标题不能为空")
    private String title;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "组件路径")
    private String component;

    @Schema(description = "菜单类型 menu: 菜单 button: 按钮")
    private String type;

    @Schema(description = "打开类型 tab: 选项卡 url: 外链")
    private String openType;

    @Schema(description = "外链地址")
    private String url;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)")
    private String auth;

    @Schema(description = "是否缓存 0:true 1:false")
    private Integer keepAlive;

    @Schema(description = "排序")
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort;

    @Schema(description = "父级菜单名称")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String parentName;

}