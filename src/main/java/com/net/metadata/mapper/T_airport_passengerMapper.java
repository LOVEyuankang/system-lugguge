package com.net.metadata.mapper;


import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_passengerMapper {
    /**
     * 查询旅客值机列表分页
     *
     * @param pageInfo
     * @return
     */
    @SuppressWarnings("rawtypes")
	List findT_airport_passengerPageCondition(PageInfo pageInfo);

    /**
     * 旅客值机统计
     *
     * @param pageInfo
     * @return
     */
    int findT_airport_passengerPageCount(PageInfo pageInfo);
}
