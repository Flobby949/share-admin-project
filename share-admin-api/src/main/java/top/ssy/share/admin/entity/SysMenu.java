package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import top.ssy.share.admin.serializer.MenuSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu {

    /**
     * id
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;

    /**
     * 上级ID，一级菜单为0
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜单标题
     */
    @TableField("title")
    private String title;
    /**
     * 菜单路径
     */
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 菜单类型 menu: 菜单 button: 按钮
     */
    @TableField("type")
    private String type;

    /**
     * 打开类型 tab: 选项卡 url: 外链
     */
    @TableField("open_type")
    private String openType;

    /**
     * 外链地址
     */
    @TableField("url")
    private String url;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)
     */
    @TableField("auth")
    private String auth;

    /**
     * 是否缓存 0:false 1:true
     */
    @TableField("keep_alive")
    @JsonSerialize(using = MenuSerializer.class)
    private Integer keepAlive;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    @Schema(description = "是否隐藏 0:true 1:false")
    @JsonSerialize(using = MenuSerializer.class)
    private Integer hide;

    /**
     * 删除标识  0：正常   1：已删除
     */
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
