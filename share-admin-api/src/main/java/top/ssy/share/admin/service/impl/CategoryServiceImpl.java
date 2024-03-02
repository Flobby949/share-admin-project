package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.convert.CategoryConvert;
import top.ssy.share.admin.dto.CategoryEditDTO;
import top.ssy.share.admin.entity.Category;
import top.ssy.share.admin.enums.DeleteFlagEnum;
import top.ssy.share.admin.mapper.CategoryMapper;
import top.ssy.share.admin.query.CategoryQuery;
import top.ssy.share.admin.service.CategoryService;
import top.ssy.share.admin.vo.CategoryVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 14:55
 **/

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public PageResult<CategoryVO> page(CategoryQuery query) {
        Page<CategoryVO> page = new Page<>(query.getPage(), query.getLimit());
        List<CategoryVO> list = baseMapper.getCategoryPage(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public void saveAndEdit(CategoryEditDTO dto) {
        Category category = CategoryConvert.INSTANCE.convert(dto);
        if (dto.getPkId() == null) {
            save(category);
        } else {
            updateById(category);
        }
    }

    @Override
    public List<String> listByPkIdList(List<Integer> pkIdList) {
        return baseMapper.selectBatchIds(pkIdList)
                .stream()
                .filter(category -> Objects.equals(category.getDeleteFlag(), DeleteFlagEnum.NOT_DELETE.getCode()))
                .map(Category::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(List<Integer> id) {
        baseMapper.deleteBatchIds(id);
    }
}
