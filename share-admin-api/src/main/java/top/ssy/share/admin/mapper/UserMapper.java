package top.ssy.share.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.ssy.share.admin.entity.User;
import top.ssy.share.admin.query.UserQuery;
import top.ssy.share.admin.vo.UserInfoVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : mapper
 * @create : 2024-03-01 13:06
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {

    default User getByPhone(String phone){
        return this.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
    }

    List<UserInfoVO> getUserPage(Page<UserInfoVO> page, @Param("query") UserQuery query);
}
