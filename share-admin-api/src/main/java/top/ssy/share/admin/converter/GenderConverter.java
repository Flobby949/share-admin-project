package top.ssy.share.admin.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

/**
 * @author : Flobby
 * @program : share-admin-api
 * @description : 用户性别转换器
 * @create : 2024-03-02 15:49
 **/

public class GenderConverter implements Converter<Integer> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) {
        Integer value = context.getValue();
        if (value == 0) {
            return new WriteCellData<>("男");
        } else if (value == 1) {
            return new WriteCellData<>("女");
        } else {
            return new WriteCellData<>("未知");
        }
    }
}
