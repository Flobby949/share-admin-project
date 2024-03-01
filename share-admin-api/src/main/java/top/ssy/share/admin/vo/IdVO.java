package top.ssy.share.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 当参数只有id时
 *
 * @Author ycshang
 * @Date 2023-06-02 14:27
 */
@Data
@Schema(description = "ID参数")
public class IdVO {

    @Schema(description = "id")
    private Integer id;
}
