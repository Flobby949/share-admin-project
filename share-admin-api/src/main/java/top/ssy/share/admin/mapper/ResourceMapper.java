package top.ssy.share.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.ssy.share.admin.entity.Resource;
import top.ssy.share.admin.query.ResourceQuery;
import top.ssy.share.admin.vo.ResourceVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 15:40
 **/

public interface ResourceMapper extends BaseMapper<Resource> {

    List<ResourceVO> getResourcePage(Page<ResourceVO> page, @Param("query") ResourceQuery query);
}
