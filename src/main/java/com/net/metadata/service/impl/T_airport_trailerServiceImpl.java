package com.net.metadata.service.impl;

import com.net.metadata.entity.T_airport_trailer;
import com.net.metadata.mapper.T_airport_trailerMapper;
import com.net.metadata.service.T_airport_trailerService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class T_airport_trailerServiceImpl implements T_airport_trailerService {

	@Autowired
	private T_airport_trailerMapper t_airport_trailerMapper;
	
	@Override
	public void findT_airport_trailerDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_trailerMapper.findT_airport_trailerPageCondition(pageInfo));
		pageInfo.setTotal(t_airport_trailerMapper.findT_airport_trailerPageCount(pageInfo));
	}

	@Override
	public void addT_airport_trailer(T_airport_trailer t_airport_trailer) {
		t_airport_trailerMapper.insert(t_airport_trailer);
	}

	@Override
	public void updateT_airport_trailer(T_airport_trailer t_airport_trailer) {
		t_airport_trailerMapper.updateT_airport_trailer(t_airport_trailer);
	}

	@Override
	public T_airport_trailer findT_airport_trailerById(Long ID) {
		return t_airport_trailerMapper.findT_airport_trailerById(ID);
	}

	@Override
	public void deleteT_airport_trailerById(Long ID) {
		t_airport_trailerMapper.deleteT_airport_trailerById(ID);
	}

	@Override
	public T_airport_trailer findT_airport_trailerByCODE(String CODE) {
		return t_airport_trailerMapper.findT_airport_trailerByCODE(CODE);
	}

	@Override
	public List<T_airport_trailer> findT_airport_trailerAll() {
		return t_airport_trailerMapper.findT_airport_trailerAll();
	}

}
