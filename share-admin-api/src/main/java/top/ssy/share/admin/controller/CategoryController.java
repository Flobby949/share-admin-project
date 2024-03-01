package top.ssy.share.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.dto.CategoryEditDTO;
import top.ssy.share.admin.dto.NoticeEditDTO;
import top.ssy.share.admin.query.CategoryQuery;
import top.ssy.share.admin.query.NoticeQuery;
import top.ssy.share.admin.security.cache.TokenStoreCache;
import top.ssy.share.admin.security.user.ManagerDetail;
import top.ssy.share.admin.service.CategoryService;
import top.ssy.share.admin.service.NoticeService;
import top.ssy.share.admin.vo.CategoryVO;
import top.ssy.share.admin.vo.NoticeVO;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 分类接口
 * @create : 2024-03-01 14:33
 **/


@RestController
@AllArgsConstructor
@Tag(name = "分类接口", description = "分类接口")
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/page")
    @Operation(summary = "分页")
    public Result<PageResult<CategoryVO>> page(@RequestBody @Valid CategoryQuery query) {
        return Result.ok(categoryService.page(query));
    }

    @PostMapping("saveAndEdit")
    @Operation(summary = "新增或修改")
    public Result<String> saveAndEdit(@RequestBody @Valid CategoryEditDTO dto) {

        categoryService.saveAndEdit(dto);

        return Result.ok();
    }
}
