package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysUserConvert;
import com.chinapost.sd.effective.system.domain.dto.SysUser;
import com.chinapost.sd.effective.system.domain.vo.GetSysUserVO;
import com.chinapost.sd.effective.system.domain.vo.PageSysUserVO;
import com.chinapost.sd.effective.system.domain.vo.command.*;
import com.chinapost.sd.effective.system.domain.vo.query.SysUserGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysUserPageQuery;
import com.chinapost.sd.effective.system.mapper.SysUserMapper;
import com.chinapost.sd.effective.system.service.SysUserService;
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
 * <p>
 * SysUserController类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@RestController
@RequestMapping("/system/user")
@Tag(name = "用户信息表API", description = "用户信息表API")
public class SysUserController{
    private final SysUserConvert sysUserConvert = SysUserConvert.INSTANCE;

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Operation(summary = "用户信息表分页列表", description = "分页获取用户信息表列表")
    @PostMapping("/page")
    public ResponseResult<Page<PageSysUserVO>> page(@RequestBody SysUserPageQuery query) {
        Page<PageSysUserVO> sysUserPage = sysUserMapper.selectByPage(query.toPage(), query);
        return ResponseResult.success(sysUserPage);
    }

    @Operation(summary = "所有用户表", description = "所有用户的详细信息")
    @PostMapping("/getAllInDataScope")
    public ResponseResult<List<PageSysUserVO>> getAllInDataScope(){
        List<SysUser> sysUsers = sysUserService.getAllInDataScope();
        return ResponseResult.success(sysUserConvert.dtoList2VoList(sysUsers));
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.EXPORT)
    @Operation(summary = "用户信息导出", description = "用户信息导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserPageQuery query){
        List<PageSysUserVO> voList = sysUserMapper.selectByList(query);
        ExcelUtil<PageSysUserVO> util = new ExcelUtil<>(PageSysUserVO.class);
        util.exportExcel(response, voList, "岗位数据");
    }
    
    @Operation(summary = "用户信息表信息", description = "用户信息表的详细信息")
    @PostMapping("/get")
    public ResponseResult<GetSysUserVO> get(@RequestBody SysUserGetQuery query) {
        SysUser sysUser = sysUserService.getById(query.getId());
        GetSysUserVO sysUserVo = sysUserConvert.dto2Vo(sysUser);
        return ResponseResult.success(sysUserVo);
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.ADD)
    @Operation(summary = "用户信息表新增", description = "用户信息表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysUserAddCommand command) {
        SysUser sysUser = sysUserConvert.convert(command);
        sysUserService.add(sysUser);
        return ResponseResult.success();
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @Operation(summary = "重置用户密码", description = "重置用户密码")
    @PostMapping("/resetPassword")
    public ResponseResult<Void> resetPassword(@RequestBody SysUserResetPasswordCommand command) {
        sysUserService.resetPassword(command.getUserId(), command.getPassword());
        return ResponseResult.success();
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @Operation(summary = "修改用户状态")
    @PostMapping("/changeStatus")
    public ResponseResult<Void> changeStatus(@RequestBody SysUserChangeStatusCommand command) {
        sysUserService.changeUserStatus(command.getUserId(), command.getStatus());
        return ResponseResult.success();
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @Operation(summary = "用户信息表更新", description = "用户信息表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysUserUpdateCommand command) {
        SysUser sysUser = sysUserConvert.convert(command);
        sysUserService.update(sysUser);
        return ResponseResult.success();
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.DELETE)
    @Operation(summary = "用户信息表删除", description = "用户信息表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysUserDeleteCommand command) {
        sysUserService.delete(command.getId());
        return ResponseResult.success();
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.DELETE)
    @Operation(summary = "用户信息表批量删除", description = "用户信息表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysUserBatchDeleteCommand command) {
        sysUserService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.exportTemplateExcel(response, "用户数据");
    }

}
