package top.ssy.share.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ssy.share.admin.entity.Resource;
import top.ssy.share.admin.vo.ResourceVO;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 资源转换类
 * @create : 2024-03-02 12:25
 **/

@Mapper
public interface ResourceConvert {
    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    ResourceVO convert(Resource resource);
}
