package com.net.metadata.service;

import com.net.metadata.entity.T_airport_warning_threshold;
import com.net.metadata.utils.PageInfo;

/**
 * 预警阀值管理 Service
 */
public interface T_airport_warning_thresholdService {

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    T_airport_warning_threshold findT_airport_warning_thresholdById(Long id);

    /**
     * 查询所有对应信息
     * @param pageInfo
     */
    void findT_airport_warning_thresholdDataGrid(PageInfo pageInfo);

    /**
     * 查询所有对应信息的总条数
     * @param pageInfo
     * @return
     */
    int findT_airport_warning_thresholdCount(PageInfo pageInfo);

    /**
     * 添加信息
     * @param entity
     */
    int addT_airport_warning_threshold(T_airport_warning_threshold entity);

    /**
     * 修改信息
     * @param entity
     */
    int updateT_airport_warning_threshold(T_airport_warning_threshold entity);

    /**
     * 删除信息
     * @param id
     */
    int delT_airport_warning_threshold(Long id);
}
