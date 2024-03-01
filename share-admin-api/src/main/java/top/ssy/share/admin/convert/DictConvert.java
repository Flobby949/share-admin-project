package top.ssy.share.admin.convert;

import top.ssy.share.admin.dto.DictRequestDTO;
import top.ssy.share.admin.entity.Dict;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictConvert {
    DictConvert INSTANCE = Mappers.getMapper(DictConvert.class);


    Dict convertToDict(DictRequestDTO dto);
}
