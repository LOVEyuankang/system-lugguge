package com.net.metadata.mapper;


import com.net.metadata.entity.T_airport_trailer;
import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_trailerMapper {

	/**
     * 添加拖车
     *
     * @param t_airport_trailer
     * @return
     */
    int insert(T_airport_trailer t_airport_trailer);

    /**
     * 查询拖车列表分页
     *
     * @param pageInfo
     * @return
     */
    @SuppressWarnings("all")
	List findT_airport_trailerPageCondition(PageInfo pageInfo);

    /**
     * 拖车统计
     *
     * @param pageInfo
     * @return
     */
    int findT_airport_trailerPageCount(PageInfo pageInfo);

    /**
     * 拖车列表
     *
     * @return
     */
    List<T_airport_trailer> findT_airport_trailerAll();

    /**
     * 根据id查询拖车
     *
     * @param id
     * @return
     */
    T_airport_trailer findT_airport_trailerById(Long ID);

    /**
     * 修改拖车
     *
     * @param t_airport_trailer
     * @return
     */
    int updateT_airport_trailer(T_airport_trailer t_airport_trailer);

    /**
     * 删除拖车
     *
     * @param id
     * @return
     */
    int deleteT_airport_trailerById(Long ID);
    
    /**
     * 判断id唯一性
     *
     * @param sql
     * @return
     */
    T_airport_trailer findT_airport_trailerByCODE(String CODE);
}
