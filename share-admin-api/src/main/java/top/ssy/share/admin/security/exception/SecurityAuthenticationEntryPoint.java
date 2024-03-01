package top.ssy.share.admin.security.exception;


import com.alibaba.fastjson2.JSONObject;
import top.ssy.share.admin.common.exception.ErrorCode;
import top.ssy.share.admin.common.result.Result;
import top.ssy.share.admin.utils.HttpContextUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author ycshang
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        response.getWriter().print(JSONObject.toJSONString(Result.error(ErrorCode.UNAUTHORIZED)));
    }
}