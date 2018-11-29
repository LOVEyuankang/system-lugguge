package com.net.metadata.service.impl;

import com.net.metadata.entity.T_airport_check_in_counters;
import com.net.metadata.mapper.T_airport_check_in_countersMapper;
import com.net.metadata.service.T_airport_check_in_countersService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 值机柜台信息 ServiceImpl
 */
@Service
@Transactional
public class T_airport_check_in_countersServiceImpl implements T_airport_check_in_countersService {

    @Autowired
    private T_airport_check_in_countersMapper t_airport_check_in_countersMapper;

    @Override
    public T_airport_check_in_counters findT_airport_check_in_countersById(Long id) {
        return t_airport_check_in_countersMapper.findT_airport_check_in_countersById(id);
    }

    @Override
    public void findT_airport_check_in_countersDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(t_airport_check_in_countersMapper.findT_airport_check_in_countersDataGrid(pageInfo));
        pageInfo.setTotal(t_airport_check_in_countersMapper.findT_airport_check_in_countersCount(pageInfo));
    }

    @Override
    public int findT_airport_check_in_countersCount(PageInfo pageInfo) {
        return t_airport_check_in_countersMapper.findT_airport_check_in_countersCount(pageInfo);
    }

    @Override
    public int addT_airport_check_in_counters(T_airport_check_in_counters entity) {
        return t_airport_check_in_countersMapper.addT_airport_check_in_counters(entity);
    }

    @Override
    public int updateT_airport_check_in_counters(T_airport_check_in_counters entity) {
        return t_airport_check_in_countersMapper.updateT_airport_check_in_counters(entity);
    }

    @Override
    public int delT_airport_check_in_counters(Long id) {
        return t_airport_check_in_countersMapper.delT_airport_check_in_counters(id);
    }

    @Override
    public T_airport_check_in_counters findT_airport_check_in_countersByCheckinid(String checkinid) {
        return t_airport_check_in_countersMapper.findT_airport_check_in_countersByCheckinid(checkinid);
    }

    @Override
    public T_airport_check_in_counters findT_airport_check_in_countersByCheckincountersid(String checkincountersid) {
        return t_airport_check_in_countersMapper.findT_airport_check_in_countersByCheckincountersid(checkincountersid);
    }
}
