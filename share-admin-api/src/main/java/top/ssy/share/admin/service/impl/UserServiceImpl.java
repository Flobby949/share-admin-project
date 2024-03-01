package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ssy.share.admin.common.exception.ServerException;
import top.ssy.share.admin.common.model.BaseServiceImpl;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.convert.UserConvert;
import top.ssy.share.admin.dto.UserEditDTO;
import top.ssy.share.admin.entity.User;
import top.ssy.share.admin.mapper.UserMapper;
import top.ssy.share.admin.query.UserQuery;
import top.ssy.share.admin.service.UserService;
import top.ssy.share.admin.vo.UserInfoVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : impl
 * @create : 2024-03-01 13:07
 **/

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public PageResult<UserInfoVO> page(UserQuery query) {
        Page<UserInfoVO> page = new Page<>(query.getPage(), query.getLimit());
        List<UserInfoVO> list = baseMapper.getUserPage(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public void update(UserEditDTO dto) {
        User user = UserConvert.INSTANCE.convert(dto);
        User byPhone = baseMapper.getByPhone(user.getPhone());
        if (byPhone != null && !byPhone.getPkId().equals(user.getPkId())) {
            throw new ServerException("手机号已存在");
        }
        baseMapper.updateById(user);
    }
}
