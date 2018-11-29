package com.net.metadata.mapper;

import com.net.metadata.entity.T_airport_dynchute;
import com.net.metadata.utils.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_dynchuteMapper {

    /**
     * 查询滑槽分配列表分页
     *
     * @param pageInfo
     * @return
     */
    @SuppressWarnings("rawtypes")
	List findT_airport_dynchutePageCondition(PageInfo pageInfo);

    /**
     * 滑槽分配统计
     *
     * @param pageInfo
     * @return
     */
    int findT_airport_dynchutePageCount(PageInfo pageInfo);
    
    T_airport_dynchute findT_airport_dynchuteByID(@Param("id") Long id);
    
    @SuppressWarnings("rawtypes")
	List findT_airport_dynchuteByCondition(@Param("plandate") String plandate, @Param("aircorp") String aircorp, @Param("fltno") String fltno, @Param("attr") String attr);

    void updateT_airport_dynchute(@Param("id") Long id);
}


