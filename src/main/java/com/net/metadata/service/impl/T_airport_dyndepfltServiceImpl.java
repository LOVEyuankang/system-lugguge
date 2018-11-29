package com.net.metadata.service.impl;

import com.net.metadata.mapper.T_airport_dyndepfltMapper;
import com.net.metadata.service.T_airport_dyndepfltService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class T_airport_dyndepfltServiceImpl implements T_airport_dyndepfltService {


	@Autowired
	private T_airport_dyndepfltMapper t_airport_dyndepfltMapper;
	@Override
	public void findT_airport_dyndepfltDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_dyndepfltMapper.findT_airport_dyndepfltPageCondition(pageInfo));
		pageInfo.setTotal(t_airport_dyndepfltMapper.findT_airport_dyndepfltPageCount(pageInfo));
	}
	@Override
	public int findT_airport_dyndepfltAll() {
		return t_airport_dyndepfltMapper.findT_airport_dyndepfltAll();
	}
	
	@Override
	public int findT_airport_dyndepfltAllbyStatus() {
		return t_airport_dyndepfltMapper.findT_airport_dyndepfltAllbyStatus();
	}
	@Override
	public int findT_airport_dyndepfltAllbytime(String ti) {
		return t_airport_dyndepfltMapper.findT_airport_dyndepfltAllbytime(ti);
	}
	@Override
	public int findT_airport_dyndepfltAllbyStatusbytime(String tim) {
		return t_airport_dyndepfltMapper.findT_airport_dyndepfltAllbyStatusbytime(tim);
	}

}
