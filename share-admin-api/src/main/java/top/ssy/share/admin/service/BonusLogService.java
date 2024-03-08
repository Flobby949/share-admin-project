package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.entity.BonusLog;
import top.ssy.share.admin.enums.BonusActionEnum;
import top.ssy.share.admin.vo.BonusLogVO;

import java.util.List;
import java.util.Map;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-02 13:52
 **/

public interface BonusLogService extends IService<BonusLog> {

    void addBonusLog(Integer userId, BonusActionEnum contentEnum, Integer bonus);

    void addBonusLog(Integer userId, BonusActionEnum contentEnum);

    Map<String, List<BonusLogVO>> userBonusResult(Integer userId);

    Long todayUserCheckCount();
}
