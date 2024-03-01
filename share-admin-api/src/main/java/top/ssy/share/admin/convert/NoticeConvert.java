package top.ssy.share.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ssy.share.admin.dto.NoticeEditDTO;
import top.ssy.share.admin.entity.Notice;
import top.ssy.share.admin.vo.NoticeVO;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 14:40
 **/

@Mapper
public interface NoticeConvert {
    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    NoticeVO convert(Notice notice);

    Notice convert(NoticeEditDTO dto);
}
