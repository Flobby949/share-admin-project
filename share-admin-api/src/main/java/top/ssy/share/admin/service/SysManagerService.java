package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.entity.SysManager;
import top.ssy.share.admin.query.ChangePasswordQuery;
import top.ssy.share.admin.query.SysManagerQuery;
import top.ssy.share.admin.security.user.ManagerDetail;
import top.ssy.share.admin.vo.SysManagerVO;

import java.util.List;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
public interface SysManagerService extends IService<SysManager> {

    /**
     * 管理员列表
     *
     * @param query
     * @return
     */
    PageResult<SysManagerVO> page(SysManagerQuery query);

    /**
     * 新增管理员
     *
     * @param vo
     */
    void save(SysManagerVO vo);

    /**
     * 修改管理员信息
     *
     * @param vo
     */
    void update(SysManagerVO vo);

    /**
     * 删除管理员信息
     *
     * @param idList
     */
    void delete(List<Integer> idList);

    /**
     * 修改密码
     *
     * @param query
     */

    void changePassword(ChangePasswordQuery query);

    /**
     * 获取管理员信息
     *
     * @param manage
     * @return
     */

    SysManagerVO getManagerInfo(ManagerDetail manage);
}
