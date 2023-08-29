package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysPostConvert;
import com.chinapost.sd.effective.system.domain.dto.SysPost;
import com.chinapost.sd.effective.system.domain.vo.SysPostVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysPostAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysPostBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysPostDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysPostUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysPostGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysPostListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysPostPageQuery;
import com.chinapost.sd.effective.system.service.SysPostService;
import com.chinapost.sd.boot.infrastructure.annotations.AccessLog;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.constant.BusinessTypeEnum;
import com.chinapost.sd.boot.infrastructure.utils.poi.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * SysPostController类
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/post")
@Tag(name = "岗位信息表API", description = "岗位信息表API")
public class SysPostController{

    private final SysPostConvert sysPostConvert = SysPostConvert.INSTANCE;

    @Autowired
    private SysPostService sysPostService;

    @Operation(summary = "岗位信息表分页列表", description = "分页获取岗位信息表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysPostVO>> page(@RequestBody SysPostPageQuery query) {
        Page<SysPost> sysPostPage = sysPostService.page(query);
        return ResponseResult.success(sysPostConvert.dtoPage2VoPage(sysPostPage));
    }

    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.EXPORT)
    @Operation(summary = "岗位信息表导出", description = "岗位信息表导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysPostListQuery query){
        List<SysPost> list = sysPostService.list(query);
        List<SysPostVO> voList = sysPostConvert.dtoList2VoList(list);
        ExcelUtil<SysPostVO> util = new ExcelUtil<>(SysPostVO.class);
        util.exportExcel(response, voList, "岗位数据");
    }

    @Operation(summary = "岗位信息表列表", description = "获取岗位信息表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysPostVO>> list(@RequestBody SysPostListQuery query) {
        List<SysPost> sysPostList = sysPostService.list(query);
        return ResponseResult.success(sysPostConvert.dtoList2VoList(sysPostList));
    }

    @Operation(summary = "岗位信息表信息", description = "岗位信息表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysPostVO> get(@RequestBody SysPostGetQuery query) {
        SysPost sysPost = sysPostService.getById(query.getId());
        return ResponseResult.success(sysPostConvert.dto2Vo(sysPost));
    }

    @Operation(summary = "岗位信息表新增", description = "岗位信息表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysPostAddCommand command) {
        SysPost sysPost = sysPostConvert.convert(command);
        sysPostService.add(sysPost);
        return ResponseResult.success();
    }

    @Operation(summary = "岗位信息表更新", description = "岗位信息表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysPostUpdateCommand command) {
        SysPost sysPost = sysPostConvert.convert(command);
        sysPostService.update(sysPost);
        return ResponseResult.success();
    }

    @Operation(summary = "岗位信息表删除", description = "岗位信息表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysPostDeleteCommand command) {
        sysPostService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "岗位信息表批量删除", description = "岗位信息表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysPostBatchDeleteCommand command) {
        sysPostService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
