package top.ssy.share.admin.mapper;

import top.ssy.share.admin.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单关系 Mapper 接口
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Integer> getMenuIdList(@Param("roleId") Integer roleId);

}
