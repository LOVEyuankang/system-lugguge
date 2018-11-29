package com.net.metadata.service.impl;

import com.net.metadata.entity.Chute;
import com.net.metadata.mapper.ChutesMapper;
import com.net.metadata.service.ChuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChuteServiceImpl implements ChuteService {

	@Autowired
	private ChutesMapper chutesMapper;
	
	@Override
	public Chute findByCode(String code) {
		return chutesMapper.findByCode(code);
	}

	@Override
	public List<Chute> findAll() {
		return chutesMapper.findAll();
	}

}
