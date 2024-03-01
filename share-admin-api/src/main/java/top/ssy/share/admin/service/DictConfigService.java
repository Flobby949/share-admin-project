package top.ssy.share.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ssy.share.admin.dto.DictConfigRequestDTO;
import top.ssy.share.admin.entity.DictConfig;
import top.ssy.share.admin.query.DictConfigQuery;

import java.util.List;

public interface DictConfigService extends IService<DictConfig> {

    /**
     * 字典列表
     *
     * @param query
     * @return
     */
    List<DictConfig> list(DictConfigQuery query);

    /**
     * 新增字典配置
     *
     * @param dto
     */
    void add(DictConfigRequestDTO dto);

    /**
     * 编辑字典配置
     *
     * @param dto
     */
    void edit(DictConfigRequestDTO dto);

    /**
     * 删除字典配置
     *
     * @param ids
     */
    void remove(List<Integer> ids);
}
