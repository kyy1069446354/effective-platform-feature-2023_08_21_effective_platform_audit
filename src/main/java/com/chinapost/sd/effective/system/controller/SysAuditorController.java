package com.chinapost.sd.effective.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.convert.SysAuditorConvert;
import com.chinapost.sd.effective.system.domain.dto.SysAuditor;
import com.chinapost.sd.effective.system.domain.vo.SysAuditorVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysAuditorAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysAuditorUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysAuditorBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysAuditorDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysAuditorGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysAuditorPageQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysAuditorListQuery;
import com.chinapost.sd.effective.system.service.SysAuditorService;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;

/**
 * SysAuditorController类
 *
 * @author admin
 * @since 2023-08-29
 */
@RestController
@RequestMapping("/system/auditor")
@Tag(name = "审核员表API", description = "审核员表API")
public class SysAuditorController{

    private final SysAuditorConvert sysAuditorConvert = SysAuditorConvert.INSTANCE;

    @Autowired
    private SysAuditorService sysAuditorService;

    @Operation(summary = "审核员表分页列表", description = "分页获取审核员表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysAuditorVO>> page(@RequestBody SysAuditorPageQuery query) {
        Page<SysAuditor> sysAuditorPage = sysAuditorService.page(query);
        return ResponseResult.success(sysAuditorConvert.dtoPage2VoPage(sysAuditorPage));
    }

    @Operation(summary = "审核员表列表", description = "获取审核员表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysAuditorVO>> list(@RequestBody SysAuditorListQuery query) {
        List<SysAuditor> sysAuditorList = sysAuditorService.list(query);
        return ResponseResult.success(sysAuditorConvert.dtoList2VoList(sysAuditorList));
    }

    @Operation(summary = "审核员表信息", description = "审核员表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysAuditorVO> get(@RequestBody SysAuditorGetQuery query) {
        SysAuditor sysAuditor = sysAuditorService.getById(query.getId());
        return ResponseResult.success(sysAuditorConvert.dto2Vo(sysAuditor));
    }

    @Operation(summary = "审核员表新增", description = "审核员表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysAuditorAddCommand command) {
        SysAuditor sysAuditor = sysAuditorConvert.convert(command);
        sysAuditorService.add(sysAuditor);
        return ResponseResult.success();
    }

    @Operation(summary = "审核员表更新", description = "审核员表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysAuditorUpdateCommand command) {
        SysAuditor sysAuditor = sysAuditorConvert.convert(command);
        sysAuditorService.update(sysAuditor);
        return ResponseResult.success();
    }

    @Operation(summary = "审核员表删除", description = "审核员表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysAuditorDeleteCommand command) {
        sysAuditorService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "审核员表批量删除", description = "审核员表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysAuditorBatchDeleteCommand command) {
        sysAuditorService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
