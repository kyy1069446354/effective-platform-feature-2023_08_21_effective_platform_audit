package com.chinapost.sd.effective.system.constant;


/**
 * @author tangyang
 * 对应 sys_menu表的menu_type字段
 */
public enum MenuTypeEnum  {

    /**
     * 菜单类型
     */
    DIRECTORY(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮");

    private final int value;
    private final String description;

    MenuTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }


    public Integer getValue() {
        return value;
    }


    public String description() {
        return description;
    }


}
