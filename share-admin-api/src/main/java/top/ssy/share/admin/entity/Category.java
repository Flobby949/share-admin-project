package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 分类
 * @create : 2024-03-01 14:47
 **/

@Getter
@Setter
@ToString
@TableName("t_category")
public class Category {

    @TableId(value = "pk_id", type= IdType.AUTO)
    private Integer pkId;
    private String title;
    /**
     * 0-网盘，1-资源
     */
    private Integer type;
    private String description;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
