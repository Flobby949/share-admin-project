package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ssy.share.admin.common.exception.ServerException;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.convert.ResourceConvert;
import top.ssy.share.admin.dto.ResourceAuditDTO;
import top.ssy.share.admin.entity.Category;
import top.ssy.share.admin.entity.Resource;
import top.ssy.share.admin.entity.User;
import top.ssy.share.admin.enums.BonusActionEnum;
import top.ssy.share.admin.enums.DeleteFlagEnum;
import top.ssy.share.admin.mapper.ResourceMapper;
import top.ssy.share.admin.query.ResourceQuery;
import top.ssy.share.admin.service.*;
import top.ssy.share.admin.vo.ResourceVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 15:41
 **/

@Slf4j
@Service
@AllArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    private final TagService tagService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final BonusLogService bonusLogService;

    @Override
    public PageResult<ResourceVO> page(ResourceQuery query) {
        LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(Resource::getDeleteFlag, DeleteFlagEnum.NOT_DELETE.getCode())
                .eq(StringUtils.isNotBlank(query.getTitle()), Resource::getTitle, query.getTitle())
                .eq(ObjectUtils.isNotEmpty(query.getAuthor()), Resource::getAuthor, query.getAuthor())
                .eq(ObjectUtils.isNotEmpty(query.getIsTop()), Resource::getIsTop, query.getIsTop())
                .eq(ObjectUtils.isNotEmpty(query.getStatus()), Resource::getStatus, query.getStatus());
        Page<Resource> resourcePage = baseMapper.selectPage(new Page<>(query.getPage(), query.getLimit()), wrapper);
        List<Resource> list = resourcePage.getRecords();
        List<ResourceVO> resourceVOList = list.stream().map(item -> {
            ResourceVO resource = ResourceConvert.INSTANCE.convert(item);
            Category diskType = categoryService.getById(item.getDiskType());
            User author = userService.getById(item.getAuthor());
            resource.setAuthorName(author.getNickname());
            resource.setDiskType(diskType.getTitle());
            resource.setResTypeList(categoryService.listByPkIdList(resource.getResType()));
            resource.setTagList(tagService.listByPkIdList(resource.getTags()));
            return resource;
        }).collect(Collectors.toList());
        return new PageResult<>(resourceVOList, resourcePage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void audit(ResourceAuditDTO dto) {
        Resource resource = baseMapper.selectById(dto.getPkId());
        if (ObjectUtils.isEmpty(resource)) {
            log.error("资源不存在, pkId:{}", dto.getPkId());
            throw new ServerException("资源不存在");
        }
        resource.setStatus(dto.getStatus());
        resource.setRemark(dto.getRemark());
        baseMapper.updateById(resource);

        User author = userService.getById(resource.getAuthor());
        if (ObjectUtils.isEmpty(author)) {
            log.error("作者不存在, pkId:{}", resource.getAuthor());
            throw new ServerException("作者不存在");
        }
        author.setBonus(author.getBonus() + 10);
        userService.updateById(author);

        bonusLogService.addBonusLog(author.getPkId(), BonusActionEnum.RESOURCE_AUDIT_PASS);
    }
}
