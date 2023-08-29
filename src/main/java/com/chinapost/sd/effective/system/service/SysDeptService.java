package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysDeptConvert;
import com.chinapost.sd.effective.system.domain.dto.ImportSysDept;
import com.chinapost.sd.effective.system.domain.dto.SysDept;
import com.chinapost.sd.effective.system.domain.po.SysDeptPO;
import com.chinapost.sd.effective.system.domain.vo.ImportSysDeptVO;
import com.chinapost.sd.effective.system.domain.vo.query.SysDeptListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDeptPageQuery;
import com.chinapost.sd.effective.system.mapper.SysDeptMapper;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import com.chinapost.sd.boot.infrastructure.error.BusinessErrorCode;
import com.chinapost.sd.boot.infrastructure.exception.BusinessException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * SysDeptService类
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysDeptService {
    private final SysDeptConvert sysDeptConvert = SysDeptConvert.INSTANCE;

    @Resource
    private SysDeptMapper sysDeptMapper;

    public SysDept getById(Long id){
        SysDeptPO po = sysDeptMapper.selectById(id);
        return sysDeptConvert.po2Dto(po);
    }

    public List<SysDept> list(SysDeptListQuery listQuery){
        List<SysDeptPO> poList = sysDeptMapper.selectByList(listQuery);
        return sysDeptConvert.poList2DtoList(poList);
    }


    public Page<SysDept> page(SysDeptPageQuery query) {
        Page<SysDeptPO> poPage = sysDeptMapper.selectByPage(query.toPage(), query);
        return sysDeptConvert.poPage2DtoPage(poPage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysDept sysDept){
        sysDept.setId(IdGenerator.generate());
        sysDept.setAncestors(this.generateAncestors(sysDept));
        SysDeptPO sysDeptPo = sysDeptConvert.dto2Po(sysDept);
        sysDeptMapper.save(sysDeptPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysDept sysDept){
        // 名称不能重复
        // 父id不能改为自己
        if (Objects.equals(sysDept.getParentId(), sysDept.getId())){
            throw new BusinessException(BusinessErrorCode.DEPT_PARENT_ID_IS_NOT_ALLOWED_SELF);
        }
        // 有在用的子节点不能停用
        if (sysDept.getStatus() == 0 && !getChildren(sysDept.getId()).isEmpty()){
            throw new BusinessException(BusinessErrorCode.DEPT_STATUS_ID_IS_NOT_ALLOWED_CHANGE);
        }
        SysDeptPO sysDeptPo = sysDeptConvert.dto2Po(sysDept);
        sysDeptMapper.update(sysDeptPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        // 如果有子节点不允许删除
        List<SysDept> sons = getChildren(id);
        if (!sons.isEmpty()){
            throw new BusinessException(BusinessErrorCode.DEPT_EXIST_CHILD_DEPT_NOT_ALLOW_DELETE);
        }
        // 有关联的用户不允许删除
        sysDeptMapper.deleteById(id);
    }

    public List<SysDept> getChildren(Long parentId){
        SysDeptListQuery listQuery = new SysDeptListQuery();
        listQuery.setParentId(parentId);
        listQuery.setStatus(1);
        List<SysDeptPO> sysDeptPoS = sysDeptMapper.selectByList(listQuery);
        return sysDeptConvert.poList2DtoList(sysDeptPoS);
    }
    public List<SysDept> getAll(){
        List<SysDeptPO> sysDeptPoS = sysDeptMapper.selectAll();
        return sysDeptConvert.poList2DtoList(sysDeptPoS);
    }


    private String generateAncestors(SysDept sysDept) {
        if (sysDept.getParentId() == 0){
            return "0";
        }
        SysDeptPO parent = sysDeptMapper.selectById(sysDept.getParentId());
        return parent.getAncestors() + "," + sysDept.getParentId();
    }

    /**
     *  导入部门数据
     * @param deptList 部门列表
     * @param isUpdateSupport 是否支持更新
     * @return 成功或失败信息
     * @throws Exception 异常
     */
    @Transactional(rollbackFor = Exception.class)
    public void importDeptList(List<ImportSysDeptVO> deptList, boolean isUpdateSupport) throws Exception{
        if (CollectionUtils.isEmpty(deptList)){
            throw new Exception("导入部门数据不能为空！");
        }

        Long count = sysDeptMapper.selectCount(new QueryWrapper<>());
        if (count > 0){
            throw new Exception("导入数据前，请先清空原有数据！");
        }

        List<ImportSysDept> deptNodeList = sysDeptConvert.voList2DtoList(deptList);
        //  构建树
        Map<Long, ImportSysDept> deptNodeMap = new HashMap<>(deptNodeList.size() + deptNodeList.size()/2);
        for (ImportSysDept deptNode : deptNodeList) {
            deptNodeMap.put(deptNode.getId(), deptNode);
        }
        List<ImportSysDept> deptRoots = new ArrayList<>();
        for (ImportSysDept deptNode : deptNodeList) {
            ImportSysDept nodeParent = deptNodeMap.get(deptNode.getParentId());
            if (nodeParent != null){
                nodeParent.addChild(deptNode);
                deptNode.setParent(nodeParent);
            }else {
                deptRoots.add(deptNode);
            }
        }

        //  层序遍历树 设置ancestors和hasChildren
        Queue<ImportSysDept> queue = new LinkedList<>(deptRoots);
        while (!queue.isEmpty()){
            ImportSysDept dept = queue.poll();
            if (CollectionUtils.isEmpty(dept.getChildren())){
                dept.setHasChildren(false);
            }else {
                dept.setHasChildren(true);
                queue.addAll(dept.getChildren());
            }
            ImportSysDept parent = dept.getParent();
            if (parent == null){
                dept.setAncestors("");
            }else {
                if (parent.getAncestors().isEmpty()){
                    dept.setAncestors(String.valueOf(parent.getId()));
                }else {
                    dept.setAncestors(parent.getAncestors() + "," + parent.getId());
                }
            }
        }

        sysDeptMapper.save((List<SysDeptPO>)(Object)deptNodeList, 500);
    }

    public void clear() {
        sysDeptMapper.deleteAll();
    }


}
