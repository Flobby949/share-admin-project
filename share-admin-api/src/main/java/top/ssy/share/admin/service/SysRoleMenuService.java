package top.ssy.share.admin.service;

import top.ssy.share.admin.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单关系 服务类
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Integer> getMenuIdList(Integer roleId);

    /**
     * 根据菜单id，删除角色菜单关系
     * @param menuId 菜单id
     */
    void deleteByMenuId(Integer menuId);

    /**
     * 保存或修改
     * @param roleId      角色ID
     * @param menuIdList  菜单ID列表
     */
    void saveOrUpdate(Integer roleId, List<Integer> menuIdList);



    /**
     * 根据角色id列表，删除角色菜单关系
     * @param roleIdList 角色id列表
     */
    void deleteByRoleIdList(List<Integer> roleIdList);

}
