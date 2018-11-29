package com.net.metadata.service;


import com.net.metadata.entity.Place;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.Tree;

import java.util.List;

/**
 * @description：地名管理
 * @author：yxd
 * @date：2015/10/1 14:51
 */
public interface PlaceService {
	 /**
     * 查询地名树
     *
     * @return
     */
    List<Tree> findTree();
	 /**
     * 查询所有地名
     *
     * @return
     */
	void findPlaceDataGrid(PageInfo pageInfo);

    /**
     * 添加地名
     *
     * @param place
     */
    void addPlace(Place place);


    /**
     * 修改地名
     *
     * @param place
     */
    void updatePlace(Place place);

    /**
     * 根据id查询地名
     *
     * @param place_no
     * @return
     */
    Place findPlaceById(Long place_no);
    

    /**
     * 根据id删除地名
     *
     * @param place_no
     */
    void deletePlaceById(Long place_no);
    
    @SuppressWarnings("rawtypes")
	List findPlaceAll();
    
    /**
     * 判断id唯一性
     *
     * @param sql
     * @return
     */
    Place findPlaceByplace_no(Long place_no);
}
