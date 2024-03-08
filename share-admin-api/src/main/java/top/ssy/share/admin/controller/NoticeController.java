package top.ssy.share.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.dto.NoticeEditDTO;
import top.ssy.share.admin.query.NoticeQuery;
import top.ssy.share.admin.security.cache.TokenStoreCache;
import top.ssy.share.admin.security.user.ManagerDetail;
import top.ssy.share.admin.service.NoticeService;
import top.ssy.share.admin.vo.NoticeVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 公告接口
 * @create : 2024-03-01 14:33
 **/


@RestController
@AllArgsConstructor
@Tag(name = "公告接口", description = "公告接口")
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final TokenStoreCache tokenStoreCache;

    @PostMapping("/page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:notice:view')")
    public Result<PageResult<NoticeVO>> page(@RequestBody @Valid NoticeQuery query) {
        return Result.ok(noticeService.page(query));
    }

    @PostMapping("saveAndEdit")
    @Operation(summary = "新增或修改")
    @PreAuthorize("hasAnyAuthority('sys:notice:add', 'sys:notice:edit')")
    public Result<String> saveAndEdit(@RequestBody @Valid NoticeEditDTO dto, @RequestHeader("Authorization") String token) {
        ManagerDetail user = tokenStoreCache.getUser(token);
        dto.setAdminId(user.getPkId());
        noticeService.saveAndEdit(dto);

        return Result.ok();
    }

    @PostMapping("/delete")
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:notice:remove')")
    public Result<String> delete(@RequestBody List<Integer> ids) {
        noticeService.delete(ids);
        return Result.ok();
    }
}
