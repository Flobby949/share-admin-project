package top.ssy.share.admin.common.model;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    /**
     * 获取分页对象
     *
     * @param query 分页参数
     */
    protected Page<T> getPage(Query query) {
        Page<T> page = new Page<>(query.getPage(), query.getLimit());

        // 排序
        if (StringUtils.isNotBlank(query.getOrder())) {
            if (query.isAsc()) {
                return page.addOrder(OrderItem.asc(query.getOrder()));
            } else {
                return page.addOrder(OrderItem.desc(query.getOrder()));
            }
        }
        return page;
    }
}