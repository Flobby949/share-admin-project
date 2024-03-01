package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.ssy.share.admin.common.model.BaseServiceImpl;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.convert.SysRoleConvert;
import top.ssy.share.admin.entity.SysRole;
import top.ssy.share.admin.mapper.SysRoleMapper;
import top.ssy.share.admin.query.SysRoleQuery;
import top.ssy.share.admin.service.SysManagerRoleService;
import top.ssy.share.admin.service.SysRoleMenuService;
import top.ssy.share.admin.service.SysRoleService;
import top.ssy.share.admin.vo.SysRoleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private SysRoleMenuService sysRoleMenuService;
    private SysManagerRoleService sysManagerRoleService;

    @Override
    public PageResult<SysRoleVO> page(SysRoleQuery query) {
        Page<SysRoleVO> page = new Page<>(query.getPage(),query.getLimit());
        List<SysRoleVO> list = baseMapper.getRolePage(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<SysRoleVO> getList(SysRoleQuery query) {
        List<SysRole> entityList = baseMapper.selectList(new LambdaQueryWrapper<>());
        return SysRoleConvert.INSTANCE.convertList(entityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleVO vo) {
        SysRole entity = SysRoleConvert.INSTANCE.convert(vo);
        // 保存角色
        baseMapper.insert(entity);
        // 保存角色菜单关系
        sysRoleMenuService.saveOrUpdate(entity.getPkId(), vo.getMenuIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleVO vo) {
        SysRole entity = SysRoleConvert.INSTANCE.convert(vo);

        // 更新角色
        updateById(entity);

        // 更新角色菜单关系
        sysRoleMenuService.saveOrUpdate(entity.getPkId(), vo.getMenuIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> idList) {
        // 删除角色
        removeByIds(idList);

        // 删除用户角色关系
        sysManagerRoleService.deleteByRoleIdList(idList);

        // 删除角色菜单关系
        sysRoleMenuService.deleteByRoleIdList(idList);
    }
}
