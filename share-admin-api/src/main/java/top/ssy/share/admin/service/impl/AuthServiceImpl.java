package top.ssy.share.admin.service.impl;

import top.ssy.share.admin.common.exception.ServerException;
import top.ssy.share.admin.security.cache.TokenStoreCache;
import top.ssy.share.admin.security.user.ManagerDetail;
import top.ssy.share.admin.security.utils.TokenUtils;
import top.ssy.share.admin.service.AuthService;
import top.ssy.share.admin.vo.SysAccountLoginVO;
import top.ssy.share.admin.vo.SysTokenVO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 认证服务实现
 *
 * @Author ycshang
 * @Date 2023-05-18 17:31
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenStoreCache tokenStoreCache;
    private final AuthenticationManager authenticationManager;

    @Override
    public SysTokenVO loginByAccount(SysAccountLoginVO params) {
        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(params.getUsername(), params.getPassword()));
        } catch (DisabledException e) {
            throw new ServerException("该账号已被禁用");
        } catch (BadCredentialsException e) {
            throw new ServerException("用户名或密码错误");
        }
        // 用户信息
        ManagerDetail user = (ManagerDetail) authentication.getPrincipal();
        // 生成 accessToken
        String accessToken = TokenUtils.generator();
        // 保存用户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);

        return new SysTokenVO(accessToken);
    }

    @Override
    public void logout(String accessToken) {
        // 用户信息
        ManagerDetail manager = tokenStoreCache.getUser(accessToken);

        // 删除用户信息
        tokenStoreCache.deleteUser(accessToken);

    }

}
