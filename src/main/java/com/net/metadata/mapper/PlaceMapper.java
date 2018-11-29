package com.net.metadata.mapper;


import com.net.metadata.entity.Place;
import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceMapper {
	/**
     * 添加地名
     *
     * @param place
     * @return
     */
    int insert(Place place);

    /**
     * 查询地名列表分页
     *
     * @param pageInfo
     * @return
     */
    @SuppressWarnings("all")
	List findPlacePageCondition(PageInfo pageInfo);

    /**
     * 地名统计
     *
     * @param pageInfo
     * @return
     */
    int findPlacePageCount(PageInfo pageInfo);

    /**
     * 地名列表
     *
     * @return
     */
    List<Place> findPlaceAll();

    /**
     * 根据id查询地名
     *
     * @param place_no
     * @return
     */
    Place findPlaceById(Long place_no);

    /**
     * 修改地名
     *
     * @param place
     * @return
     */
    int updatePlace(Place place);

    /**
     * 删除地名
     *
     * @param place_no
     * @return
     */
    int deletePlaceById(Long place_no);
    
    /**
     * 判断id唯一性
     *
     * @param sql
     * @return
     */
    Place findPlaceByplace_no(Long place_no);
}
