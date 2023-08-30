package com.chinapost.sd.effective.system.constant;

/**
 * ClassName: AuditRuleStatusEnum
 * Package: com.chinapost.sd.effective.system.constant
 * Description:
 *
 * @Author 康有友
 * @Create 2023/8/30 9:10
 * @Version 1.0
 */
public enum AuditRuleStatusEnum {

    /**
     * 审核规则状态
     */
    OFF(0, "停用"),
    ON(1, "启用");

    private final int value;
    private final String description;

    AuditRuleStatusEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
