package com.net.metadata.mapper;


import com.net.metadata.entity.T_airport_chute_device;
import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_chute_deviceMapper {
	/**
     * 添加滑槽对应设备
     *
     * @param t_airport_chute_device
     * @return
     */
    int insert(T_airport_chute_device t_airport_chute_device);

    /**
     * 查询滑槽对应设备列表分页
     *
     * @param pageInfo
     * @return
     */
    @SuppressWarnings("all")
	List findT_airport_chute_devicePageCondition(PageInfo pageInfo);

    /**
     * 滑槽对应设备统计
     *
     * @param pageInfo
     * @return
     */
    int findT_airport_chute_devicePageCount(PageInfo pageInfo);

    /**
     * 滑槽对应设备列表
     */
    List<T_airport_chute_device> findT_airport_chute_deviceAll();

    /**
     * 根据id查询滑槽对应设备
     */
    T_airport_chute_device findT_airport_chute_deviceById(Long ID);

    /**
     * 修改滑槽对应设备
     */
    int updateT_airport_chute_device(T_airport_chute_device t_airport_chute_device);

    /**
     * 删除滑槽对应设备
     */
    int deleteT_airport_chute_deviceById(Long ID);
    

    /**
     * 根据滑槽编号查询滑槽对应设备
     */
    List<T_airport_chute_device> findT_airport_chute_deviceByCODE(String CHUTECODE);
}
