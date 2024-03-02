package top.ssy.share.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 用户修改dto
 * @create : 2024-03-01 13:47
 **/

@Data
@Schema(description = "用户修改dto")
public class UserEditDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3975140673973740199L;

    @NotNull
    @Schema(description = "用户id")
    private Integer pkId;
    @Schema(description = "用户名")
    private String nickname;
    @Length(min = 11, max = 11, message = "手机号长度为11位")
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "性别")
    private Integer gender;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "生日")
    private String birthday;
    @Schema(description = "账户状态")
    private Integer enabled;
}
