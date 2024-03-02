package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.dto.TagEditDTO;
import top.ssy.share.admin.entity.Tag;
import top.ssy.share.admin.query.TagQuery;
import top.ssy.share.admin.vo.TagVO;

import java.util.List;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 标签服务
 * @create : 2024-03-01 16:00
 **/

public interface TagService extends IService<Tag> {

    PageResult<TagVO> page(TagQuery query);

    void saveAndEdit(TagEditDTO dto);

    List<String> listByPkIdList(List<Integer> pkIdList);

    void delete(List<Integer> ids);
}
