package com.chinapost.sd.effective.system.controller;

import cn.hutool.core.lang.tree.Tree;
import com.chinapost.sd.effective.system.convert.SysMenuConvert;
import com.chinapost.sd.effective.system.domain.dto.SysMenu;
import com.chinapost.sd.effective.system.domain.vo.SysMenuVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysMenuAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysMenuBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysMenuDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysMenuUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysMenuGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysMenuListQuery;
import com.chinapost.sd.effective.system.service.SysMenuService;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.utils.AuthenticationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * SysMenuController类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/menu")
@Tag(name = "菜单权限表API", description = "菜单权限表API")
public class SysMenuController{

    private final SysMenuConvert sysMenuConvert = SysMenuConvert.INSTANCE;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取菜单下拉树列表
     */
    @Operation(summary = "菜单列表（树级）", description = "菜单树级下拉框")
    @GetMapping("/dropdownList")
    public ResponseResult<List<Tree<Long>>> dropdownList() {
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        List<Tree<Long>> dropdownList = sysMenuService.getDropdownList(loginUser);
        return ResponseResult.success(dropdownList);
    }

    @Operation(summary = "菜单权限表列表", description = "获取菜单权限表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysMenuVO>> list(@RequestBody SysMenuListQuery query) {
        List<SysMenu> sysMenuList = sysMenuService.list(query);
        return ResponseResult.success(sysMenuConvert.dtoList2VoList(sysMenuList));
    }

    @Operation(summary = "菜单权限表信息", description = "菜单权限表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysMenuVO> get(@RequestBody SysMenuGetQuery query) {
        SysMenu sysMenu = sysMenuService.getById(query.getId());
        return ResponseResult.success(sysMenuConvert.dto2Vo(sysMenu));
    }

    @Operation(summary = "菜单权限表新增", description = "菜单权限表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysMenuAddCommand command) {
        SysMenu sysMenu = sysMenuConvert.convert(command);
        sysMenuService.add(sysMenu);
        return ResponseResult.success();
    }

    @Operation(summary = "菜单权限表更新", description = "菜单权限表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysMenuUpdateCommand command) {
        SysMenu sysMenu = sysMenuConvert.convert(command);
        sysMenuService.update(sysMenu);
        return ResponseResult.success();
    }

    @Operation(summary = "菜单权限表删除", description = "菜单权限表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysMenuDeleteCommand command) {
        sysMenuService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "菜单权限表批量删除", description = "菜单权限表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysMenuBatchDeleteCommand command) {
        sysMenuService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
