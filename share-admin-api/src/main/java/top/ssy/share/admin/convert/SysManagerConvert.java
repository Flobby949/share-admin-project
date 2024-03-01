package top.ssy.share.admin.convert;

import top.ssy.share.admin.entity.SysManager;
import top.ssy.share.admin.security.user.ManagerDetail;
import top.ssy.share.admin.vo.SysManagerVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SysManagerConvert {
    SysManagerConvert INSTANCE = Mappers.getMapper(SysManagerConvert.class);

    SysManager convert(SysManagerVO vo);

    ManagerDetail convertDetail(SysManager entity);

    List<SysManagerVO> convertList(List<SysManager> list);
}
