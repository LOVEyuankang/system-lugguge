package com.net.metadata.service.impl;

import com.net.metadata.mapper.T_airport_dynchuteMapper;
import com.net.metadata.service.T_airport_dynchuteService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class T_airport_dynchuteServiceImpl implements T_airport_dynchuteService {

	@Autowired
	private T_airport_dynchuteMapper t_airport_dynchuteMapper;
	
	@Override
	public void findT_airport_dynchuteDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_dynchuteMapper.findT_airport_dynchutePageCondition(pageInfo));
		pageInfo.setTotal(t_airport_dynchuteMapper.findT_airport_dynchutePageCount(pageInfo));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findT_airport_dynchute(String plandate, String aircorp, String fltno, String attr) {
		return t_airport_dynchuteMapper.findT_airport_dynchuteByCondition(plandate, aircorp, fltno, attr);
	}

	@Override
	public void EndLoading(Long id) {
		t_airport_dynchuteMapper.updateT_airport_dynchute(id);
	}

}
