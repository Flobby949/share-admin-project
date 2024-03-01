package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.convert.TagConvert;
import top.ssy.share.admin.dto.TagEditDTO;
import top.ssy.share.admin.entity.Tag;
import top.ssy.share.admin.mapper.TagMapper;
import top.ssy.share.admin.query.TagQuery;
import top.ssy.share.admin.service.TagService;
import top.ssy.share.admin.vo.TagVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 16:00
 **/

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public PageResult<TagVO> page(TagQuery query) {
        Page<TagVO> page = new Page<>(query.getPage(), query.getLimit());
        List<TagVO> list = baseMapper.getTagPage(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public void saveAndEdit(TagEditDTO dto) {
        Tag tag = TagConvert.INSTANCE.convert(dto);
        if (tag.getPkId() == null) {
            save(tag);
        } else {
            updateById(tag);
        }
    }
}
