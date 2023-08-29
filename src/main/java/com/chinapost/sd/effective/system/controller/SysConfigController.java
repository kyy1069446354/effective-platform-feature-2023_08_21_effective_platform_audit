package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysConfigConvert;
import com.chinapost.sd.effective.system.domain.dto.SysConfig;
import com.chinapost.sd.effective.system.domain.vo.SysConfigVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysConfigAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysConfigBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysConfigDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysConfigUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysConfigGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysConfigPageQuery;
import com.chinapost.sd.effective.system.service.SysConfigService;
import com.chinapost.sd.boot.infrastructure.annotations.AccessLog;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.constant.BusinessTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysConfigController类
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/config")
@Tag(name = "参数配置表API", description = "参数配置表API")
public class SysConfigController{

    private final SysConfigConvert sysConfigConvert = SysConfigConvert.INSTANCE;

    @Autowired
    private SysConfigService sysConfigService;

    @PreAuthorize("@ss.hasPermission('system:config:list')")
    @Operation(summary = "参数配置表分页列表", description = "分页获取参数配置表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysConfigVO>> page(@RequestBody SysConfigPageQuery query) {
        Page<SysConfig> sysConfigPage = sysConfigService.page(query);
        return ResponseResult.success(sysConfigConvert.dtoPage2VoPage(sysConfigPage));
    }


    @PreAuthorize("@ss.hasPermission('system:config:query')")
    @Operation(summary = "参数配置表信息", description = "参数配置表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysConfigVO> get(@RequestBody SysConfigGetQuery query) {
        SysConfig sysConfig = sysConfigService.getById(query.getId());
        return ResponseResult.success(sysConfigConvert.dto2Vo(sysConfig));
    }

    @PreAuthorize("@ss.hasPermission('system:config:add')")
    @AccessLog(title = "参数管理", businessType = BusinessTypeEnum.ADD)
    @Operation(summary = "参数配置表新增", description = "参数配置表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysConfigAddCommand command) {
        SysConfig sysConfig = sysConfigConvert.convert(command);
        sysConfigService.add(sysConfig);
        return ResponseResult.success();
    }

    @PreAuthorize("@ss.hasPermission('system:config:edit')")
    @AccessLog(title = "参数管理", businessType = BusinessTypeEnum.MODIFY)
    @Operation(summary = "参数配置表更新", description = "参数配置表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysConfigUpdateCommand command) {
        SysConfig sysConfig = sysConfigConvert.convert(command);
        sysConfigService.update(sysConfig);
        return ResponseResult.success();
    }

    @PreAuthorize("@ss.hasPermission('system:config:remove')")
    @AccessLog(title = "参数管理", businessType = BusinessTypeEnum.DELETE)
    @Operation(summary = "参数配置表删除", description = "参数配置表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysConfigDeleteCommand command) {
        sysConfigService.delete(command.getId());
        return ResponseResult.success();
    }

    @PreAuthorize("@ss.hasPermission('system:config:remove')")
    @AccessLog(title = "参数管理", businessType = BusinessTypeEnum.DELETE)
    @Operation(summary = "参数配置表批量删除", description = "参数配置表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysConfigBatchDeleteCommand command) {
        sysConfigService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
