package top.ssy.share.admin.controller;


import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.service.CommonService;
import top.ssy.share.admin.vo.FileUrlVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "通用模块")
@RestController
@RequestMapping("common")
@AllArgsConstructor
public class CommonController {

	private final CommonService commonService;

	@PostMapping(value = "/upload/img")
	@ResponseBody
	@Operation(summary = "图片上传")
	public Result<FileUrlVO> upload(@RequestBody MultipartFile file) {
		return Result.ok(commonService.upload(file));
	}

}
