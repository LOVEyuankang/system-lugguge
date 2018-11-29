package com.net.metadata.service;


import com.net.metadata.utils.PageInfo;

public interface T_airport_dyndepfltService {
	 /**
     * 查询航班动态
     *
     * @return
     */
	void findT_airport_dyndepfltDataGrid(PageInfo pageInfo);
	
	 /**
     * 航班动态总数
     *
     * @return
     */
	int findT_airport_dyndepfltAll();
	
	 /**
     * 航班动态时间段总数
     *
     * @return
     */
	int findT_airport_dyndepfltAllbytime(String ti);
	
	 /**
     * 已保障的航班
     *
     * @return
     */
	int findT_airport_dyndepfltAllbyStatus();
	
	 /**
     * 已保障的时间段航班
     *
     * @return
     */
	int findT_airport_dyndepfltAllbyStatusbytime(String tim);
}
