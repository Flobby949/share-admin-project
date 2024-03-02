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
import top.ssy.share.admin.dto.CategoryEditDTO;
import top.ssy.share.admin.query.CategoryQuery;
import top.ssy.share.admin.service.CategoryService;
import top.ssy.share.admin.vo.CategoryVO;

import java.util.List;

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

    @PostMapping("/delete")
    @Operation(summary = "删除")
    public Result<String> delete(@RequestBody List<Integer> ids) {
        categoryService.delete(ids);
        return Result.ok();
    }
}
