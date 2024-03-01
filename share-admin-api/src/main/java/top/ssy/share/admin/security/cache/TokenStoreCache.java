package top.ssy.share.admin.security.cache;

import com.alibaba.fastjson2.JSON;
import top.ssy.share.admin.common.cache.RedisCache;
import top.ssy.share.admin.common.cache.RedisKeys;
import top.ssy.share.admin.security.user.ManagerDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycshang
 */
@Component
@AllArgsConstructor
public class TokenStoreCache {
    private final RedisCache redisCache;

    public void saveUser(String accessToken, ManagerDetail user) {
        String accessTokenKey = RedisKeys.getAccessTokenKey(accessToken);
        String managerIdKey = RedisKeys.getManagerIdKey(user.getPkId());
        if (redisCache.get(managerIdKey) != null) {
            redisCache.delete(String.valueOf(redisCache.get(managerIdKey)));
        }
        redisCache.set(managerIdKey, accessTokenKey);
        redisCache.set(accessTokenKey, user);
    }

    public ManagerDetail getUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        return JSON.to(ManagerDetail.class, redisCache.get(key));
    }

    public void deleteUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.delete(key);
    }

    public void deleteUserById(Integer id) {
        String managerIdKey = RedisKeys.getManagerIdKey(id);
        String key = String.valueOf(redisCache.get(managerIdKey));
        redisCache.delete(key);
    }

    public void deleteUserByIds(List<Integer> ids) {
        List<String> keys = new ArrayList<>();
        for (Integer id : ids) {
            String managerIdKey = RedisKeys.getManagerIdKey(id);
            String key = String.valueOf(redisCache.get(managerIdKey));
            keys.add(key);
        }
        redisCache.delete(keys);
    }
}
