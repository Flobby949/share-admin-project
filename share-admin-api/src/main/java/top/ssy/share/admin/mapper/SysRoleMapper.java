package top.ssy.share.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.ssy.share.admin.entity.SysRole;
import top.ssy.share.admin.query.SysRoleQuery;
import top.ssy.share.admin.vo.SysRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色管理 Mapper 接口
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRoleVO> getRolePage(Page<SysRoleVO> page, @Param("query") SysRoleQuery query);

}
