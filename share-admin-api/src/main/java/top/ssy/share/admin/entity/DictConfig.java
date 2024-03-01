package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ycshang
 * @since 2023-11-13
 */
@Getter
@Setter
@TableName("sys_dict_config")
@Schema(description = "DictConfig对象")
public class DictConfig {

    @Schema(description = "主键唯一字段")
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;

    @Schema(description = "字典编号")
    @TableField("dict_number")
    private String dictNumber;

    @Schema(description = "名称")
    @TableField("title")
    private String title;

    @Schema(description = "数据值")
    @TableField("value")
    private String value;

    @Schema(description = "0:未删除  1:已删除")
    @TableField("delete_flag")
    @TableLogic
    @JsonIgnore
    private Integer deleteFlag;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
