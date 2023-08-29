package com.chinapost.sd.effective.system.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
public class SysOperationLog{

    /**
    * 日志主键
    */
    private Long id;

    /**
    * 业务类型（0其它 1新增 2修改 3删除）
    */
    private Integer businessType;

    /**
    * 请求方式
    */
    private String requestMethod;

    /**
    * 请求模块
    */
    private String title;

    /**
    * 请求URL
    */
    private String requestUrl;

    /**
    * 调用方法
    */
    private String calledMethod;

    /**
    * 操作类别（0其它 1后台用户 2手机端用户）
    */
    private Integer operatorType;

    /**
    * 用户ID
    */
    private Long userId;

    /**
    * 操作人员
    */
    private String username;

    /**
    * 操作人员ip
    */
    private String operatorIp;

    /**
    * 操作地点
    */
    private String operatorLocation;

    /**
    * 部门ID
    */
    private Long deptId;

    /**
    * 部门名称
    */
    private String deptName;

    /**
    * 请求参数
    */
    private String operationParam;

    /**
    * 返回参数
    */
    private String operationResult;

    /**
    * 操作状态（1正常 0异常）
    */
    private Integer status;

    /**
    * 错误消息
    */
    private String errorStack;

    /**
    * 操作时间
    */
    private LocalDateTime operationTime;

    /**
    * 逻辑删除
    */
    private Boolean deleted;
}
