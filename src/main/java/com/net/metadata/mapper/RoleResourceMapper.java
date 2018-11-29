package com.net.metadata.mapper;

import com.net.metadata.entity.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceMapper {
	/**
	 * 添加角色资源关联
	 * 
	 * @param roleResource
	 * @return
	 */
	int insert(RoleResource roleResource);

	/**
	 * 根据角色id查询角色资源关联列表
	 * 
	 * @param id
	 * @return
	 */
	List<RoleResource> findRoleResourceIdListByRoleId(Long id);

	/**
	 * 删除角色资源关联关系
	 * 
	 * @param roleResourceId
	 * @return
	 */
	int deleteById(Long roleResourceId);
}