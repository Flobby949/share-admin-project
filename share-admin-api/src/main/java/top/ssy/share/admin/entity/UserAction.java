package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 用户行为表
 * @create : 2024-03-05 12:55
 **/

@Data
@TableName("t_action")
public class UserAction {
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private Integer userId;
    private Integer resourceId;
    /**
     * @see top.ssy.share.admin.enums.UserActionEnum
     */
    private Integer type;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
