package top.ssy.share.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 资源表
 * @create : 2024-03-01 15:15
 **/

@Getter
@Setter
@ToString
@TableName(value = "t_resource", autoResultMap = true)
public class Resource {

    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private String title;
    private Integer author;
    private Integer diskType;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> resType;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> tags;
    private String downloadUrl;
    private String detail;
    private Integer price;
    private Integer likeNum;
    /**
     * @see top.ssy.share.admin.enums.CommonStatusEnum
     */
    private Integer isTop;
    /**
     * @see top.ssy.share.admin.enums.ResourceStatusEnum
     */
    private Integer status;
    private String remark;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
