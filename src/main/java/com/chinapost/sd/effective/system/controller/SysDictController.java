package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysDictConvert;
import com.chinapost.sd.effective.system.convert.SysDictDataConvert;
import com.chinapost.sd.effective.system.domain.dto.SysDict;
import com.chinapost.sd.effective.system.domain.dto.SysDictData;
import com.chinapost.sd.effective.system.domain.vo.SysDictDataVO;
import com.chinapost.sd.effective.system.domain.vo.SysDictVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictPageQuery;
import com.chinapost.sd.effective.system.service.SysDictDataService;
import com.chinapost.sd.effective.system.service.SysDictService;
import com.chinapost.sd.boot.infrastructure.annotations.AccessLog;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.constant.BusinessTypeEnum;
import com.chinapost.sd.boot.infrastructure.utils.poi.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * SysDictController类
 *
 * @author tangyang
 * @since 2023-07-10
 */
@RestController
@RequestMapping("/system/dict")
@Tag(name = "字典类型表API", description = "字典类型表API")
public class SysDictController{

    private final SysDictConvert sysDictConvert = SysDictConvert.INSTANCE;
    private final SysDictDataConvert sysDictDataConvert = SysDictDataConvert.INSTANCE;

    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 根据字典类型查询字典数据信息
     * 换成用Enum
     */
    @GetMapping(value = "/{dictCode}")
    @Operation(summary = "字典数据", description = "获取字典数据")
    @Parameter(name = "dictCode", description = "字典对应key")
    public ResponseResult<List<SysDictDataVO>> getSysDictDataByCode(@PathVariable String dictCode) {
        List<SysDictData> sysDictDataList= sysDictDataService.getByDictCode(dictCode);
        return ResponseResult.success(sysDictDataConvert.dtoList2VoList(sysDictDataList));
    }

    @Operation(summary = "字典类型表分页列表", description = "分页获取字典类型表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysDictVO>> page(@RequestBody SysDictPageQuery query) {
        Page<SysDict> sysDictPage = sysDictService.page(query);
        return ResponseResult.success(sysDictConvert.dtoPage2VoPage(sysDictPage));
    }

    @AccessLog(title = "字典管理", businessType = BusinessTypeEnum.EXPORT)
    @Operation(summary = "字典类型导出", description = "字典类型导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictListQuery query){
        List<SysDict> list = sysDictService.list(query);
        List<SysDictVO> voList = sysDictConvert.dtoList2VoList(list);
        ExcelUtil<SysDictVO> util = new ExcelUtil<>(SysDictVO.class);
        util.exportExcel(response, voList, "操作日志数据");
    }
    @Operation(summary = "字典类型表列表", description = "获取字典类型表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysDictVO>> list(@RequestBody SysDictListQuery query) {
        List<SysDict> sysDictList = sysDictService.list(query);
        return ResponseResult.success(sysDictConvert.dtoList2VoList(sysDictList));
    }

    @Operation(summary = "字典类型表信息", description = "字典类型表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysDictVO> get(@RequestBody SysDictGetQuery query) {
        SysDict sysDict = sysDictService.getById(query.getId());
        return ResponseResult.success(sysDictConvert.dto2Vo(sysDict));
    }

    @Operation(summary = "字典类型表新增", description = "字典类型表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysDictAddCommand command) {
        SysDict sysDict = sysDictConvert.convert(command);
        sysDictService.add(sysDict);
        return ResponseResult.success();
    }

    @Operation(summary = "字典类型表更新", description = "字典类型表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysDictUpdateCommand command) {
        SysDict sysDict = sysDictConvert.convert(command);
        sysDictService.update(sysDict);
        return ResponseResult.success();
    }

    @Operation(summary = "字典类型表删除", description = "字典类型表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysDictDeleteCommand command) {
        sysDictService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "字典类型表批量删除", description = "字典类型表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysDictBatchDeleteCommand command) {
        sysDictService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
