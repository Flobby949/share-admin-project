package top.ssy.share.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
@Schema(description = "字典管理")
public class DictRequestDTO {
    @Schema(description = "主键")
    private Integer pkId;
    @Schema(description = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    private String title;
    @Schema(description = "字典编码")
    @NotBlank(message = "字典编码不能为空")
    private String number;
    @Schema(description = "字典描述")
    private String description;

}