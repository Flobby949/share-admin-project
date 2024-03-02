package top.ssy.share.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ssy.share.admin.entity.BonusLog;
import top.ssy.share.admin.vo.BonusLogVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 积分转换
 * @create : 2024-03-02 14:04
 **/

@Mapper
public interface BonusLogConvert {
    BonusLogConvert INSTANCE = Mappers.getMapper(BonusLogConvert.class);

    List<BonusLogVO> convert(List<BonusLog> list);
}
