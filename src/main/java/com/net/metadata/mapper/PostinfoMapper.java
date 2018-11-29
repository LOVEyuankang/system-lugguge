package com.net.metadata.mapper;

import com.net.metadata.entity.Postinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostinfoMapper {
	/**
	 * 删除岗位
	 * 
	 * @param id
	 * @return
	 */
	int deletePostinfoById(Long id);

	/**
	 * 添加岗位
	 * 
	 * @param postinfo
	 * @return
	 */
	int insert(Postinfo postinfo);

	/**
	 * 修改岗位
	 * 
	 * @param postinfo
	 * @return
	 */
	int updatePostinfo(Postinfo postinfo);

	/**
	 * 根据岗位id查询岗位
	 * 
	 * @param id
	 * @return
	 */
	Postinfo findPostinfoById(Long id);

	/**
	 * 岗位列表
	 * 
	 * @param pageInfo
	 * @return
	 */

	List<Postinfo> findPostinfoAllByDeptid(@Param("deptid") String deptid);

}