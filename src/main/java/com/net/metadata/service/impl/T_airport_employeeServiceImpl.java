package com.net.metadata.service.impl;

import com.net.metadata.entity.T_airport_employee;
import com.net.metadata.mapper.T_airport_employeeMapper;
import com.net.metadata.service.T_airport_employeeService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class T_airport_employeeServiceImpl implements T_airport_employeeService {

	@Autowired
	private T_airport_employeeMapper t_airport_employeeMapper;
	
	@Override
	public void findT_airport_employeeDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_employeeMapper.findT_airport_employeePageCondition(pageInfo));
		pageInfo.setTotal(t_airport_employeeMapper.findT_airport_employeePageCount(pageInfo));
	}

	@Override
	public void addT_airport_employee(T_airport_employee t_airport_employee) {
		t_airport_employeeMapper.insert(t_airport_employee);
	}

	@Override
	public void updateT_airport_employee(T_airport_employee t_airport_employee) {
		t_airport_employeeMapper.updateT_airport_employee(t_airport_employee);
	}

	@Override
	public T_airport_employee findT_airport_employeeById(Long ID) {
		return t_airport_employeeMapper.findT_airport_employeeById(ID);
	}

	@Override
	public void deleteT_airport_employeeById(Long ID) {
		t_airport_employeeMapper.deleteT_airport_employeeById(ID);
	}

	@Override
	public T_airport_employee findT_airport_employeeByCODE(String CODE) {
		return t_airport_employeeMapper.findT_airport_employeeByCODE(CODE);
	}

	@Override
	public List<T_airport_employee> findAllT_airport_employee() {
		return t_airport_employeeMapper.findT_airport_employeeAll();
	}

}
