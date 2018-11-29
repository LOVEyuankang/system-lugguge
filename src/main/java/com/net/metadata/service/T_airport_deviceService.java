package com.net.metadata.service;


import com.net.metadata.entity.T_airport_device;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.Tree;

import java.util.List;

/**
 * @description：设备管理
 * @author：yxd
 * @date：2015/10/1 14:51
 */
public interface T_airport_deviceService {

	/**
	 * 查询设备树
	 * 
	 * @return
	 */
	List<Tree> findTree();

	/**
	 * 查询所有设备
	 * 
	 * @return
	 */
	void findT_airport_deviceDataGrid(PageInfo pageInfo);

	/**
	 * 添加设备
	 * 
	 * @param t_airport_device
	 */
	void addT_airport_device(T_airport_device t_airport_device);

	/**
	 * 修改设备
	 * 
	 * @param t_airport_device
	 */
	void updateT_airport_device(T_airport_device t_airport_device);

	/**
	 * 根据id查询设备
	 * 
	 * @param id
	 * @return
	 */
	T_airport_device findT_airport_deviceById(Long ID);

	/**
	 * 根据id删除设备
	 * 
	 * @param id
	 */
	void deleteT_airport_deviceById(Long ID);

	/**
	 * 判断id唯一性
	 * 
	 * @param sql
	 * @return
	 */
	T_airport_device findT_airport_deviceByCODE(String CODE);
}
