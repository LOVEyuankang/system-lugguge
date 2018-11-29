package com.net.metadata.mapper;


import com.net.metadata.entity.T_airport_chute;
import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_chuteMapper {

	/**
     * 添加滑槽
     *
     * @param t_airport_chute
     * @return
     */
    int insert(T_airport_chute t_airport_chute);

    /**
     * 查询滑槽列表分页
     *
     * @param pageInfo
     * @return
     */
    @SuppressWarnings("all")
	List findT_airport_chutePageCondition(PageInfo pageInfo);

    /**
     * 滑槽统计
     *
     * @param pageInfo
     * @return
     */
    int findT_airport_chutePageCount(PageInfo pageInfo);

    /**
     * 滑槽列表
     *
     * @return
     */
    List<T_airport_chute> findT_airport_chuteAll();

    /**
     * 根据id查询滑槽
     *
     * @param id
     * @return
     */
    T_airport_chute findT_airport_chuteById(Long ID);

    /**
     * 修改滑槽
     *
     * @param t_airport_chute
     * @return
     */
    int updateT_airport_chute(T_airport_chute t_airport_chute);

    /**
     * 删除滑槽
     *
     * @param id
     * @return
     */
    int deleteT_airport_chuteById(Long ID);
    
    /**
     * 判断id唯一性
     *
     * @param sql
     * @return
     */
    T_airport_chute findT_airport_chuteByCODE(String CODE);
}
