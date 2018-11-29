package com.net.metadata.service;


import com.net.metadata.entity.T_airport_luggage;
import com.net.metadata.utils.PageInfo;

import java.util.List;

public interface T_airport_luggageService {

	public void addT_airport_luggage(T_airport_luggage model);
	
	public void updateT_airport_luggage(T_airport_luggage model);
	
	public void deleteT_airport_luggage(Long id);
	
	public void findT_airport_luggageDataGrid(PageInfo pageInfo);
	
	public T_airport_luggage findT_airport_luggageById(Long id);
	
	@SuppressWarnings("rawtypes")
	public List findByBgcode(String bgcode);
	
	public int findT_airport_luggageAll();
	
	public int findT_airport_luggageAllbystutas();
	
	public int findT_airport_luggageAllbystutasw();
	
	public int findT_airport_luggageAllbytime(String ti);
	
	public int findT_airport_luggageAllbystutastime(String ti);
}
