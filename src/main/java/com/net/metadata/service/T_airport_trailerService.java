package com.net.metadata.service;


import com.net.metadata.entity.T_airport_trailer;
import com.net.metadata.utils.PageInfo;

import java.util.List;

/**
 * @description：拖车管理
 * @author：yxd
 * @date：2015/10/1 14:51
 */
public interface T_airport_trailerService {
	 /**
     * 查询所有拖车
     *
     * @return
     */
	void findT_airport_trailerDataGrid(PageInfo pageInfo);

    /**
     * 添加拖车
     *
     * @param t_airport_trailer
     */
    void addT_airport_trailer(T_airport_trailer t_airport_trailer);


    /**
     * 修改拖车
     *
     * @param t_airport_trailer
     */
    void updateT_airport_trailer(T_airport_trailer t_airport_trailer);

    /**
     * 根据id查询拖车
     *
     * @param id
     * @return
     */
    T_airport_trailer findT_airport_trailerById(Long ID);
    

    /**
     * 根据id删除拖车
     *
     * @param id
     */
    void deleteT_airport_trailerById(Long ID);
    
    /**
     * 判断id唯一性
     *
     * @param sql
     * @return
     */
    T_airport_trailer findT_airport_trailerByCODE(String CODE);
    
    List<T_airport_trailer> findT_airport_trailerAll();
}
