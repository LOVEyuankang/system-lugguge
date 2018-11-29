package com.net.metadata.service.impl;

import com.net.metadata.mapper.Brs_luggage_viewMapper;
import com.net.metadata.service.Brs_luggage_viewService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Brs_luggage_viewServiceImpl implements Brs_luggage_viewService {

	@Autowired
	private Brs_luggage_viewMapper brs_luggage_viewMapper;
	
	@Override
	public void findBrs_luggage_ViewDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(brs_luggage_viewMapper.findTPageCondition(pageInfo));
		pageInfo.setTotal(brs_luggage_viewMapper.findPageCount(pageInfo));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List printSearch(String plandate, String flightno) {
		return brs_luggage_viewMapper.printSearch(plandate, flightno);
	}

}
