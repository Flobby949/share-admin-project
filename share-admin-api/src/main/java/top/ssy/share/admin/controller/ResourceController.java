package top.ssy.share.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.dto.ResourceAuditDTO;
import top.ssy.share.admin.query.ResourceQuery;
import top.ssy.share.admin.service.ResourceService;
import top.ssy.share.admin.vo.ResourceVO;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 资源接口
 * @create : 2024-03-02 12:34
 **/

@RestController
@AllArgsConstructor
@RequestMapping("/resource")
@Tag(name = "资源接口", description = "资源接口")
public class ResourceController {
    private final ResourceService resourceService;

    @PostMapping("/page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:resource:view')")
    public Result<PageResult<ResourceVO>> page(@RequestBody @Valid ResourceQuery query) {
        return Result.ok(resourceService.page(query));
    }

    @PostMapping("/audit")
    @Operation(summary = "审核")
    @PreAuthorize("hasAuthority('sys:resource:audit')")
    public Result<Void> audit(@RequestBody @Valid ResourceAuditDTO dto) {
        resourceService.audit(dto);
        return Result.ok();
    }
}
