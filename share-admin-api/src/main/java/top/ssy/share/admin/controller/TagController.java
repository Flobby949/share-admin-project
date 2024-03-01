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
import top.ssy.share.admin.dto.TagEditDTO;
import top.ssy.share.admin.query.TagQuery;
import top.ssy.share.admin.service.TagService;
import top.ssy.share.admin.vo.TagVO;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 标签接口
 * @create : 2024-03-01 14:33
 **/


@RestController
@AllArgsConstructor
@Tag(name = "标签接口", description = "标签接口")
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @PostMapping("/page")
    @Operation(summary = "分页")
    public Result<PageResult<TagVO>> page(@RequestBody @Valid TagQuery query) {
        return Result.ok(tagService.page(query));
    }

    @PostMapping("saveAndEdit")
    @Operation(summary = "新增或修改")
    public Result<String> saveAndEdit(@RequestBody @Valid TagEditDTO dto) {
        tagService.saveAndEdit(dto);

        return Result.ok();
    }
}