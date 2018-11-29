package com.net.metadata.mapper;

import com.net.metadata.utils.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Brs_luggage_viewMapper {
	
	@SuppressWarnings("rawtypes")
	List findTPageCondition(PageInfo pageInfo);

	int findPageCount(PageInfo pageInfo);
	
	@SuppressWarnings("rawtypes")
	List printSearch(@Param("plandate") String plandate, @Param("flightno") String flightno);
}