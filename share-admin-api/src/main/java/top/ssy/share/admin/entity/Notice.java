package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 公告表
 * @create : 2024-03-01 14:22
 **/

@Getter
@Setter
@ToString
@TableName("t_notice")
public class Notice {

    @TableId(value = "pk_id", type= IdType.AUTO)
    private Integer pkId;
    private String title;
    private String cover;
    private String content;
    private Integer adminId;
    private Integer isTop;
    private Integer isSwiper;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
