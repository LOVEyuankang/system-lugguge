package com.net.metadata.mapper;


import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_dyndepfltMapper {
    /**
     * 查询航班动态列表分页
     *
     * @param pageInfo
     * @return
     */
    @SuppressWarnings("rawtypes")
	List findT_airport_dyndepfltPageCondition(PageInfo pageInfo);

    /**
     * 航班动态统计
     *
     * @param pageInfo
     * @return
     */
    int findT_airport_dyndepfltPageCount(PageInfo pageInfo);
    
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
