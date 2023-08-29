package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志记录PageQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "操作日志记录PageQuery")
public class SysOperationLogPageQuery extends BasePageReq {

    /**
    * 日志主键
    */
    @Schema(description = "日志主键")
    private Long id;

    /**
    * 业务类型（0其它 1新增 2修改 3删除）
    */
    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    private Integer businessType;

    /**
    * 请求方式
    */
    @Schema(description = "请求方式")
    private Integer requestMethod;

    /**
    * 请求模块
    */
    @Schema(description = "请求模块")
    private String title;

    /**
    * 请求URL
    */
    @Schema(description = "请求URL")
    private String requestUrl;

    /**
    * 调用方法
    */
    @Schema(description = "调用方法")
    private String calledMethod;

    /**
    * 操作类别（0其它 1后台用户 2手机端用户）
    */
    @Schema(description = "操作类别（0其它 1后台用户 2手机端用户）")
    private Integer operatorType;

    /**
    * 用户ID
    */
    @Schema(description = "用户ID")
    private Long userId;

    /**
    * 操作人员
    */
    @Schema(description = "操作人员")
    private String username;

    /**
    * 操作人员ip
    */
    @Schema(description = "操作人员ip")
    private String operatorIp;

    /**
    * 操作地点
    */
    @Schema(description = "操作地点")
    private String operatorLocation;

    /**
    * 部门ID
    */
    @Schema(description = "部门ID")
    private Long deptId;

    /**
    * 部门名称
    */
    @Schema(description = "部门名称")
    private String deptName;

    /**
    * 请求参数
    */
    @Schema(description = "请求参数")
    private String operationParam;

    /**
    * 返回参数
    */
    @Schema(description = "返回参数")
    private String operationResult;

    /**
    * 操作状态（1正常 0异常）
    */
    @Schema(description = "操作状态（1正常 0异常）")
    private Integer status;

    /**
    * 错误消息
    */
    @Schema(description = "错误消息")
    private String errorStack;

    /**
    * 操作时间
    */
    @Schema(description = "操作时间")
    private LocalDateTime operationTime;

    /**
    * 逻辑删除
    */
    @Schema(description = "逻辑删除")
    private Boolean deleted;
}
