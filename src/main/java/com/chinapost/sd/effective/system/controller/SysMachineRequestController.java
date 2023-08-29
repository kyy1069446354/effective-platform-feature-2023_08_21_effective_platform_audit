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

import com.chinapost.sd.effective.system.convert.SysMachineRequestConvert;
import com.chinapost.sd.effective.system.domain.dto.SysMachineRequest;
import com.chinapost.sd.effective.system.domain.vo.SysMachineRequestVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysMachineRequestAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysMachineRequestUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysMachineRequestBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysMachineRequestDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysMachineRequestGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysMachineRequestPageQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysMachineRequestListQuery;
import com.chinapost.sd.effective.system.service.SysMachineRequestService;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;

/**
 * SysMachineRequestController类
 *
 * @author admin
 * @since 2023-08-28
 */
@RestController
@RequestMapping("/system/machineRequest")
@Tag(name = "机器审核工单表API", description = "机器审核工单表API")
public class SysMachineRequestController{

    private final SysMachineRequestConvert sysMachineRequestConvert = SysMachineRequestConvert.INSTANCE;

    @Autowired
    private SysMachineRequestService sysMachineRequestService;

    @Operation(summary = "机器审核工单表分页列表", description = "分页获取机器审核工单表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysMachineRequestVO>> page(@RequestBody SysMachineRequestPageQuery query) {
        Page<SysMachineRequest> sysMachineRequestPage = sysMachineRequestService.page(query);
        return ResponseResult.success(sysMachineRequestConvert.dtoPage2VoPage(sysMachineRequestPage));
    }

    @Operation(summary = "机器审核工单表列表", description = "获取机器审核工单表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysMachineRequestVO>> list(@RequestBody SysMachineRequestListQuery query) {
        List<SysMachineRequest> sysMachineRequestList = sysMachineRequestService.list(query);
        return ResponseResult.success(sysMachineRequestConvert.dtoList2VoList(sysMachineRequestList));
    }

    @Operation(summary = "机器审核工单表信息", description = "机器审核工单表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysMachineRequestVO> get(@RequestBody SysMachineRequestGetQuery query) {
        SysMachineRequest sysMachineRequest = sysMachineRequestService.getById(query.getId());
        return ResponseResult.success(sysMachineRequestConvert.dto2Vo(sysMachineRequest));
    }

    @Operation(summary = "机器审核工单表新增", description = "机器审核工单表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysMachineRequestAddCommand command) {
        SysMachineRequest sysMachineRequest = sysMachineRequestConvert.convert(command);
        sysMachineRequestService.add(sysMachineRequest);
        return ResponseResult.success();
    }

    @Operation(summary = "机器审核工单表更新", description = "机器审核工单表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysMachineRequestUpdateCommand command) {
        SysMachineRequest sysMachineRequest = sysMachineRequestConvert.convert(command);
        sysMachineRequestService.update(sysMachineRequest);
        return ResponseResult.success();
    }

    @Operation(summary = "机器审核工单表删除", description = "机器审核工单表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysMachineRequestDeleteCommand command) {
        sysMachineRequestService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "机器审核工单表批量删除", description = "机器审核工单表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysMachineRequestBatchDeleteCommand command) {
        sysMachineRequestService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
