package top.ssy.share.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 用户状态
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Getter
@AllArgsConstructor
public enum AccountStatusEnum {
    /**
     * 停用
     */
    DISABLE(0, "停用"),
    /**
     * 正常
     */
    ENABLED(1, "正常");

    private final int value;
    private final String name;

    public static String getNameByValue(int value) {
        for (AccountStatusEnum s : AccountStatusEnum.values()) {
            if (s.getValue() == value) {
                return s.getName();
            }
        }
        return "";
    }

    public static Integer getValueByName(String name) {
        for (AccountStatusEnum s : AccountStatusEnum.values()) {
            if (Objects.equals(s.getName(), name)) {
                return s.getValue();
            }
        }
        return null;
    }
}
