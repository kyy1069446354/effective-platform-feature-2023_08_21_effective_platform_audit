package com.chinapost.sd.effective.system.domain.vo;

import com.chinapost.sd.boot.commons.utils.tree.TreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取部门树级结构的nodeVO
 *
 * @author tangyang
 * @since 2023/8/3
 */
@Getter
@Setter
public class SysDeptTreeNodeVO implements TreeNode<SysDeptTreeNodeVO> {

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private Long id;

    /**
     * 父部门id
     */
    @Schema(description = "父部门id")
    private Long parentId;
    private List<SysDeptTreeNodeVO> children;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String name;

    /**
     * 部门编码
     */
    @Schema(description = "部门编码")
    private String code;

    @Override
    public List<SysDeptTreeNodeVO> getChildren() {
        return children;
    }

    @Override
    public void addChild(SysDeptTreeNodeVO child) {
        if (children == null){
            children = new ArrayList<>();
        }
        children.add(child);
    }
}
