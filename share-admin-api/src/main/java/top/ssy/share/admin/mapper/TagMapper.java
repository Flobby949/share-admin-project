package top.ssy.share.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.ssy.share.admin.entity.Tag;
import top.ssy.share.admin.query.TagQuery;
import top.ssy.share.admin.vo.TagVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 标签mapper
 * @create : 2024-03-01 15:59
 **/


public interface TagMapper extends BaseMapper<Tag> {

    List<TagVO> getTagPage(Page<TagVO> page, @Param("query") TagQuery query);
}
