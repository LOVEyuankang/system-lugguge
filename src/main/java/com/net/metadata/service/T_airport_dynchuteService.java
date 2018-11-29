package com.net.metadata.service;


import com.net.metadata.utils.PageInfo;

import java.util.List;

public interface T_airport_dynchuteService {
	 /**
     * 查询滑槽分配
     *
     * @return
     */
	void findT_airport_dynchuteDataGrid(PageInfo pageInfo);
	
	@SuppressWarnings("rawtypes")
	List findT_airport_dynchute(String plandate, String aircorp, String fltno, String attr);
	
	void EndLoading(Long id);
}
