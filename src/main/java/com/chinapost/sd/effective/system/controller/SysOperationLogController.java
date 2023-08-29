package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysOperationLogConvert;
import com.chinapost.sd.effective.system.domain.dto.SysOperationLog;
import com.chinapost.sd.effective.system.domain.vo.SysOperationLogVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysOperationLogAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysOperationLogBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysOperationLogDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysOperationLogUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysOperationLogGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysOperationLogListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysOperationLogPageQuery;
import com.chinapost.sd.effective.system.service.SysOperationLogService;
import com.chinapost.sd.boot.infrastructure.annotations.AccessLog;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.constant.BusinessTypeEnum;
import com.chinapost.sd.boot.infrastructure.utils.poi.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * SysOperationLogController类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/operationLog")
@Tag(name = "操作日志记录API", description = "操作日志记录API")
public class SysOperationLogController{

    private final SysOperationLogConvert sysOperationLogConvert = SysOperationLogConvert.INSTANCE;

    @Autowired
    private SysOperationLogService sysOperationLogService;

    @Operation(summary = "操作日志记录分页列表", description = "分页获取操作日志记录列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysOperationLogVO>> page(@RequestBody SysOperationLogPageQuery query) {
        Page<SysOperationLog> sysOperationLogPage = sysOperationLogService.page(query);
        return ResponseResult.success(sysOperationLogConvert.dtoPage2VoPage(sysOperationLogPage));
    }

    @Operation(summary = "操作日志记录列表", description = "获取操作日志记录列表")
    @PostMapping("/list")
    public ResponseResult<List<SysOperationLogVO>> list(@RequestBody SysOperationLogListQuery query) {
        List<SysOperationLog> sysOperationLogList = sysOperationLogService.list(query);
        return ResponseResult.success(sysOperationLogConvert.dtoList2VoList(sysOperationLogList));
    }

    @AccessLog(title = "操作日志管理", businessType = BusinessTypeEnum.EXPORT)
    @Operation(summary = "操作日志导出", description = "操作日志导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOperationLogListQuery query){
        List<SysOperationLog> list = sysOperationLogService.list(query);
        List<SysOperationLogVO> voList = sysOperationLogConvert.dtoList2VoList(list);
        ExcelUtil<SysOperationLogVO> util = new ExcelUtil<>(SysOperationLogVO.class);
        util.exportExcel(response, voList, "操作日志数据");
    }

    @Operation(summary = "操作日志记录信息", description = "操作日志记录的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysOperationLogVO> get(@RequestBody SysOperationLogGetQuery query) {
        SysOperationLog sysOperationLog = sysOperationLogService.getById(query.getId());
        return ResponseResult.success(sysOperationLogConvert.dto2Vo(sysOperationLog));
    }

    @Operation(summary = "操作日志记录新增", description = "操作日志记录新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysOperationLogAddCommand command) {
        SysOperationLog sysOperationLog = sysOperationLogConvert.convert(command);
        sysOperationLogService.add(sysOperationLog);
        return ResponseResult.success();
    }

    @Operation(summary = "操作日志记录更新", description = "操作日志记录更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysOperationLogUpdateCommand command) {
        SysOperationLog sysOperationLog = sysOperationLogConvert.convert(command);
        sysOperationLogService.update(sysOperationLog);
        return ResponseResult.success();
    }

    @Operation(summary = "操作日志记录删除", description = "操作日志记录删除")
    @DeleteMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysOperationLogDeleteCommand command) {
        sysOperationLogService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "操作日志记录批量删除", description = "操作日志记录批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysOperationLogBatchDeleteCommand command) {
        sysOperationLogService.batchDelete(command.getIds());
        return ResponseResult.success();
    }


    @Operation(summary = "系统访问记录清空", description = "系统访问记录清空")
    @PostMapping("/clear")
    public ResponseResult<Void> clear() {
        sysOperationLogService.clear();
        return ResponseResult.success();
    }




}
