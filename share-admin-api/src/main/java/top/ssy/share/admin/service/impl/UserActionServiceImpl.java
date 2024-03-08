package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ssy.share.admin.entity.UserAction;
import top.ssy.share.admin.enums.UserActionEnum;
import top.ssy.share.admin.mapper.UserActionMapper;
import top.ssy.share.admin.service.UserActionService;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-05 16:07
 **/

@Slf4j
@Service
@AllArgsConstructor
public class UserActionServiceImpl extends ServiceImpl<UserActionMapper, UserAction> implements UserActionService {

    @Override
    public void insertUserAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum) {
        UserAction userAction = new UserAction();
        userAction.setUserId(userId);
        userAction.setResourceId(resourceId);
        userAction.setType(userActionEnum.getCode());
        save(userAction);
    }

    @Override
    public void deleteUserAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum) {
        LambdaQueryWrapper<UserAction> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAction::getUserId, userId)
                .eq(UserAction::getResourceId, resourceId)
                .eq(UserAction::getType, userActionEnum.getCode());
        baseMapper.delete(wrapper);
    }
}
