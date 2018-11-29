package com.net.metadata.mapper;

import com.net.metadata.entity.T_airport_check_in_counters;
import com.net.metadata.utils.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 值机柜台信息管理 Mapper
 */
@Repository
public interface T_airport_check_in_countersMapper {

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    T_airport_check_in_counters findT_airport_check_in_countersById(@Param("id") Long id);

    /**
     * 查询所有对应信息
     * @param pageInfo
     */
    List findT_airport_check_in_countersDataGrid(PageInfo pageInfo);

    /**
     * 查询所有对应信息的总条数
     * @param pageInfo
     * @return
     */
    int findT_airport_check_in_countersCount(PageInfo pageInfo);

    /**
     * 添加信息
     * @param entity
     */
    int addT_airport_check_in_counters(T_airport_check_in_counters entity);

    /**
     * 修改信息
     * @param entity
     */
    int updateT_airport_check_in_counters(T_airport_check_in_counters entity);

    /**
     * 删除信息
     * @param id
     */
    int delT_airport_check_in_counters(@Param("id") Long id);

    /**
     * 按值机岛编号查询信息
     * @param checkinid
     * @return
     */
    T_airport_check_in_counters findT_airport_check_in_countersByCheckinid(@Param("checkinid") String checkinid);

    /**
     * 按值机岛编号查询信息
     * @param checkincountersid
     * @return
     */
    T_airport_check_in_counters findT_airport_check_in_countersByCheckincountersid(@Param("checkincountersid") String checkincountersid);

}
