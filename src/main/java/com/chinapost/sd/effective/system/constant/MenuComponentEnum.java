package com.chinapost.sd.effective.system.constant;


/**
 * @author tangyang
 */
public enum MenuComponentEnum{

    /**
     * 菜单组件类型
     */
    LAYOUT(1,"Layout"),
    PARENT_VIEW(2,"ParentView"),
    INNER_LINK(3,"InnerLink");

    private final int value;
    private final String description;

    MenuComponentEnum(int value, String description) {
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
