package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.ssy.share.admin.common.exception.ServerException;
import top.ssy.share.admin.convert.DictConfigConvert;
import top.ssy.share.admin.dto.DictConfigRequestDTO;
import top.ssy.share.admin.entity.DictConfig;
import top.ssy.share.admin.mapper.DictConfigMapper;
import top.ssy.share.admin.query.DictConfigQuery;
import top.ssy.share.admin.service.DictConfigService;
import top.ssy.share.admin.service.DictService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DictConfigServiceImpl extends ServiceImpl<DictConfigMapper, DictConfig> implements DictConfigService {

    private final DictService dictService;

    @Override
    public List<DictConfig> list(DictConfigQuery query) {
        LambdaQueryWrapper<DictConfig> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(query.getTitle())) {
            wrapper.like(DictConfig::getTitle, query.getTitle());
        }
        wrapper.eq(DictConfig::getDictNumber, query.getNumber());
        wrapper.orderByDesc(DictConfig::getCreateTime);
        List<DictConfig> result = baseMapper.selectList(wrapper);
        return result;
    }

    @Override
    public void add(DictConfigRequestDTO dto) {
        DictConfig dictConfig = DictConfigConvert.INSTANCE.convertToConfig(dto);
        LambdaQueryWrapper<DictConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictConfig::getDictNumber, dictConfig.getDictNumber());
        wrapper.eq(DictConfig::getTitle, dictConfig.getTitle());
        List<DictConfig> dictConfigs = baseMapper.selectList(wrapper);
        if (dictConfigs.size() > 0) {
            throw new ServerException("该字典标题已存在");
        }
        wrapper.clear();
        dictConfigs.clear();
        wrapper.eq(DictConfig::getDictNumber, dictConfig.getDictNumber());
        wrapper.eq(DictConfig::getValue, dictConfig.getValue());
        dictConfigs = baseMapper.selectList(wrapper);
        if (dictConfigs.size() > 0) {
            throw new ServerException("该字典值已存在");
        }
        baseMapper.insert(dictConfig);
        dictService.refreshTransCache();
    }


    @Override
    public void edit(DictConfigRequestDTO dto) {
        DictConfig dictConfig = DictConfigConvert.INSTANCE.convertToConfig(dto);
        LambdaQueryWrapper<DictConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictConfig::getDictNumber, dictConfig.getDictNumber());
        wrapper.eq(DictConfig::getTitle, dictConfig.getTitle());
        List<DictConfig> dictConfigs = baseMapper.selectList(wrapper);
        if (dictConfigs.size() > 0) {
            throw new ServerException("该字典标题已存在");
        }
        wrapper.clear();
        dictConfigs.clear();
        wrapper.eq(DictConfig::getDictNumber, dictConfig.getDictNumber());
        wrapper.eq(DictConfig::getValue, dictConfig.getValue());
        dictConfigs = baseMapper.selectList(wrapper);
        if (dictConfigs.size() > 0) {
            throw new ServerException("该字典值已存在");
        }
        updateById(dictConfig);
        dictService.refreshTransCache();
    }

    @Override
    public void remove(List<Integer> ids) {
        removeBatchByIds(ids);
    }
}
