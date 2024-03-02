package top.ssy.share.admin.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.ssy.share.admin.converter.GenderConverter;
import top.ssy.share.admin.converter.UserEnabledConverter;

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
    @ExcelIgnore
    @Schema(name = "pk_id", description = "用户id")
    private Integer pkId;
    @ExcelProperty("昵称")
    @Schema(name = "nickname", description = "昵称")
    private String nickname;
    @ExcelProperty("手机号")
    @Schema(name = "phone", description = "手机号")
    private String phone;
    @ExcelProperty("微信openid")
    @Schema(name = "wxOpenId", description = "微信openid")
    private String wxOpenId;
    @ExcelProperty("头像")
    @Schema(name = "avatar", description = "头像")
    private String avatar;
    // TODO excel导出性别转换
    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    @Schema(name = "gender", description = "性别")
    private Integer gender;
    @ExcelProperty("生日")
    @Schema(name = "birthday", description = "生日")
    private String birthday;
    @ExcelProperty("积分")
    @Schema(name = "bonus", description = "积分")
    private Integer bonus;
    @ExcelProperty("备注")
    @Schema(name = "remark", description = "备注")
    private String remark;
    @ExcelProperty(value = "账户状态", converter = UserEnabledConverter.class)
    @Schema(description = "账户状态")
    private Integer enabled;
    @ExcelProperty("创建时间")
    @Schema(name = "createTime", description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
