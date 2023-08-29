package com.chinapost.sd.effective.system.domain.vo;

import com.chinapost.sd.boot.infrastructure.annotations.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门导入的Excel定义类
 *
 * @author tangyang
 * @since 2023/8/1
 */
@Getter
@Setter
@ToString
public class ImportSysDeptVO {
    /**
     * 部门id
     */
    @Excel(name = "部门id", sort = 1)
    private String id;

    /**
     * 父部门id
     */
    @Excel(name = "父部门id", sort = 2)
    private String parentId;


    /**
     * 部门编码
     */
    @Excel(name = "部门编码", sort = 3)
    private String code;

    /**
     * 部门名称
     */
    @Excel(name = "部门名称", sort = 4)
    private String name;
}
