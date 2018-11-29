package com.net.metadata.mapper;


import com.net.metadata.entity.T_airport_employee;
import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_employeeMapper {

	/**
	 * 添加操作员
	 * 
	 * @param t_airport_employee
	 * @return
	 */
	int insert(T_airport_employee t_airport_employee);

	/**
	 * 查询操作员列表分页
	 * 
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings("all")
	List findT_airport_employeePageCondition(PageInfo pageInfo);

	/**
	 * 操作员统计
	 * 
	 * @param pageInfo
	 * @return
	 */
	int findT_airport_employeePageCount(PageInfo pageInfo);

	/**
	 * 操作员列表
	 * 
	 * @return
	 */
	List<T_airport_employee> findT_airport_employeeAll();

	/**
	 * 根据id查询操作员
	 * 
	 * @param id
	 * @return
	 */
	T_airport_employee findT_airport_employeeById(Long ID);

	/**
	 * 修改操作员
	 * 
	 * @param t_airport_employee
	 * @return
	 */
	int updateT_airport_employee(T_airport_employee t_airport_employee);

	/**
	 * 删除操作员
	 * 
	 * @param id
	 * @return
	 */
	int deleteT_airport_employeeById(Long ID);

	/**
	 * 判断id唯一性
	 * 
	 * @param sql
	 * @return
	 */
	T_airport_employee findT_airport_employeeByCODE(String CODE);
}
