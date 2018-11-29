package com.net.metadata.service;


import com.net.metadata.entity.Chute;

import java.util.List;

public interface ChuteService {

	public Chute findByCode(String code);
	
	public List<Chute> findAll();
}
