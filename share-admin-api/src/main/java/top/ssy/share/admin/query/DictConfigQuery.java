package top.ssy.share.admin.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
@Schema(description = "字典配置查询")
public class DictConfigQuery {
    @Schema(description = "字典名称")
    private String title;

    @Schema(description = "字典编号")
    @NotBlank(message = "字典编号禁止为空")
    private String number;
}
