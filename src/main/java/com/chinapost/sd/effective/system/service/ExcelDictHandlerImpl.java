package com.chinapost.sd.effective.system.service;

import com.chinapost.sd.effective.system.domain.dto.SysDictData;
import com.chinapost.sd.boot.infrastructure.utils.poi.ExcelDictHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/28
 */
@Component
public class ExcelDictHandlerImpl implements ExcelDictHandler {
    @Autowired
    SysDictDataService sysDictDataService;
    @Override
    public Map<String, String> getDictValueMap(String dictType) {
        List<SysDictData> dictDataList = sysDictDataService.getByDictCode(dictType);
        Map<String, String> result = new HashMap<>();
        for (SysDictData sysDictData : dictDataList) {
            result.put(String.valueOf(sysDictData.getValue()), sysDictData.getLabel());
        }
        return result;
    }
}
