package com.net.metadata.service.impl;

import com.net.metadata.entity.SysLog;
import com.net.metadata.mapper.SysLogMapper;
import com.net.metadata.service.LogService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description：
 * @author：zhixuan.wang
 * @date：2015/10/30 10:40
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void insertLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(sysLogMapper.findDataGrid(pageInfo));
        pageInfo.setTotal(sysLogMapper.findDataGridCount(pageInfo));
    }

	@Override
	public SysLog selectByPrimaryKey(Long id) {
		return sysLogMapper.selectByPrimaryKey(id);
	}
}
