package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.entity.Resource;
import top.ssy.share.admin.mapper.ResourceMapper;
import top.ssy.share.admin.query.ResourceQuery;
import top.ssy.share.admin.service.ResourceService;
import top.ssy.share.admin.vo.ResourceVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 15:41
 **/

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {


    @Override
    public PageResult<ResourceVO> page(ResourceQuery query) {
        Page<ResourceVO> page = new Page<>(query.getPage(), query.getLimit());
        List<ResourceVO> list = baseMapper.getResourcePage(page, query);
        return null;
    }
}
