package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.entity.UserAction;
import top.ssy.share.admin.enums.UserActionEnum;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-05 16:07
 **/

public interface UserActionService extends IService<UserAction> {

    void insertUserAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum);

    void deleteUserAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum);

}
