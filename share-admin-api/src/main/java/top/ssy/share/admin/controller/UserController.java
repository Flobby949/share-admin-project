package top.ssy.share.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.dto.UserEditDTO;
import top.ssy.share.admin.query.UserQuery;
import top.ssy.share.admin.service.UserService;
import top.ssy.share.admin.vo.UserInfoVO;

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

    @PostMapping("/page")
    @Operation(summary = "分页")
    public Result<PageResult<UserInfoVO>> page(@RequestBody @Valid UserQuery query) {
        return Result.ok(userService.page(query));
    }

    @PostMapping("edit")
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid UserEditDTO dto) {

        userService.update(dto);

        return Result.ok();
    }

}
