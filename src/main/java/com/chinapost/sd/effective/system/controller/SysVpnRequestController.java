package com.chinapost.sd.effective.system.controller;

import com.chinapost.sd.effective.system.convert.SysVpnRequestConvert;
import com.chinapost.sd.effective.system.domain.dto.SysVpnRequest;
import com.chinapost.sd.effective.system.domain.vo.SysVpnRequestVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysVpnRequestAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysVpnRequestBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysVpnRequestDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysVpnRequestUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysVpnRequestGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysVpnRequestListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysVpnRequestPageQuery;
import com.chinapost.sd.effective.system.service.SysVpnRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;

import java.util.List;

/**
 * SysVpnRequestController类
 *
 * @author admin
 * @since 2023-08-28
 */
@RestController
@RequestMapping("/system/vpnRequest")
@Tag(name = "vpn审核工单表API", description = "vpn审核工单表API")
public class SysVpnRequestController{

    private final SysVpnRequestConvert sysVpnRequestConvert = SysVpnRequestConvert.INSTANCE;

    @Autowired
    private SysVpnRequestService sysVpnRequestService;

    @Operation(summary = "vpn审核工单表分页列表", description = "分页获取vpn审核工单表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysVpnRequestVO>> page(@RequestBody SysVpnRequestPageQuery query) {
        Page<SysVpnRequest> sysVpnRequestPage = sysVpnRequestService.page(query);
        return ResponseResult.success(sysVpnRequestConvert.dtoPage2VoPage(sysVpnRequestPage));
    }

    @Operation(summary = "vpn审核工单表列表", description = "获取vpn审核工单表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysVpnRequestVO>> list(@RequestBody SysVpnRequestListQuery query) {
        List<SysVpnRequest> sysVpnRequestList = sysVpnRequestService.list(query);
        return ResponseResult.success(sysVpnRequestConvert.dtoList2VoList(sysVpnRequestList));
    }

    @Operation(summary = "vpn审核工单表信息", description = "vpn审核工单表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysVpnRequestVO> get(@RequestBody SysVpnRequestGetQuery query) {
        SysVpnRequest sysVpnRequest = sysVpnRequestService.getById(query.getId());
        return ResponseResult.success(sysVpnRequestConvert.dto2Vo(sysVpnRequest));
    }

    @Operation(summary = "vpn审核工单表新增", description = "vpn审核工单表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysVpnRequestAddCommand command) {
        SysVpnRequest sysVpnRequest = sysVpnRequestConvert.convert(command);
        sysVpnRequestService.add(sysVpnRequest);
        return ResponseResult.success();
    }

    @Operation(summary = "vpn审核工单表更新", description = "vpn审核工单表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysVpnRequestUpdateCommand command) {
        SysVpnRequest sysVpnRequest = sysVpnRequestConvert.convert(command);
        sysVpnRequestService.update(sysVpnRequest);
        return ResponseResult.success();
    }

    @Operation(summary = "vpn审核工单表删除", description = "vpn审核工单表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysVpnRequestDeleteCommand command) {
        sysVpnRequestService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "vpn审核工单表批量删除", description = "vpn审核工单表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysVpnRequestBatchDeleteCommand command) {
        sysVpnRequestService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
