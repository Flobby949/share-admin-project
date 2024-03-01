package top.ssy.share.admin.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import top.ssy.share.admin.common.constant.Constant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ycshang
 */
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        strictInsertFill(metaObject, Constant.CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        // 更新时间
        strictInsertFill(metaObject, Constant.UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        // 删除标识
        strictInsertFill(metaObject, Constant.DELETE_FLAG, Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        strictUpdateFill(metaObject, Constant.UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
}