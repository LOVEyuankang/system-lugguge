package com.net.metadata.service;


import com.net.metadata.entity.T_airport_employee;
import com.net.metadata.utils.PageInfo;

import java.util.List;

/**
 * @description：操作员管理
 * @author：yxd
 * @date：2015/10/1 14:51
 */
public interface T_airport_employeeService {
	 /**
     * 查询所有操作员
     *
     * @return
     */
	void findT_airport_employeeDataGrid(PageInfo pageInfo);

    /**
     * 添加操作员
     *
     * @param t_airport_employee
     */
    void addT_airport_employee(T_airport_employee t_airport_employee);


    /**
     * 修改操作员
     *
     * @param t_airport_employee
     */
    void updateT_airport_employee(T_airport_employee t_airport_employee);

    /**
     * 根据id查询操作员
     *
     * @param id
     * @return
     */
    T_airport_employee findT_airport_employeeById(Long ID);
    

    /**
     * 根据id删除操作员
     *
     * @param id
     */
    void deleteT_airport_employeeById(Long ID);
    
    /**
     * 判断id唯一性
     *
     * @param sql
     * @return
     */
    T_airport_employee findT_airport_employeeByCODE(String CODE);
    
    List<T_airport_employee> findAllT_airport_employee();
}
