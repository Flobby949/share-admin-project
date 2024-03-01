package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.ssy.share.admin.common.exception.ServerException;
import top.ssy.share.admin.common.model.BaseServiceImpl;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.convert.SysManagerConvert;
import top.ssy.share.admin.entity.SysManager;
import top.ssy.share.admin.enums.SuperAdminEnum;
import top.ssy.share.admin.mapper.SysManagerMapper;
import top.ssy.share.admin.query.ChangePasswordQuery;
import top.ssy.share.admin.query.SysManagerQuery;
import top.ssy.share.admin.security.user.ManagerDetail;
import top.ssy.share.admin.service.SysManagerRoleService;
import top.ssy.share.admin.service.SysManagerService;
import top.ssy.share.admin.vo.SysManagerVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
@Service
@AllArgsConstructor
public class SysManagerServiceImpl extends BaseServiceImpl<SysManagerMapper, SysManager> implements SysManagerService {

    private SysManagerRoleService sysManagerRoleService;

    @Override
    public PageResult<SysManagerVO> page(SysManagerQuery query) {
        Page<SysManagerVO> page = new Page<>(query.getPage(), query.getLimit());
        List<SysManagerVO> list = baseMapper.getManagerPage(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysManagerVO vo) {
        SysManager entity = SysManagerConvert.INSTANCE.convert(vo);
        entity.setSuperAdmin(SuperAdminEnum.NO.getValue());

        // 判断用户名是否存在
        SysManager manager = baseMapper.getByUsername(entity.getUsername());
        if (manager != null) {
            throw new ServerException("用户名已经存在");
        }
        // 保存用户
        baseMapper.insert(entity);
        // 保存用户角色关系
        sysManagerRoleService.saveOrUpdate(entity.getPkId(), vo.getRoleId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysManagerVO vo) {
        SysManager entity = SysManagerConvert.INSTANCE.convert(vo);
        // 判断用户名是否存在
        SysManager manager = baseMapper.getByUsername(entity.getUsername());
        if (manager != null && !manager.getPkId().equals(entity.getPkId())) {
            throw new ServerException("用户名已经存在");
        }
        // 更新用户
        updateById(entity);
        // 更新用户角色关系
        sysManagerRoleService.saveOrUpdate(entity.getPkId(), vo.getRoleId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> idList) {
        // 删除管理员
        removeByIds(idList);
        // 删除用户角色关系
        sysManagerRoleService.removeByManagerId(idList);
    }

    @Override
    public SysManagerVO getManagerInfo(ManagerDetail manage) {
        SysManagerVO sysManagerVO = new SysManagerVO();
        SysManager sysManager = baseMapper.selectById(manage.getPkId());
        if (sysManager == null) {
            throw new ServerException("管理员不存在");
        }
        sysManagerVO.setPkId(sysManager.getPkId());
        sysManagerVO.setAvatar(sysManager.getAvatar());
        sysManagerVO.setUsername(sysManager.getUsername());
        sysManagerVO.setStatus(sysManager.getStatus());
        sysManagerVO.setRoleId(sysManagerRoleService.getByManagerId(manage.getPkId()).getRoleId());
        sysManagerVO.setCreateTime(sysManager.getCreateTime());
        return sysManagerVO;
    }

    @Override
    public void changePassword(ChangePasswordQuery query) {
        SysManager sysManager = baseMapper.selectById(query.getPkId());
        if (sysManager == null) {
            throw new ServerException("管理员不存在");
        }
        sysManager.setPassword(query.getPassword());
        updateById(sysManager);
    }
}
