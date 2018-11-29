package com.net.metadata.service;

import com.net.metadata.entity.T_airport_sorting_table;
import com.net.metadata.utils.PageInfo;

/**
 * 分拣转盘信息 Service
 */
public interface T_airport_sorting_tableService {

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    T_airport_sorting_table findT_airport_sorting_tableById(Long id);

    /**
     * 查询所有对应信息
     * @param pageInfo
     */
    void findT_airport_sorting_tableDataGrid(PageInfo pageInfo);

    /**
     * 查询所有对应信息的总条数
     * @param pageInfo
     * @return
     */
    int findT_airport_sorting_tableCount(PageInfo pageInfo);

    /**
     * 添加信息
     * @param entity
     */
    int addT_airport_sorting_table(T_airport_sorting_table entity);

    /**
     * 修改信息
     * @param entity
     */
    int updateT_airport_sorting_table(T_airport_sorting_table entity);

    /**
     * 删除信息
     * @param id
     */
    int delT_airport_sorting_table(Long id);

    /**
     * 按分拣转盘编号查询信息
     * @param sortingtableid
     * @return
     */
    T_airport_sorting_table findT_airport_sorting_tableBySortingtableid(String sortingtableid);

}
