package com.chinapost.sd.effective.system.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chinapost.sd.boot.infrastructure.base.BasePO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@TableName("sys_operation_log")
@Schema(description = "操作日志记录")
public class SysOperationLogPO extends BasePO {

    /**
    * 日志主键
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 业务类型（0其它 1新增 2修改 3删除）
    */
    @TableField("business_type")
    private Integer businessType;

    /**
    * 请求方式
    */
    @TableField("request_method")
    private String requestMethod;

    /**
    * 模块标题
    */
    @TableField("title")
    private String title;

    /**
    * 请求URL
    */
    @TableField("request_url")
    private String requestUrl;

    /**
    * 调用方法
    */
    @TableField("called_method")
    private String calledMethod;

    /**
    * 操作类别（0其它 1后台用户 2手机端用户）
    */
    @TableField("operator_type")
    private Integer operatorType;

    /**
    * 用户ID
    */
    @TableField("user_id")
    private Long userId;

    /**
    * 操作人员
    */
    @TableField("username")
    private String username;

    /**
    * 操作人员ip
    */
    @TableField("operator_ip")
    private String operatorIp;

    /**
    * 操作地点
    */
    @TableField("operator_location")
    private String operatorLocation;

    /**
    * 部门ID
    */
    @TableField("dept_id")
    private Long deptId;

    /**
    * 部门名称
    */
    @TableField("dept_name")
    private String deptName;

    /**
    * 请求参数
    */
    @TableField("operation_param")
    private String operationParam;

    /**
    * 返回参数
    */
    @TableField("operation_result")
    private String operationResult;

    /**
    * 操作状态（1正常 0异常）
    */
    @TableField("status")
    private Integer status;

    /**
    * 错误消息
    */
    @TableField("error_stack")
    private String errorStack;

    /**
    * 操作时间
    */
    @TableField("operation_time")
    private LocalDateTime operationTime;

    /**
    * 逻辑删除
    */
    @TableField("deleted")
    private Boolean deleted;
}
