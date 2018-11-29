package com.net.metadata.service.impl;

import com.net.metadata.entity.T_airport_sorting_table;
import com.net.metadata.mapper.T_airport_sorting_tableMapper;
import com.net.metadata.service.T_airport_sorting_tableService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分拣转盘信息 Service Impl
 */
@Service
@Transactional
public class T_airport_sorting_tableServiceImpl implements T_airport_sorting_tableService {

    @Autowired
    private T_airport_sorting_tableMapper t_airport_sorting_tableMapper;

    @Override
    public T_airport_sorting_table findT_airport_sorting_tableById(Long id) {
        return t_airport_sorting_tableMapper.findT_airport_sorting_tableById(id);
    }

    @Override
    public void findT_airport_sorting_tableDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(t_airport_sorting_tableMapper.findT_airport_sorting_tableDataGrid(pageInfo));
        pageInfo.setTotal(t_airport_sorting_tableMapper.findT_airport_sorting_tableCount(pageInfo));
    }

    @Override
    public int findT_airport_sorting_tableCount(PageInfo pageInfo) {
        return t_airport_sorting_tableMapper.findT_airport_sorting_tableCount(pageInfo);
    }

    @Override
    public int addT_airport_sorting_table(T_airport_sorting_table entity) {
        return t_airport_sorting_tableMapper.addT_airport_sorting_table(entity);
    }

    @Override
    public int updateT_airport_sorting_table(T_airport_sorting_table entity) {
        return t_airport_sorting_tableMapper.updateT_airport_sorting_table(entity);
    }

    @Override
    public int delT_airport_sorting_table(Long id) {
        return t_airport_sorting_tableMapper.delT_airport_sorting_table(id);
    }

    @Override
    public T_airport_sorting_table findT_airport_sorting_tableBySortingtableid(String sortingtableid) {
        return t_airport_sorting_tableMapper.findT_airport_sorting_tableBySortingtableid(sortingtableid);
    }
}
