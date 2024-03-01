package top.ssy.share.admin.convert;

import top.ssy.share.admin.dto.DictConfigRequestDTO;
import top.ssy.share.admin.entity.DictConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DictConfigConvert {
    DictConfigConvert INSTANCE = Mappers.getMapper(DictConfigConvert.class);


    DictConfig convertToConfig(DictConfigRequestDTO dto);
}
