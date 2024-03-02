package top.ssy.share.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ssy.share.admin.dto.UserEditDTO;
import top.ssy.share.admin.entity.User;
import top.ssy.share.admin.vo.UserInfoVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : user转换类
 * @create : 2024-03-01 13:53
 **/

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserEditDTO convert(User user);

    User convert(UserEditDTO dto);

    List<UserInfoVO> convert(List<User> list);
}
