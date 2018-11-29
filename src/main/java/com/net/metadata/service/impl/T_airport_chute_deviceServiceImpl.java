package com.net.metadata.service.impl;

import com.net.metadata.entity.T_airport_chute_device;
import com.net.metadata.mapper.T_airport_chute_deviceMapper;
import com.net.metadata.service.T_airport_chute_deviceService;
import com.net.metadata.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class T_airport_chute_deviceServiceImpl implements T_airport_chute_deviceService {

	@Autowired
	private T_airport_chute_deviceMapper t_airport_chute_deviceMapper;

	@Override
	public void findT_airport_chute_deviceDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_chute_deviceMapper.findT_airport_chute_devicePageCondition(pageInfo));
		pageInfo.setTotal(t_airport_chute_deviceMapper.findT_airport_chute_devicePageCount(pageInfo));
	}

	@Override
	public void addT_airport_chute_device(T_airport_chute_device t_airport_chute_device) {
		t_airport_chute_deviceMapper.insert(t_airport_chute_device);
	}

	@Override
	public void updateT_airport_chute_device(T_airport_chute_device t_airport_chute_device) {
		t_airport_chute_deviceMapper.updateT_airport_chute_device(t_airport_chute_device);
	}

	@Override
	public T_airport_chute_device findT_airport_chute_deviceById(Long ID) {
		return t_airport_chute_deviceMapper.findT_airport_chute_deviceById(ID);
	}

	@Override
	public void deleteT_airport_chute_deviceById(Long ID) {
		t_airport_chute_deviceMapper.deleteT_airport_chute_deviceById(ID);
	}

	@Override
	public List<T_airport_chute_device> findT_airport_chute_deviceByCODE(String CHUTECODE) {
		return t_airport_chute_deviceMapper.findT_airport_chute_deviceByCODE(CHUTECODE);
	}

}
