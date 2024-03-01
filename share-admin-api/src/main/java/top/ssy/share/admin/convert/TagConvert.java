package top.ssy.share.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ssy.share.admin.dto.TagEditDTO;
import top.ssy.share.admin.entity.Tag;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 16:02
 **/

@Mapper
public interface TagConvert {

    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    Tag convert(TagEditDTO dto);
}
