package com.net.metadata.service;


import com.net.metadata.entity.SysLog;
import com.net.metadata.utils.PageInfo;

/**
 * @description：操作日志
 * @author：zhixuan.wang
 * @date：2015/10/30 10:35
 */
public interface LogService {

    void insertLog(SysLog sysLog);

    void findDataGrid(PageInfo pageInfo);
    
    SysLog selectByPrimaryKey(Long id);
}
