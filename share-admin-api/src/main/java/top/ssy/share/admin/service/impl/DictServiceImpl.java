package top.ssy.share.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.ssy.share.admin.common.exception.ServerException;
import top.ssy.share.admin.common.result.PageResult;
import top.ssy.share.admin.convert.DictConvert;
import top.ssy.share.admin.dto.DictRequestDTO;
import top.ssy.share.admin.entity.Dict;
import top.ssy.share.admin.entity.DictConfig;
import top.ssy.share.admin.mapper.DictConfigMapper;
import top.ssy.share.admin.mapper.DictMapper;
import top.ssy.share.admin.query.DictQuery;
import top.ssy.share.admin.service.DictService;
import com.fhs.trans.service.impl.DictionaryTransService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    private final DictConfigMapper dictConfigMapper;
    private final DictionaryTransService dictionaryTransService;

    @Override
    public PageResult<Dict> getPage(DictQuery dictQuery) {
        Page<Dict> page = new Page<>(dictQuery.getPage(), dictQuery.getLimit());
        List<Dict> result = baseMapper.getPage(page, dictQuery);
        return new PageResult<>(result, page.getTotal());
    }

    @Override
    public boolean existByNumber(String number) {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getNumber, number);
        return baseMapper.exists(wrapper);
    }

    @Override
    public void add(DictRequestDTO dto) {
        Dict dict = DictConvert.INSTANCE.convertToDict(dto);
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getTitle, dict.getTitle());
        if (baseMapper.exists(wrapper)) {
            throw new ServerException("字典名称已存在");
        }
        wrapper.clear();
        wrapper.eq(Dict::getNumber, dict.getNumber());
        if (baseMapper.exists(wrapper)) {
            throw new ServerException("字典编号已存在");
        }
        baseMapper.insert(dict);
        refreshTransCache();
    }

    @Override
    public void edit(DictRequestDTO dto) {
        Dict updateDict = DictConvert.INSTANCE.convertToDict(dto);
        Dict dict = baseMapper.selectById(updateDict.getPkId());
        if (dict == null) {
            throw new ServerException("字典不存在");
        }
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getTitle, dict.getTitle()).ne(Dict::getPkId, dict.getPkId());
        if (baseMapper.exists(wrapper)) {
            throw new ServerException("字典名称已存在");
        }
        wrapper.clear();
        wrapper.eq(Dict::getNumber, dict.getNumber()).ne(Dict::getPkId, dict.getPkId());
        if (baseMapper.exists(wrapper)) {
            throw new ServerException("字典编号已存在");
        }

//        如果修改了字典编码,字典配置选项的编码都需要修改
        if (!dict.getNumber().equals(updateDict.getNumber())) {
            List<DictConfig> dictConfigs = dictConfigMapper.selectList(new LambdaQueryWrapper<DictConfig>().eq(DictConfig::getDictNumber, dict.getNumber()));
            dictConfigs.forEach(item -> {
                item.setDictNumber(updateDict.getNumber());
                dictConfigMapper.updateById(item);
            });
        }
        updateById(dict);
        refreshTransCache();

    }

    @Override
    public void remove(List<Integer> ids) {
        List<Dict> dicts = baseMapper.selectList(new LambdaQueryWrapper<Dict>().in(Dict::getPkId, ids));
        List<String> numbers = dicts.stream().map(Dict::getNumber).collect(Collectors.toList());
//       同步删除字典配置的子项
        dictConfigMapper.delete(new LambdaQueryWrapper<DictConfig>().in(DictConfig::getDictNumber, numbers));
        removeByIds(ids);

    }

    @Override
    public void refreshTransCache() {
//        异步不阻塞主线程，不会增加启动用时
        CompletableFuture.supplyAsync(() -> {
//            获取所有的字典项数据
            List<DictConfig> dataList = dictConfigMapper.selectList(null);

//            根据类型分组
            Map<String, List<DictConfig>> dictTypeDataMap = dataList.stream().collect(Collectors.groupingBy(DictConfig::getDictNumber));
            List<Dict> dicts = super.list();
            for (Dict dict : dicts) {
                if (dictTypeDataMap.containsKey(dict.getNumber())) {
                    dictionaryTransService.refreshCache(dict.getNumber(), dictTypeDataMap.get(dict.getNumber()).stream().collect(Collectors.toMap(DictConfig::getValue, DictConfig::getTitle)));
                }
            }
            return null;
        });

    }
}
