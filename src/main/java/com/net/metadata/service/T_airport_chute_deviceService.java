package com.net.metadata.service;


import com.net.metadata.entity.T_airport_chute_device;
import com.net.metadata.utils.PageInfo;

import java.util.List;

/**
 * @description：滑槽对应设备管理
 * @author：yxd
 * @date：2015/10/1 14:51
 */
public interface T_airport_chute_deviceService {
	/**
	 * 查询所有滑槽对应设备
	 * 
	 * @return
	 */
	void findT_airport_chute_deviceDataGrid(PageInfo pageInfo);

	/**
	 * 添加滑槽对应设备
	 * 
	 * @param t_airport_chute_device
	 */
	void addT_airport_chute_device(T_airport_chute_device t_airport_chute_device);

	/**
	 * 修改滑槽对应设备
	 * 
	 * @param t_airport_chute_device
	 */
	void updateT_airport_chute_device( T_airport_chute_device t_airport_chute_device);

	/**
	 * 根据id查询滑槽对应设备
	 * 
	 * @param id
	 * @return
	 */
	T_airport_chute_device findT_airport_chute_deviceById(Long ID);

	/**
	 * 根据id删除滑槽对应设备
	 * 
	 * @param id
	 */
	void deleteT_airport_chute_deviceById(Long ID);

	/**
	 * 根据滑槽编号查询滑槽对应设备
	 * 
	 * @param CHUTECODE
	 * @return
	 */
	List<T_airport_chute_device> findT_airport_chute_deviceByCODE(String CHUTECODE);
}
