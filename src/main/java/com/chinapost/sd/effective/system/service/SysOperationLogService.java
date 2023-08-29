package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysOperationLogConvert;
import com.chinapost.sd.effective.system.domain.dto.SysOperationLog;
import com.chinapost.sd.effective.system.domain.po.SysOperationLogPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysOperationLogListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysOperationLogPageQuery;
import com.chinapost.sd.effective.system.mapper.SysOperationLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * SysOperationLogService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysOperationLogService {
    private final SysOperationLogConvert sysOperationLogConvert = SysOperationLogConvert.INSTANCE;

    @Resource
    private SysOperationLogMapper sysOperationLogMapper;

    public SysOperationLog getById(Long id) {
        SysOperationLogPO po = sysOperationLogMapper.selectById(id);
        return sysOperationLogConvert.po2Dto(po);
    }

    public Page<SysOperationLog> page(SysOperationLogPageQuery pageQuery) {
        Page<SysOperationLogPO> poPage = sysOperationLogMapper.selectByPage(pageQuery.toPage(), pageQuery);
        return sysOperationLogConvert.poPage2DtoPage(poPage);
    }

    public List<SysOperationLog> list(SysOperationLogListQuery listQuery) {
        List<SysOperationLogPO> poList = sysOperationLogMapper.selectByList(listQuery);
        return sysOperationLogConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysOperationLog sysOperationLog) {
        SysOperationLogPO sysOperationLogPo = sysOperationLogConvert.dto2Po(sysOperationLog);
        sysOperationLogMapper.save(sysOperationLogPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysOperationLog sysOperationLog) {
        SysOperationLogPO sysOperationLogPo = sysOperationLogConvert.dto2Po(sysOperationLog);
        sysOperationLogMapper.update(sysOperationLogPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        sysOperationLogMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids) {
        sysOperationLogMapper.deleteBatchIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public void clear() {
        sysOperationLogMapper.deleteAll();
    }
}
