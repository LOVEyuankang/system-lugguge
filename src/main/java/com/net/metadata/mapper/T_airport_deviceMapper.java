package com.net.metadata.mapper;


import com.net.metadata.entity.T_airport_device;
import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_deviceMapper {

	/**
	 * 添加设备
	 * 
	 * @param t_airport_device
	 * @return
	 */
	int insert(T_airport_device t_airport_device);

	/**
	 * 查询设备列表分页
	 * 
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List findT_airport_devicePageCondition(PageInfo pageInfo);

	/**
	 * 设备统计
	 * 
	 * @param pageInfo
	 * @return
	 */
	int findT_airport_devicePageCount(PageInfo pageInfo);

	/**
	 * 设备列表
	 * 
	 * @return
	 */
	List<T_airport_device> findT_airport_deviceAll();

	/**
	 * 根据id查询设备
	 * 
	 * @param id
	 * @return
	 */
	T_airport_device findT_airport_deviceById(Long ID);

	/**
	 * 修改设备
	 * 
	 * @param t_airport_device
	 * @return
	 */
	int updateT_airport_device(T_airport_device t_airport_device);

	/**
	 * 删除设备
	 * 
	 * @param id
	 * @return
	 */
	int deleteT_airport_deviceById(Long ID);

	/**
	 * 判断id唯一性
	 * 
	 * @param sql
	 * @return
	 */
	T_airport_device findT_airport_deviceByCODE(String CODE);
}
