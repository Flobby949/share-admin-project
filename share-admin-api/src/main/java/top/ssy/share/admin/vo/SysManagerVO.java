package top.ssy.share.admin.vo;

import top.ssy.share.admin.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ycshang
 */
@Data
@Schema(description = "管理员")
public class SysManagerVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "pk_id")
    private Integer pkId;

    @Schema(description = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "角色id")
    private Integer roleId;

    @Schema(description = "状态 0：停用    1：正常", required = true)
    @Range(min = 0, max = 1, message = "用户状态不正确")
    private Integer status;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

}