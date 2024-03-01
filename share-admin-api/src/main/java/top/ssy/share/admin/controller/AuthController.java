package top.ssy.share.admin.controller;

import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.security.utils.TokenUtils;
import top.ssy.share.admin.service.AuthService;
import top.ssy.share.admin.service.SysCaptchaService;
import top.ssy.share.admin.vo.SysAccountLoginVO;
import top.ssy.share.admin.vo.SysCaptchaVO;
import top.ssy.share.admin.vo.SysTokenVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
@Tag(name = "认证管理")
@RestController
@RequestMapping("sys/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final SysCaptchaService sysCaptchaService;

    @PostMapping("login")
    @Operation(summary = "账号密码登录")
    public Result<SysTokenVO> login(@RequestBody SysAccountLoginVO login) {
        return Result.ok(authService.loginByAccount(login));
    }

    @PostMapping("captcha")
    @Operation(summary = "图片验证码")
    public Result<SysCaptchaVO> captcha() {
        SysCaptchaVO captchaVO = sysCaptchaService.generate();

        return Result.ok(captchaVO);
    }

    @PostMapping("logout")
    @Operation(summary = "退出")
    public Result<String> logout(HttpServletRequest request) {
        authService.logout(TokenUtils.getAccessToken(request));

        return Result.ok();
    }

}
