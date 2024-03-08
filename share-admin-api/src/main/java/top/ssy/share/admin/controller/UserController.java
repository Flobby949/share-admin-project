package top.ssy.share.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.dto.UserEditDTO;
import top.ssy.share.admin.entity.User;
import top.ssy.share.admin.query.UserQuery;
import top.ssy.share.admin.service.BonusLogService;
import top.ssy.share.admin.service.UserService;
import top.ssy.share.admin.vo.BonusLogVO;
import top.ssy.share.admin.vo.UserInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 用户管理接口
 * @create : 2024-03-01 13:09
 **/

@RestController
@AllArgsConstructor
@Tag(name = "用户管理接口")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BonusLogService bonusLogService;

    @PostMapping("/page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:user:view')")
    public Result<PageResult<UserInfoVO>> page(@RequestBody @Valid UserQuery query) {
        return Result.ok(userService.page(query));
    }

    @PostMapping("edit")
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public Result<String> update(@RequestBody @Valid UserEditDTO dto) {

        userService.update(dto);

        return Result.ok();
    }

    @GetMapping("list")
    @Operation(summary = "全部用户列表")
    public Result<List<User>> list() {
        return Result.ok(userService.list());
    }


    @GetMapping("bonus/list")
    @Operation(summary = "积分列表")
    @PreAuthorize("hasAuthority('sys:user:bonus')")
    public Result<Map<String, List<BonusLogVO>>> bonusList(@RequestParam Integer userId) {
        return Result.ok(bonusLogService.userBonusResult(userId));
    }

    @PostMapping("enabled")
    @Operation(summary = "账户状态修改")
    @PreAuthorize("hasAuthority('sys:user:ice')")
    public Result<String> enabled(@RequestParam Integer userId) {
        userService.enabled(userId);
        return Result.ok();
    }

    @PostMapping("export")
    @Operation(summary = "导出")
    @PreAuthorize("hasAuthority('sys:user:export')")
    public void export(@RequestBody UserQuery query, HttpServletResponse response) {
        userService.export(query, response);
    }

}
