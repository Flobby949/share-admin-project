package top.ssy.share.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ssy.share.admin.dto.CategoryEditDTO;
import top.ssy.share.admin.entity.Category;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 分类
 * @create : 2024-03-01 14:53
 **/

@Mapper
public interface CategoryConvert {
    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    Category convert(CategoryEditDTO dto);
}
