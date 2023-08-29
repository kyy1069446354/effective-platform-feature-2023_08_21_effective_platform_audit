package com.chinapost.sd.effective.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysDictDataConvert;
import com.chinapost.sd.effective.system.domain.dto.SysDictData;
import com.chinapost.sd.effective.system.domain.vo.SysDictDataVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictDataAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictDataBatchDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictDataDeleteCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictDataUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictDataGetQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictDataListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictDataPageQuery;
import com.chinapost.sd.effective.system.service.SysDictDataService;
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
 * SysDictDataController类
 *
 * @author tangyang
 * @since 2023-07-10
 */
@RestController
@RequestMapping("/system/dictData")
@Tag(name = "字典数据表API", description = "字典数据表API")
public class SysDictDataController{

    private final SysDictDataConvert sysDictDataConvert = SysDictDataConvert.INSTANCE;

    @Autowired
    private SysDictDataService sysDictDataService;

    @Operation(summary = "字典数据表分页列表", description = "分页获取字典数据表列表")
    @PostMapping("/page")
    public ResponseResult<Page<SysDictDataVO>> page(@RequestBody SysDictDataPageQuery query) {
        Page<SysDictData> sysDictDataPage = sysDictDataService.page(query);
        return ResponseResult.success(sysDictDataConvert.dtoPage2VoPage(sysDictDataPage));
    }

    @AccessLog(title = "字典管理", businessType = BusinessTypeEnum.EXPORT)
    @Operation(summary = "字典数据导出", description = "字典数据导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictDataListQuery query){
        List<SysDictData> list = sysDictDataService.list(query);
        List<SysDictDataVO> voList = sysDictDataConvert.dtoList2VoList(list);
        ExcelUtil<SysDictDataVO> util = new ExcelUtil<>(SysDictDataVO.class);
        util.exportExcel(response, voList, "操作日志数据");
    }

    @Operation(summary = "字典数据表列表", description = "获取字典数据表列表")
    @PostMapping("/list")
    public ResponseResult<List<SysDictDataVO>> list(@RequestBody SysDictDataListQuery query) {
        List<SysDictData> sysDictDataList = sysDictDataService.list(query);
        return ResponseResult.success(sysDictDataConvert.dtoList2VoList(sysDictDataList));
    }

    @Operation(summary = "字典数据表信息", description = "字典数据表的详细信息")
    @PostMapping("/get")
    public ResponseResult<SysDictDataVO> get(@RequestBody SysDictDataGetQuery query) {
        SysDictData sysDictData = sysDictDataService.getById(query.getId());
        return ResponseResult.success(sysDictDataConvert.dto2Vo(sysDictData));
    }

    @Operation(summary = "字典数据表新增", description = "字典数据表新增")
    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody SysDictDataAddCommand command) {
        SysDictData sysDictData = sysDictDataConvert.convert(command);
        sysDictDataService.add(sysDictData);
        return ResponseResult.success();
    }

    @Operation(summary = "字典数据表更新", description = "字典数据表更新")
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody SysDictDataUpdateCommand command) {
        SysDictData sysDictData = sysDictDataConvert.convert(command);
        sysDictDataService.update(sysDictData);
        return ResponseResult.success();
    }

    @Operation(summary = "字典数据表删除", description = "字典数据表删除")
    @PostMapping("/delete")
    public ResponseResult<Void> delete(@RequestBody SysDictDataDeleteCommand command) {
        sysDictDataService.delete(command.getId());
        return ResponseResult.success();
    }

    @Operation(summary = "字典数据表批量删除", description = "字典数据表批量删除")
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody SysDictDataBatchDeleteCommand command) {
        sysDictDataService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

}
