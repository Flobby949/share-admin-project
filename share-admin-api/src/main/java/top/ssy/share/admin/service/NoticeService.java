package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.dto.NoticeEditDTO;
import top.ssy.share.admin.entity.Notice;
import top.ssy.share.admin.query.NoticeQuery;
import top.ssy.share.admin.vo.NoticeVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 公告
 * @create : 2024-03-01 14:34
 **/


public interface NoticeService extends IService<Notice> {

    PageResult<NoticeVO> page(NoticeQuery query);

    void saveAndEdit(NoticeEditDTO dto);

    void delete(List<Integer> id);
}
