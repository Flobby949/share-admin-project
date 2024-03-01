package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.convert.NoticeConvert;
import top.ssy.share.admin.dto.NoticeEditDTO;
import top.ssy.share.admin.entity.Notice;
import top.ssy.share.admin.mapper.NoticeMapper;
import top.ssy.share.admin.query.NoticeQuery;
import top.ssy.share.admin.service.NoticeService;
import top.ssy.share.admin.vo.NoticeVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description :
 * @create : 2024-03-01 14:35
 **/

@Service
@AllArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public PageResult<NoticeVO> page(NoticeQuery query) {
        Page<NoticeVO> page = new Page<>(query.getPage(), query.getLimit());
        List<NoticeVO> list = baseMapper.getNoticePage(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public void saveAndEdit(NoticeEditDTO dto) {
        Notice newNotice = NoticeConvert.INSTANCE.convert(dto);
        if (newNotice.getPkId() == null) {
            save(newNotice);
        } else {
            updateById(newNotice);
        }
    }
}
