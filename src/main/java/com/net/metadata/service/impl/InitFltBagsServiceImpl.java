package com.net.metadata.service.impl;

import com.net.metadata.entity.InitFltBags;
import com.net.metadata.mapper.InitFltBagsMapper;
import com.net.metadata.service.InitFltBagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InitFltBagsServiceImpl implements InitFltBagsService {

	@Autowired
	private InitFltBagsMapper initFltBagsMapper;
	@Override
	public List<InitFltBags> findInit(String ip) {
		return initFltBagsMapper.findInit(ip);
	}
	@Override
	public List<InitFltBags> findCounts(String plandate, String aircorp,
			String fltno, String code) {
		return initFltBagsMapper.findCounts(plandate, aircorp, fltno, code);
	}

}
