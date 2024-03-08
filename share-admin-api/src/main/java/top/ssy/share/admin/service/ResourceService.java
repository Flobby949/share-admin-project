package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.dto.ResourceAuditDTO;
import top.ssy.share.admin.entity.Resource;
import top.ssy.share.admin.enums.ResourceStatusEnum;
import top.ssy.share.admin.query.ResourceQuery;
import top.ssy.share.admin.vo.ResourceVO;

import java.util.Map;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 15:41
 **/

public interface ResourceService extends IService<Resource> {

    /**
     * 分页
     *
     * @param query 查询
     * @return {@link PageResult}<{@link ResourceVO}>
     */
    PageResult<ResourceVO> page(ResourceQuery query);

    void audit(ResourceAuditDTO dto);

    Map<String, Long> countResource();

    Long countResourceByStatus(ResourceStatusEnum status);
}
