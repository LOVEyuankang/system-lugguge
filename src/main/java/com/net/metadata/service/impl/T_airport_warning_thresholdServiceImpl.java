package com.net.metadata.service.impl;

import com.net.metadata.entity.T_airport_warning_threshold;
import com.net.metadata.mapper.T_airport_warning_thresholdMapper;
import com.net.metadata.service.T_airport_warning_thresholdService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预警阀值管理 Service Impl
 */
@Service
@Transactional
public class T_airport_warning_thresholdServiceImpl implements T_airport_warning_thresholdService {

    @Autowired
    private T_airport_warning_thresholdMapper t_airport_warning_thresholdMapper;

    @Override
    public T_airport_warning_threshold findT_airport_warning_thresholdById(Long id) {
        return t_airport_warning_thresholdMapper.findT_airport_warning_thresholdById(id);
    }

    @Override
    public void findT_airport_warning_thresholdDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(t_airport_warning_thresholdMapper.findT_airport_warning_thresholdDataGrid(pageInfo));
        pageInfo.setTotal(t_airport_warning_thresholdMapper.findT_airport_warning_thresholdCount(pageInfo));
    }

    @Override
    public int findT_airport_warning_thresholdCount(PageInfo pageInfo) {
        return t_airport_warning_thresholdMapper.findT_airport_warning_thresholdCount(pageInfo);
    }

    @Override
    public int addT_airport_warning_threshold(T_airport_warning_threshold entity) {
        return t_airport_warning_thresholdMapper.addT_airport_warning_threshold(entity);
    }

    @Override
    public int updateT_airport_warning_threshold(T_airport_warning_threshold entity) {
        return t_airport_warning_thresholdMapper.updateT_airport_warning_threshold(entity);
    }

    @Override
    public int delT_airport_warning_threshold(Long id) {
        return t_airport_warning_thresholdMapper.delT_airport_warning_threshold(id);
    }
}
