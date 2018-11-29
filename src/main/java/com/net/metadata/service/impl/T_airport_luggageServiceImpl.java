package com.net.metadata.service.impl;

import com.net.metadata.entity.T_airport_luggage;
import com.net.metadata.mapper.T_airport_luggageMapper;
import com.net.metadata.service.T_airport_luggageService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class T_airport_luggageServiceImpl implements T_airport_luggageService {

	@Autowired
	private T_airport_luggageMapper t_airport_luggageMapper;
	
	@Override
	public void addT_airport_luggage(T_airport_luggage model) {
		t_airport_luggageMapper.insert(model);
	}

	@Override
	public void updateT_airport_luggage(T_airport_luggage model) {
		t_airport_luggageMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public void deleteT_airport_luggage(Long id) {
		t_airport_luggageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void findT_airport_luggageDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_luggageMapper.findTPageCondition(pageInfo));
		pageInfo.setTotal(t_airport_luggageMapper.findPageCount(pageInfo));
	}

	@Override
	public T_airport_luggage findT_airport_luggageById(Long id) {
		return t_airport_luggageMapper.selectByPrimaryKey(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByBgcode(String bgcode) {
		return t_airport_luggageMapper.findByBgcode(bgcode);
	}

	@Override
	public int findT_airport_luggageAll() {
		return t_airport_luggageMapper.findT_airport_luggageAll();
	}

	@Override
	public int findT_airport_luggageAllbystutas() {
		return t_airport_luggageMapper.findT_airport_luggageAllbystutas();
	}

	@Override
	public int findT_airport_luggageAllbystutasw() {
		return t_airport_luggageMapper.findT_airport_luggageAllbystutasw();
	}

	@Override
	public int findT_airport_luggageAllbytime(String ti) {
		return t_airport_luggageMapper.findT_airport_luggageAllbytime(ti);
	}

	@Override
	public int findT_airport_luggageAllbystutastime(String ti) {
		return t_airport_luggageMapper.findT_airport_luggageAllbystutastime(ti);
	}

}
