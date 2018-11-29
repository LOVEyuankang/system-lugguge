package com.net.metadata.mapper;

import com.net.metadata.entity.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationMapper {
	/**
	 * 删除部门
	 * 
	 * @param id
	 * @return
	 */
	int deleteOrganizationById(Long id);

	/**
	 * 添加部门
	 * 
	 * @param organization
	 * @return
	 */
	int insert(Organization organization);

	/**
	 * 更新部门
	 * 
	 * @param organization
	 * @return
	 */
	int updateOrganization(Organization organization);

	/**
	 * 查询一级部门
	 * 
	 * @return
	 */
	List<Organization> findOrganizationAllByPidNull();

	/**
	 * 查询部门子集
	 * 
	 * @param pid
	 * @return
	 */
	List<Organization> findOrganizationAllByPid(Long pid);

	/**
	 * 查询所有部门集合
	 * 
	 * @return
	 */
	List<Organization> findOrganizationAll();

	/**
	 * 根据id查询部门
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganizationById(Long id);

	Organization findOrganizationBycode(String code);

}