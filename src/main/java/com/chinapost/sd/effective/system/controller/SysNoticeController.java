package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysNoticeConvert;
import com.chinapost.sd.effective.system.domain.dto.SysNotice;
import com.chinapost.sd.effective.system.domain.vo.SysNoticeVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysNoticeAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysNoticeBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysNoticeDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysNoticeUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysNoticeGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysNoticePageQuery;
import com.chinapost.sd.effective.system.service.SysNoticeService;
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
 * SysNoticeController类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/notice")
@Tag(name = "通知公告表API", description = "通知公告表API")
public class SysNoticeController{

    private final SysNoticeConvert sysNoticeConvert = SysNoticeConvert.INSTANCE;

    @Autowired
    private SysNoticeService sysNoticeService;

    @Operation(summary = "通知公告表分页列表", description = "分页获取通知公告表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysNoticeVO>> page(@RequestBody SysNoticePageQuery query) {
        Page<SysNotice> sysNoticePage = sysNoticeService.page(query);
        return ResponseResult.success(sysNoticeConvert.dtoPage2VoPage(sysNoticePage));
    }

    @Operation(summary = "通知公告表信息", description = "通知公告表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysNoticeVO> get(@RequestBody SysNoticeGetQuery query) {
        SysNotice sysNotice = sysNoticeService.getById(query.getId());
        return ResponseResult.success(sysNoticeConvert.dto2Vo(sysNotice));
    }

    @Operation(summary = "通知公告表新增", description = "通知公告表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysNoticeAddCommand command) {
        SysNotice sysNotice = sysNoticeConvert.convert(command);
        sysNoticeService.add(sysNotice);
        return ResponseResult.success();
    }

    @Operation(summary = "通知公告表更新", description = "通知公告表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysNoticeUpdateCommand command) {
        SysNotice sysNotice = sysNoticeConvert.convert(command);
        sysNoticeService.update(sysNotice);
        return ResponseResult.success();
    }

    @Operation(summary = "通知公告表删除", description = "通知公告表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysNoticeDeleteCommand command) {
        sysNoticeService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "通知公告表批量删除", description = "通知公告表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysNoticeBatchDeleteCommand command) {
        sysNoticeService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
