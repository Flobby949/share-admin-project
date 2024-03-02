package top.ssy.share.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 用户状态修改dto
 * @create : 2024-03-02 14:55
 **/

@Data
@Schema(description = "用户状态修改dto")
public class UserEnabledDTO {
    @Schema(description = "用户id")
    private Integer pkId;
    @Schema(description = "账户状态")
    private Integer enabled;
}
