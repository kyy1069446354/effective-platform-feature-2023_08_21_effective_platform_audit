package com.chinapost.sd.effective.system.constant;

/**
 * ClassName: AuditTypeEnum
 * Package: com.chinapost.sd.effective.system.constant
 * Description:
 *
 * @Author 康有友
 * @Create 2023/8/30 9:08
 * @Version 1.0
 */
public enum AuditTypeEnum {

    /**
     * 审核类型
     */
    VPN(0, "vpn"),
    MACHINE(1, "机器");

    private final int value;
    private final String description;

    AuditTypeEnum(int value, String description) {
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
