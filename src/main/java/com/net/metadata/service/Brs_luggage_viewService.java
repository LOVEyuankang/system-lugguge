package com.net.metadata.service;


import com.net.metadata.utils.PageInfo;

import java.util.List;

public interface Brs_luggage_viewService {

	public void findBrs_luggage_ViewDataGrid(PageInfo pageInfo);
	
	@SuppressWarnings("rawtypes")
	public List printSearch(String plandate, String flightno);
}
