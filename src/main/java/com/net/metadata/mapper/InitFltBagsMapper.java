package com.net.metadata.mapper;

import com.net.metadata.entity.InitFltBags;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InitFltBagsMapper {
	
	public List<InitFltBags> findInit(@Param("ip") String ip);

	public List<InitFltBags> findCounts(@Param("plandate") String plandate, @Param("aircorp") String aircorp, @Param("fltno") String fltno, @Param("code") String code);

}
