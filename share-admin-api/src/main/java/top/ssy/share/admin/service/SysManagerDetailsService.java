package top.ssy.share.admin.service;

import top.ssy.share.admin.entity.SysManager;
import org.springframework.security.core.userdetails.UserDetails;

public interface SysManagerDetailsService {

    /**
     * 获取 UserDetails 对象
     */
    UserDetails getManagerDetails(SysManager sysManager);
}
