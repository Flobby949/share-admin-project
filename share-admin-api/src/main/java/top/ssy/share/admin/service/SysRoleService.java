package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.entity.SysRole;
import top.ssy.share.admin.query.SysRoleQuery;
import top.ssy.share.admin.vo.SysRoleVO;

import java.util.List;

/**
 * <p>
 * 角色管理 服务类
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
public interface SysRoleService extends IService<SysRole> {
    PageResult<SysRoleVO> page(SysRoleQuery query);

    List<SysRoleVO> getList(SysRoleQuery query);

    void save(SysRoleVO vo);

    void update(SysRoleVO vo);

    void delete(List<Integer> idList);
}
