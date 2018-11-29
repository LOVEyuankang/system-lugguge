package com.net.metadata.mapper;

import com.net.metadata.entity.Chute;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChutesMapper {

	List<Chute> findAll();
	
	Chute findByCode(@Param("code") String code);
}
