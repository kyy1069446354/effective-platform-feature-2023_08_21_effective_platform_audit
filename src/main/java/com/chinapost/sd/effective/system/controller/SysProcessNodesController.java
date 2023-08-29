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

import com.chinapost.sd.effective.system.convert.SysProcessNodesConvert;
import com.chinapost.sd.effective.system.domain.dto.SysProcessNodes;
import com.chinapost.sd.effective.system.domain.vo.SysProcessNodesVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysProcessNodesAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysProcessNodesUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysProcessNodesBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysProcessNodesDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysProcessNodesGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysProcessNodesPageQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysProcessNodesListQuery;
import com.chinapost.sd.effective.system.service.SysProcessNodesService;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;

/**
 * SysProcessNodesController类
 *
 * @author admin
 * @since 2023-08-28
 */
@RestController
@RequestMapping("/system/processNodes")
@Tag(name = "流程节点表API", description = "流程节点表API")
public class SysProcessNodesController{

    private final SysProcessNodesConvert sysProcessNodesConvert = SysProcessNodesConvert.INSTANCE;

    @Autowired
    private SysProcessNodesService sysProcessNodesService;

    @Operation(summary = "流程节点表分页列表", description = "分页获取流程节点表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysProcessNodesVO>> page(@RequestBody SysProcessNodesPageQuery query) {
        Page<SysProcessNodes> sysProcessNodesPage = sysProcessNodesService.page(query);
        return ResponseResult.success(sysProcessNodesConvert.dtoPage2VoPage(sysProcessNodesPage));
    }

    @Operation(summary = "流程节点表列表", description = "获取流程节点表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysProcessNodesVO>> list(@RequestBody SysProcessNodesListQuery query) {
        List<SysProcessNodes> sysProcessNodesList = sysProcessNodesService.list(query);
        return ResponseResult.success(sysProcessNodesConvert.dtoList2VoList(sysProcessNodesList));
    }

    @Operation(summary = "流程节点表信息", description = "流程节点表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysProcessNodesVO> get(@RequestBody SysProcessNodesGetQuery query) {
        SysProcessNodes sysProcessNodes = sysProcessNodesService.getById(query.getId());
        return ResponseResult.success(sysProcessNodesConvert.dto2Vo(sysProcessNodes));
    }

    @Operation(summary = "流程节点表新增", description = "流程节点表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysProcessNodesAddCommand command) {
        SysProcessNodes sysProcessNodes = sysProcessNodesConvert.convert(command);
        sysProcessNodesService.add(sysProcessNodes);
        return ResponseResult.success();
    }

    @Operation(summary = "流程节点表更新", description = "流程节点表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysProcessNodesUpdateCommand command) {
        SysProcessNodes sysProcessNodes = sysProcessNodesConvert.convert(command);
        sysProcessNodesService.update(sysProcessNodes);
        return ResponseResult.success();
    }

    @Operation(summary = "流程节点表删除", description = "流程节点表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysProcessNodesDeleteCommand command) {
        sysProcessNodesService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "流程节点表批量删除", description = "流程节点表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysProcessNodesBatchDeleteCommand command) {
        sysProcessNodesService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
