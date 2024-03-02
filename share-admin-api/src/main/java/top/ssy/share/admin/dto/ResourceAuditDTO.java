package top.ssy.share.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 资源审核dto
 * @create : 2024-03-01 15:34
 **/

@Data
@Schema(name = "resourceAuditDTO", description = "资源审核dto")
public class ResourceAuditDTO {
    @NotNull
    @Schema(name = "pkId", description = "主键")
    private Integer pkId;

    @Schema(name = "status", description = "状态")
    private Integer status;

    @Schema(name = "remark", description = "审核描述")
    private String remark;

}
