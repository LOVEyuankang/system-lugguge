package com.net.metadata.service;


import com.net.metadata.entity.T_airport_chute;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.Tree;

import java.util.List;

/**
 * @description：滑槽管理
 * @author：yxd
 * @date：2015/10/1 14:51
 */
public interface T_airport_chuteService {
	 /**
     * 查询滑槽树
     *
     * @return
     */
    List<Tree> findTree();
	 /**
     * 查询所有滑槽
     *
     * @return
     */
	void findT_airport_chuteDataGrid(PageInfo pageInfo);

    /**
     * 添加滑槽
     *
     * @param t_airport_chute
     */
    void addT_airport_chute(T_airport_chute t_airport_chute);


    /**
     * 修改滑槽
     *
     * @param t_airport_chute
     */
    void updateT_airport_chute(T_airport_chute t_airport_chute);

    /**
     * 根据id查询滑槽
     *
     * @param id
     * @return
     */
    T_airport_chute findT_airport_chuteById(Long ID);
    

    /**
     * 根据id删除滑槽
     *
     * @param id
     */
    void deleteT_airport_chuteById(Long ID);
    
    @SuppressWarnings("rawtypes")
	List findT_airport_chuteAll();
    
    /**
     * 判断id唯一性
     *
     * @param sql
     * @return
     */
    T_airport_chute findT_airport_chuteByCODE(String CODE);
}
