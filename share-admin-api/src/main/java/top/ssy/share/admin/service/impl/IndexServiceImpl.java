package top.ssy.share.admin.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ssy.share.admin.enums.ResourceStatusEnum;
import top.ssy.share.admin.service.*;
import top.ssy.share.admin.vo.DashboardDataVO;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-08 09:25
 **/

@Slf4j
@Service
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {
    private final UserService userService;
    private final TagService tagService;
    private final ResourceService resourceService;
    private final BonusLogService bonusLogService;

    @Override
    public DashboardDataVO getDashboardData() {
        DashboardDataVO resultVo = new DashboardDataVO();
        // 获取标签列表
        resultVo.setTagList(tagService.list());
        // 获取用户总数
        resultVo.setAllUserCount(userService.count());
        // 获取今日签到数·
        resultVo.setTodayUserCheckCount(bonusLogService.todayUserCheckCount());
        // 获取资源总数
        resultVo.setResourceCount(resourceService.count());
        // 未审批资源总数
        resultVo.setResourceUnAuditCount(resourceService.countResourceByStatus(ResourceStatusEnum.UNAUDITED));
        // 获取资源map
        resultVo.setResourceCountMap(resourceService.countResource());
        return resultVo;
    }
}
