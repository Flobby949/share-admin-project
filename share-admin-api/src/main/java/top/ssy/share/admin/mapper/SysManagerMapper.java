package top.ssy.share.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.ssy.share.admin.entity.SysManager;
import top.ssy.share.admin.query.SysManagerQuery;
import top.ssy.share.admin.vo.SysManagerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户管理 Mapper 接口
 * </p>
 *
 * @author ycshang
 * @since 2023-05-18
 */
public interface SysManagerMapper extends BaseMapper<SysManager> {

    default SysManager getByUsername(String username){
        return this.selectOne(new LambdaQueryWrapper<SysManager>().eq(SysManager::getUsername, username));
    }

    List<SysManagerVO> getManagerPage(Page<SysManagerVO> page, @Param("query") SysManagerQuery query);
}
