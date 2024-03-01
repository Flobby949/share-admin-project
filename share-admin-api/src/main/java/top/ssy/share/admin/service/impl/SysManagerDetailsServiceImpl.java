package top.ssy.share.admin.service.impl;

import top.ssy.share.admin.convert.SysManagerConvert;
import top.ssy.share.admin.entity.SysManager;
import top.ssy.share.admin.enums.AccountStatusEnum;
import top.ssy.share.admin.security.user.ManagerDetail;
import top.ssy.share.admin.service.SysManagerDetailsService;
import top.ssy.share.admin.service.SysMenuService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 用户 UserDetails 信息
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class SysManagerDetailsServiceImpl implements SysManagerDetailsService {
    private final SysMenuService sysMenuService;

    @Override
    public UserDetails getManagerDetails(SysManager sysManager) {
        // 转换成UserDetail对象
        ManagerDetail managerDetail = SysManagerConvert.INSTANCE.convertDetail(sysManager);

        // 账号不可用
        if (sysManager.getStatus() == AccountStatusEnum.DISABLE.getValue()) {
            managerDetail.setEnabled(false);
        }

        // 用户权限列表
        Set<String> authoritySet = sysMenuService.getManagerAuthority(managerDetail);
        managerDetail.setAuthoritySet(authoritySet);

        return managerDetail;
    }

}
