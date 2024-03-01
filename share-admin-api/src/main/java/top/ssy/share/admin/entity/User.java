package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 用户表
 * @create : 2024-03-01 13:01
 **/

@Getter
@Setter
@ToString
@TableName("t_user")
public class User {
    @TableId(value = "pk_id", type= IdType.AUTO)
    private Integer pkId;
    private String phone;
    private String wxOpenId;
    private String avatar;
    private String nickname;
    private Integer gender;
    private String birthday;
    private Integer bonus;
    private String remark;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
