package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 标签表
 * @create : 2024-03-01 15:52
 **/

@Getter
@Setter
@ToString
@TableName("t_tag")
public class Tag {
    @TableId(type = IdType.AUTO, value = "pk_id")
    private Integer pkId;
    private String title;
    private String description;
    private Integer isHot;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
