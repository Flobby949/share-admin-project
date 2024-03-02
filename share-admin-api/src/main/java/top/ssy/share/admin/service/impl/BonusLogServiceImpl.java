package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ssy.share.admin.convert.BonusLogConvert;
import top.ssy.share.admin.entity.BonusLog;
import top.ssy.share.admin.enums.BonusActionEnum;
import top.ssy.share.admin.mapper.BonusLogMapper;
import top.ssy.share.admin.service.BonusLogService;
import top.ssy.share.admin.vo.BonusLogVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-02 13:52
 **/

@Slf4j
@Service
public class BonusLogServiceImpl extends ServiceImpl<BonusLogMapper, BonusLog> implements BonusLogService {

    public void addBonusLog(Integer userId, String content, Integer bonus) {
        BonusLog bonusLog = new BonusLog();
        bonusLog.setUserId(userId);
        bonusLog.setContent(content);
        bonusLog.setBonus(bonus);
        save(bonusLog);
    }

    @Override
    public void addBonusLog(Integer userId, BonusActionEnum contentEnum, Integer bonus) {
        addBonusLog(userId, contentEnum.getType(), bonus);
    }

    @Override
    public void addBonusLog(Integer userId, BonusActionEnum contentEnum) {
        addBonusLog(userId, contentEnum.getType(), contentEnum.getBonus());
    }

    @Override
    public Map<String, List<BonusLogVO>> userBonusResult(Integer userId) {
        LambdaQueryWrapper<BonusLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(userId != null, BonusLog::getUserId, userId);
        List<BonusLogVO> voList = BonusLogConvert.INSTANCE.convert(list(wrapper));
        return voList.stream().collect(Collectors.groupingBy(BonusLogVO::getContent));
    }

}
