package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysLoginInfoConvert;
import com.chinapost.sd.effective.system.domain.dto.SysLoginInfo;
import com.chinapost.sd.effective.system.domain.vo.SysLoginInfoVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysLoginInfoBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysLoginInfoPageQuery;
import com.chinapost.sd.effective.system.service.SysLoginInfoService;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * SysLoginInfoController类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/logininfo")
@Tag(name = "系统访问记录API", description = "系统访问记录API")
public class SysLoginInfoController{

    private final SysLoginInfoConvert sysLoginInfoConvert = SysLoginInfoConvert.INSTANCE;

    @Autowired
    private SysLoginInfoService sysLoginInfoService;

    @Operation(summary = "系统访问记录分页列表", description = "分页获取系统访问记录列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysLoginInfoVO>> page(@RequestBody SysLoginInfoPageQuery query) {
        Page<SysLoginInfo> sysLoginInfoPage = sysLoginInfoService.page(query);
        return ResponseResult.success(sysLoginInfoConvert.dtoPage2VoPage(sysLoginInfoPage));
    }

    @Operation(summary = "系统访问记录批量删除", description = "系统访问记录批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysLoginInfoBatchDeleteCommand command) {
        sysLoginInfoService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

    @Operation(summary = "系统访问记录清空", description = "系统访问记录清空")
    @PostMapping("/clear")
    public ResponseResult<Void> clear() {
        sysLoginInfoService.clear();
        return ResponseResult.success();
    }


}
