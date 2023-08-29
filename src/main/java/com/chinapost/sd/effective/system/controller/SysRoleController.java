package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysRoleConvert;
import com.chinapost.sd.effective.system.domain.dto.SysRole;
import com.chinapost.sd.effective.system.domain.vo.SysRoleVO;
import com.chinapost.sd.effective.system.domain.vo.command.*;
import com.chinapost.sd.effective.system.domain.vo.query.*;
import com.chinapost.sd.effective.system.mapper.SysUserRoleMapper;
import com.chinapost.sd.effective.system.service.SysRoleService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * SysRoleController类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/role")
@Tag(name = "角色信息表API", description = "角色信息表API")
public class SysRoleController{

    private final SysRoleConvert sysRoleConvert = SysRoleConvert.INSTANCE;

    @Autowired
    private SysRoleService sysRoleService;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Operation(summary = "角色信息表分页列表", description = "分页获取角色信息表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysRoleVO>> page(@RequestBody SysRolePageQuery query) {
        Page<SysRole> sysRolePage = sysRoleService.page(query);
        return ResponseResult.success(sysRoleConvert.dtoPage2VoPage(sysRolePage));
    }

    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.EXPORT)
    @Operation(summary = "角色信息表导出", description = "角色信息表导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRoleListQuery query){
        List<SysRole> list = sysRoleService.list(query);
        List<SysRoleVO> voList = sysRoleConvert.dtoList2VoList(list);
        ExcelUtil<SysRoleVO> util = new ExcelUtil<>(SysRoleVO.class);
        util.exportExcel(response, voList, "角色数据");
    }

    @Operation(summary = "角色信息表列表", description = "获取角色信息表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysRoleVO>> list(@RequestBody SysRoleListQuery query) {
        List<SysRole> sysRoleList = sysRoleService.list(query);
        return ResponseResult.success(sysRoleConvert.dtoList2VoList(sysRoleList));
    }

    @Operation(summary = "角色信息表信息", description = "角色信息表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysRoleVO> get(@RequestBody SysRoleGetQuery query) {
        SysRole sysRole = sysRoleService.getById(query.getId());
        return ResponseResult.success(sysRoleConvert.dto2Vo(sysRole));
    }

    @Operation(summary = "角色信息表新增", description = "角色信息表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysRoleAddCommand command) {
        SysRole sysRole = sysRoleConvert.convert(command);
        sysRoleService.add(sysRole);
        return ResponseResult.success();
    }

    @Operation(summary = "角色信息表更新", description = "角色信息表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysRoleUpdateCommand command) {
        SysRole sysRole = sysRoleConvert.convert(command);
        sysRoleService.update(sysRole);
        return ResponseResult.success();
    }

    @Operation(summary = "角色信息表删除", description = "角色信息表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysRoleDeleteCommand command) {
        sysRoleService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "角色信息表批量删除", description = "角色信息表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysRoleBatchDeleteCommand command) {
        sysRoleService.batchDelete(command.getIds());
        return ResponseResult.success();
    }


    /**
     * 查询已分配用户角色列表
     */
    @Operation(summary = "已关联该角色的用户列表")
    @PostMapping("/pageGranted")
    public ResponseResult<Page<GrantedSysUserVO>> pageGranted(@RequestBody SysRolePageGrantedQuery query) {
        return ResponseResult.success(sysUserRoleMapper.selectByPageGrantedUser(
                Page.of(query.getPageNum(), query.getPageSize()), query));
    }

    /**
     * 查询未分配用户角色列表
     */
    @Operation(summary = "未关联该角色的用户列表")
    @PostMapping("/pageUnGranted")
    public ResponseResult<Page<GrantedSysUserVO>> unallocatedUserList(@RequestBody SysRolePageGrantedQuery query) {
        return ResponseResult.success(sysUserRoleMapper.selectByPageUnGrantedUser(
                Page.of(query.getPageNum(), query.getPageSize()), query));
    }


    /**
     * 批量取消授权用户
     */
    @Operation(summary = "批量解除角色和用户的关联")
    @PostMapping("/cancelGrant")
    public ResponseResult<Void> cancelGrant(@RequestBody SysRoleCancelGrantCommand command) {
        sysRoleService.cancelGrant(command.getRoleId(), command.getUserIds());
        return ResponseResult.success();
    }

    /**
     * 批量选择用户授权
     */
    @Operation(summary = "批量添加用户和角色关联")
    @PostMapping("/grant")
    public ResponseResult<Void> grant(@RequestBody SysRoleGrantCommand sysRoleGrantCommand) {
        sysRoleService.grant(sysRoleGrantCommand.getRoleId(), sysRoleGrantCommand.getUserIds());
        return ResponseResult.success();
    }

    @Operation(summary = "修改用户状态")
    @PostMapping("/changeStatus")
    public ResponseResult<Void> changeStatus(@RequestBody SysRoleChangeStatusCommand command) {
        sysRoleService.changeUserStatus(command.getRoleId(), command.getStatus());
        return ResponseResult.success();
    }

    /**
     * 修改保存数据权限
     */
    @Operation(summary = "修改角色数据权限")
    @PostMapping("/changeDataScope")
    public ResponseResult<Void> dataScope(@RequestBody SysRoleChangeDataScopeCommand command) {
        sysRoleService.changeDataScope(command.getId(), command.getDataScope(), command.getDeptIds());
        return ResponseResult.success();
    }
}
