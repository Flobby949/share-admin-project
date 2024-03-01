package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.dto.UserEditDTO;
import top.ssy.share.admin.entity.User;
import top.ssy.share.admin.query.UserQuery;
import top.ssy.share.admin.vo.UserInfoVO;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : service
 * @create : 2024-03-01 13:07
 **/

public interface UserService  extends IService<User> {

    PageResult<UserInfoVO> page(UserQuery query);

    void update(UserEditDTO dto);
}
