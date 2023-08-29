package com.chinapost.sd.effective.system.domain.dto;

import com.chinapost.sd.effective.system.domain.po.SysDeptPO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/8/4
 */
@Getter
@Setter
public class ImportSysDept extends SysDeptPO {

    private ImportSysDept parent;

    private List<ImportSysDept> children;


    public List<ImportSysDept> getChildren() {
        return children;
    }


    public void addChild(ImportSysDept child) {
        if (children == null){
            children = new ArrayList<>();
        }
        children.add(child);
    }
}
