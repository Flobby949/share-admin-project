package top.ssy.share.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.ssy.share.admin.entity.Notice;
import top.ssy.share.admin.query.NoticeQuery;
import top.ssy.share.admin.vo.NoticeVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : mapper
 * @create : 2024-03-01 13:06
 **/

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    List<NoticeVO> getNoticePage(Page<NoticeVO> page, @Param("query") NoticeQuery query);
}
