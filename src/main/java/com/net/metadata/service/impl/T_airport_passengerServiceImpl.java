package com.net.metadata.service.impl;

import com.net.metadata.mapper.T_airport_passengerMapper;
import com.net.metadata.service.T_airport_passengerService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class T_airport_passengerServiceImpl implements T_airport_passengerService {

	@Autowired
	private T_airport_passengerMapper t_airport_passengerMapper;
	
	@Override
	public void findT_airport_passengerDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_passengerMapper.findT_airport_passengerPageCondition(pageInfo));
		pageInfo.setTotal(t_airport_passengerMapper.findT_airport_passengerPageCount(pageInfo));
	}

}
