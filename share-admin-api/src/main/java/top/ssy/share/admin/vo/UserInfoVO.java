package top.ssy.share.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 用户信息返回vo
 * @create : 2024-03-01 13:10
 **/

@Data
@Schema(name = "UserInfoVO", description = "用户信息返回vo")
public class UserInfoVO {
    @Schema(name = "pk_id", description = "用户id")
    private Integer pkId;
    @Schema(name = "phone", description = "手机号")
    private String phone;
    @Schema(name = "wxOpenId", description = "微信openid")
    private String wxOpenId;
    @Schema(name = "avatar", description = "头像")
    private String avatar;
    @Schema(name = "nickname", description = "昵称")
    private String nickname;
    @Schema(name = "gender", description = "性别")
    private Integer gender;
    @Schema(name = "birthday", description = "生日")
    private String birthday;
    @Schema(name = "bonus", description = "积分")
    private Integer bonus;
    @Schema(name = "remark", description = "备注")
    private String remark;
    @Schema(name = "createTime", description = "创建时间")
    private LocalDateTime createTime;
}
