package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysDeptConvert;
import com.chinapost.sd.effective.system.domain.dto.SysDept;
import com.chinapost.sd.effective.system.domain.po.SysDeptPO;
import com.chinapost.sd.effective.system.domain.vo.ImportSysDeptVO;
import com.chinapost.sd.effective.system.domain.vo.SysDeptTreeNodeVO;
import com.chinapost.sd.effective.system.domain.vo.SysDeptVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysDeptAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDeptDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDeptUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysDeptGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDeptListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDeptPageQuery;
import com.chinapost.sd.effective.system.mapper.SysDeptMapper;
import com.chinapost.sd.effective.system.service.DataScopeHandler;
import com.chinapost.sd.effective.system.service.SysDeptService;
import com.chinapost.sd.boot.commons.utils.json.JacksonUtil;
import com.chinapost.sd.boot.commons.utils.tree.TreeUtils;
import com.chinapost.sd.boot.infrastructure.annotations.AccessLog;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.constant.BusinessTypeEnum;
import com.chinapost.sd.boot.infrastructure.utils.poi.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * SysDeptController类
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Slf4j
@RestController
@RequestMapping("/system/dept")
@Tag(name = "部门表API", description = "部门表API")
public class SysDeptController{

    private final SysDeptConvert sysDeptConvert = SysDeptConvert.INSTANCE;

    @Autowired
    private SysDeptService sysDeptService;
    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * 获取部门下拉树列表
     */
    @Operation(summary = "获取部门树级结构")
    @GetMapping("/dropdownList")
    public ResponseResult<List<SysDeptTreeNodeVO>> dropdownList() {
        List<SysDeptPO> list = sysDeptMapper.selectByList(new SysDeptListQuery());
        List<SysDeptTreeNodeVO> voList = sysDeptConvert.poList2VoList(list);
        List<SysDeptTreeNodeVO> roots = TreeUtils.buildTree(voList);
        return ResponseResult.success(roots);
    }

    /**
     * 获取部门下拉树列表
     */
    @Operation(summary = "获取部门树级结构，在数据权限范围内的")
    @GetMapping("/dropdownListInDataScope")
    public ResponseResult<List<SysDeptTreeNodeVO>> dropdownListInDataScope() {
        List<SysDeptPO> list = sysDeptMapper.selectByListInDataScope(DataScopeHandler.createDataScope());
        List<SysDeptTreeNodeVO> voList = sysDeptConvert.poList2VoList(list);
        List<SysDeptTreeNodeVO> roots = TreeUtils.buildTree(voList);
        return ResponseResult.success(roots);
    }

    @Operation(summary = "部门表分页列表", description = "部门表分页列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysDeptVO>> page(@RequestBody SysDeptPageQuery query) {
        Page<SysDept> dtoPage = sysDeptService.page(query);
        return ResponseResult.success(sysDeptConvert.dtoPage2VoPage(dtoPage));
    }

    @AccessLog(title = "部门管理", businessType = BusinessTypeEnum.EXPORT)
    @Operation(summary = "部门数据导出", description = "部门数据导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDeptListQuery query){
        List<SysDept> list = sysDeptService.list(query);
        List<SysDeptVO> voList = sysDeptConvert.dtoList2VoList(list);
        ExcelUtil<SysDeptVO> util = new ExcelUtil<>(SysDeptVO.class);
        util.exportExcel(response, voList, "操作日志数据");
    }

    @Operation(summary = "部门表信息", description = "部门表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysDeptVO> get(@RequestBody SysDeptGetQuery query) {
        SysDept sysDept = sysDeptService.getById(query.getId());
        return ResponseResult.success(sysDeptConvert.dto2Vo(sysDept));
    }

    @Operation(summary = "所有部门表", description = "所有部门表的详细信息")
    @PostMapping("/getall")
    public ResponseResult<List<SysDeptVO>> getAll() {
        List<SysDept> sysDeptList = sysDeptService.getAll();
        return ResponseResult.success(sysDeptConvert.dtoList2VoList(sysDeptList));
    }

    @Operation(summary = "部门表新增", description = "部门表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysDeptAddCommand command) {
        SysDept sysDept = sysDeptConvert.convert(command);
        sysDeptService.add(sysDept);
        return ResponseResult.success();
    }

    @Operation(summary = "部门表更新", description = "部门表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysDeptUpdateCommand command) {
        SysDept sysDept = sysDeptConvert.convert(command);
        sysDeptService.update(sysDept);
        return ResponseResult.success();
    }

    @AccessLog(title = "部门管理", businessType = BusinessTypeEnum.DELETE)
    @Operation(summary = "部门表删除", description = "部门表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysDeptDeleteCommand command) {
        sysDeptService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "部门表删除", description = "部门表删除")
    @PostMapping("/clear")
    public ResponseResult<Void> clear() {
        sysDeptService.clear();
        return ResponseResult.success();
    }
    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil<SysDeptVO> util = new ExcelUtil<>(SysDeptVO.class);
        util.exportTemplateExcel(response, "用户数据");
    }

    @Operation(summary = "部门数据导入", description = "部门数据导入")
    @PostMapping("/importData")
    public ResponseResult<String> importData(MultipartFile file, boolean updateSupport){
        List<ImportSysDeptVO> deptList = Collections.emptyList();
        try {
            Long start = System.currentTimeMillis();
            ExcelUtil<ImportSysDeptVO> util = new ExcelUtil<>(ImportSysDeptVO.class);
            deptList = util.importExcel(file.getInputStream());
            sysDeptService.importDeptList(deptList, updateSupport);
            Long end = System.currentTimeMillis();
            return ResponseResult.success("数据已全部导入成功！共 " + deptList.size() + " 条，耗时：" + (end - start)/1000 + "秒");
        }catch (Exception e){
            log.error("导入失败：数据如下：{}", JacksonUtil.toString(deptList));
            log.error(e.getMessage(), e);
            return ResponseResult.error(e);
        }
    }
}
