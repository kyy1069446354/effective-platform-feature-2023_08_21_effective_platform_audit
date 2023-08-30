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

import com.chinapost.sd.effective.system.convert.AuditRuleConvert;
import com.chinapost.sd.effective.system.domain.dto.AuditRule;
import com.chinapost.sd.effective.system.domain.vo.AuditRuleVO;
import com.chinapost.sd.effective.system.domain.vo.command.AuditRuleAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.AuditRuleUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.command.AuditRuleBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.AuditRuleDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.query.AuditRuleGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.AuditRulePageQuery;
import com.chinapost.sd.effective.system.domain.vo.query.AuditRuleListQuery;
import com.chinapost.sd.effective.system.service.AuditRuleService;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;

/**
 * AuditRuleController类
 *
 * @author admin
 * @since 2023-08-30
 */
@RestController
@RequestMapping("/resource/auditrule")
@Tag(name = "审核规则表API", description = "审核规则表API")
public class AuditRuleController{

    private final AuditRuleConvert auditRuleConvert = AuditRuleConvert.INSTANCE;

    @Autowired
    private AuditRuleService auditRuleService;

    @Operation(summary = "审核规则表分页列表", description = "分页获取审核规则表列表")
    @PostMapping("/page")
    public ResponseResult<Page<AuditRuleVO>> page(@RequestBody AuditRulePageQuery query) {
        Page<AuditRule> auditRulePage = auditRuleService.page(query);
        return ResponseResult.success(auditRuleConvert.dtoPage2VoPage(auditRulePage));
    }

    @Operation(summary = "审核规则表列表", description = "获取审核规则表列表")
    @PostMapping("/list")
    public ResponseResult<List<AuditRuleVO>> list(@RequestBody AuditRuleListQuery query) {
        List<AuditRule> auditRuleList = auditRuleService.list(query);
        return ResponseResult.success(auditRuleConvert.dtoList2VoList(auditRuleList));
    }

    @Operation(summary = "审核规则表信息", description = "审核规则表的详细信息")
    @PostMapping("/get")
    public ResponseResult<AuditRuleVO> get(@RequestBody AuditRuleGetQuery query) {
        AuditRule auditRule = auditRuleService.getById(query.getId());
        return ResponseResult.success(auditRuleConvert.dto2Vo(auditRule));
    }

    @Operation(summary = "审核规则表新增", description = "审核规则表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody AuditRuleAddCommand command) {
        AuditRule auditRule = auditRuleConvert.convert(command);
        auditRuleService.add(auditRule);
        return ResponseResult.success();
    }

    @Operation(summary = "审核规则表更新", description = "审核规则表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody AuditRuleUpdateCommand command) {
        AuditRule auditRule = auditRuleConvert.convert(command);
        auditRuleService.update(auditRule);
        return ResponseResult.success();
    }

    @Operation(summary = "审核规则表删除", description = "审核规则表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody AuditRuleDeleteCommand command) {
        auditRuleService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "审核规则表批量删除", description = "审核规则表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody AuditRuleBatchDeleteCommand command) {
        auditRuleService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
