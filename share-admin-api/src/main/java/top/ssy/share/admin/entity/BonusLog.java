package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 积分日志
 * @create : 2024-03-02 13:50
 **/

@Data
@TableName("t_bonus_log")
public class BonusLog {
    @TableId(type = IdType.AUTO)
    private Integer pkId;
    private Integer userId;
    /**
     * @see top.ssy.share.admin.enums.BonusActionEnum
     */
    private String content;
    private Integer bonus;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
