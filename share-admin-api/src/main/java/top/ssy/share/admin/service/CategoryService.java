package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.dto.CategoryEditDTO;
import top.ssy.share.admin.entity.Category;
import top.ssy.share.admin.query.CategoryQuery;
import top.ssy.share.admin.vo.CategoryVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 14:55
 **/

public interface CategoryService extends IService<Category> {

    PageResult<CategoryVO> page(CategoryQuery query);

    void saveAndEdit(CategoryEditDTO dto);

    List<String> listByPkIdList(List<Integer> pkIdList);

    void delete(List<Integer>  id);
}
