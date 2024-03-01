package top.ssy.share.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型枚举
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    /**
     * 菜单目录
     */
    MENU_DIR,
    /**
     * 菜单项
     */
    MENU,
    /**
     * 按钮
     */
    BUTTON;

}
