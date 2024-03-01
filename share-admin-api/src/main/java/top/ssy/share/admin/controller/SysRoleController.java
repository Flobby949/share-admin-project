package top.ssy.share.admin.controller;

import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.query.SysRoleQuery;
import top.ssy.share.admin.service.SysMenuService;
import top.ssy.share.admin.service.SysRoleService;
import top.ssy.share.admin.vo.SysMenuVO;
import top.ssy.share.admin.vo.SysRoleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色管理 前端控制器
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
@Tag(name = "角色管理")
@AllArgsConstructor
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    private SysRoleService sysRoleService;

    private SysMenuService sysMenuService;

    @PostMapping("page")
    @Operation(summary = "分页")
    public Result<PageResult<SysRoleVO>> page(@RequestBody @Valid SysRoleQuery query) {
        PageResult<SysRoleVO> page = sysRoleService.page(query);
        return Result.ok(page);
    }

    @PostMapping("list")
    @Operation(summary = "列表")
    public Result<List<SysRoleVO>> list() {
        List<SysRoleVO> list = sysRoleService.getList(new SysRoleQuery());
        return Result.ok(list);
    }

    @PostMapping("add")
    @Operation(summary = "保存")
    public Result<String> save(@RequestBody @Valid SysRoleVO vo) {
        sysRoleService.save(vo);
        return Result.ok();
    }

    @PostMapping("edit")
    @Operation(summary = "修改")
    public Result<String> update(@RequestBody @Valid SysRoleVO vo) {
        sysRoleService.update(vo);
        return Result.ok();
    }

    @PostMapping("remove")
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Integer> idList) {
        sysRoleService.delete(idList);
        return Result.ok();
    }

    @PostMapping("menu")
    @Operation(summary = "角色表单菜单列表")
    public Result<List<SysMenuVO>> getRoleFormMenuList() {
        List<SysMenuVO> list = sysMenuService.getRoleFormMenuList();
        return Result.ok(list);
    }

}
