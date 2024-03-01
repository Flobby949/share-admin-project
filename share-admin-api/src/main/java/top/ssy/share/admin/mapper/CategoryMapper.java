package top.ssy.share.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.ssy.share.admin.entity.Category;
import top.ssy.share.admin.query.CategoryQuery;
import top.ssy.share.admin.vo.CategoryVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 14:54
 **/

public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVO> getCategoryPage(Page<CategoryVO> page, @Param("query") CategoryQuery query);
}
