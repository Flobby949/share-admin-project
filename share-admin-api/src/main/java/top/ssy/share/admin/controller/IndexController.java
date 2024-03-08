package top.ssy.share.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.service.IndexService;
import top.ssy.share.admin.vo.DashboardDataVO;


/**
 * @author Flobby
 */

@RestController
@RequestMapping("index")
@Tag(name = "首页", description = "首页信息")
@AllArgsConstructor
public class IndexController {
    private final IndexService indexService;

    @GetMapping()
    @Operation(summary = "欢迎")
    public String index() {
        return "您好，项目已启动，祝您使用愉快！";
    }

    @GetMapping("dashboard")
    @Operation(summary = "首页数据")
    public Result<DashboardDataVO> dashboard() {
        return Result.ok(indexService.getDashboardData());
    }
}
