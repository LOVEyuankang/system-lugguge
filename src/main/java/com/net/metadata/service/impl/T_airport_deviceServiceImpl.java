package com.net.metadata.service.impl;


import com.google.common.collect.Lists;
import com.net.metadata.entity.T_airport_device;
import com.net.metadata.mapper.T_airport_deviceMapper;
import com.net.metadata.service.T_airport_deviceService;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class T_airport_deviceServiceImpl implements T_airport_deviceService {

	@Autowired
	private T_airport_deviceMapper t_airport_deviceMapper;
	
	@Override
	public void findT_airport_deviceDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(t_airport_deviceMapper.findT_airport_devicePageCondition(pageInfo));
		pageInfo.setTotal(t_airport_deviceMapper.findT_airport_devicePageCount(pageInfo));
	}

	@Override
	public void addT_airport_device(T_airport_device t_airport_device) {
		t_airport_deviceMapper.insert(t_airport_device);
	}

	@Override
	public void updateT_airport_device(T_airport_device t_airport_device) {
		t_airport_deviceMapper.updateT_airport_device(t_airport_device);
	}

	@Override
	public T_airport_device findT_airport_deviceById(Long ID) {
		return t_airport_deviceMapper.findT_airport_deviceById(ID);
	}

	@Override
	public void deleteT_airport_deviceById(Long ID) {
		t_airport_deviceMapper.deleteT_airport_deviceById(ID);
	}

	@Override
	public List<Tree> findTree() {
		   List<Tree> trees = Lists.newArrayList();
	        List<T_airport_device> t_airport_devices = t_airport_deviceMapper.findT_airport_deviceAll();
	        for (T_airport_device t_airport_device : t_airport_devices) {
	            Tree tree = new Tree();
	            tree.setPid(t_airport_device.getType());
	            tree.setText(t_airport_device.getCode());
	            trees.add(tree);
	        }
	        return trees;
	}

	@Override
	public T_airport_device findT_airport_deviceByCODE(String CODE) {
		return t_airport_deviceMapper.findT_airport_deviceByCODE(CODE);
	}

}
