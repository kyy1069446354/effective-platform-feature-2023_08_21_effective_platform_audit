package com.chinapost.sd.effective.system.constant;

/**
 * ClassName: AuditResultEnum
 * Package: com.chinapost.sd.effective.system.constant
 * Description:
 *
 * @Author 康有友
 * @Create 2023/8/30 9:12
 * @Version 1.0
 */
public enum AuditResultEnum {
    /**
     * 审核结果
     */
    AUDITING(0, "审核中"),
    APPROVED(1, "已通过"),
    REFUSED(2, "已拒绝"),
    DROPED(3, "已撤销");

    private final int value;
    private final String description;

    AuditResultEnum(int value, String description) {
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
