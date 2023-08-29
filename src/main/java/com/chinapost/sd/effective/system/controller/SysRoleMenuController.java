package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysRoleMenuConvert;
import com.chinapost.sd.effective.system.domain.dto.SysRoleMenu;
import com.chinapost.sd.effective.system.domain.vo.SysRoleMenuVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysRoleMenuAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysRoleMenuBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysRoleMenuDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysRoleMenuUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleMenuGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleMenuListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleMenuPageQuery;
import com.chinapost.sd.effective.system.service.SysRoleMenuService;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * SysRoleMenuController类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/rolemenu")
@Tag(name = "角色和菜单关联表API", description = "角色和菜单关联表API")
public class SysRoleMenuController{

    private final SysRoleMenuConvert sysRoleMenuConvert = SysRoleMenuConvert.INSTANCE;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Operation(summary = "角色和菜单关联表分页列表", description = "分页获取角色和菜单关联表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysRoleMenuVO>> page(@RequestBody SysRoleMenuPageQuery query) {
        Page<SysRoleMenu> sysRoleMenuPage = sysRoleMenuService.page(query);
        return ResponseResult.success(sysRoleMenuConvert.dtoPage2VoPage(sysRoleMenuPage));
    }

    @Operation(summary = "角色和菜单关联表列表", description = "获取角色和菜单关联表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysRoleMenuVO>> list(@RequestBody SysRoleMenuListQuery query) {
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list(query);
        return ResponseResult.success(sysRoleMenuConvert.dtoList2VoList(sysRoleMenuList));
    }

    @Operation(summary = "角色和菜单关联表信息", description = "角色和菜单关联表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysRoleMenuVO> get(@RequestBody SysRoleMenuGetQuery query) {
        SysRoleMenu sysRoleMenu = sysRoleMenuService.getById(query.getId());
        return ResponseResult.success(sysRoleMenuConvert.dto2Vo(sysRoleMenu));
    }

    @Operation(summary = "角色和菜单关联表新增", description = "角色和菜单关联表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysRoleMenuAddCommand command) {
        SysRoleMenu sysRoleMenu = sysRoleMenuConvert.convert(command);
        sysRoleMenuService.add(sysRoleMenu);
        return ResponseResult.success();
    }

    @Operation(summary = "角色和菜单关联表更新", description = "角色和菜单关联表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysRoleMenuUpdateCommand command) {
        SysRoleMenu sysRoleMenu = sysRoleMenuConvert.convert(command);
        sysRoleMenuService.update(sysRoleMenu);
        return ResponseResult.success();
    }

    @Operation(summary = "角色和菜单关联表删除", description = "角色和菜单关联表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysRoleMenuDeleteCommand command) {
        sysRoleMenuService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "角色和菜单关联表批量删除", description = "角色和菜单关联表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysRoleMenuBatchDeleteCommand command) {
        sysRoleMenuService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
