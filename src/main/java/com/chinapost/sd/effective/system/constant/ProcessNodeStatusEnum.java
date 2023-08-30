package com.chinapost.sd.effective.system.constant;

/**
 * ClassName: ProcessNodeStatusEnum
 * Package: com.chinapost.sd.effective.system.constant
 * Description:
 *
 * @Author 康有友
 * @Create 2023/8/30 9:15
 * @Version 1.0
 */
public enum ProcessNodeStatusEnum {
    /**
     * 流程节点状态
     */
    TO_BE_AUDITED(0, "待审核"),
    APPROVED(1, "已通过"),
    REFUSED(2, "已拒绝");

    private final int value;
    private final String description;

    ProcessNodeStatusEnum(int value, String description) {
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
