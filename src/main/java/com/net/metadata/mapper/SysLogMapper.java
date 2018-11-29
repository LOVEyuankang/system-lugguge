package com.net.metadata.mapper;


import com.net.metadata.entity.SysLog;
import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SysLog record);

	int insertSelective(SysLog record);

	SysLog selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SysLog record);

	int updateByPrimaryKey(SysLog record);

	@SuppressWarnings("rawtypes")
	List findDataGrid(PageInfo pageInfo);

	int findDataGridCount(PageInfo pageInfo);
}