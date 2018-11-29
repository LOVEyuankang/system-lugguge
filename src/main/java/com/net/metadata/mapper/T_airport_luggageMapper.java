package com.net.metadata.mapper;


import com.net.metadata.entity.T_airport_luggage;
import com.net.metadata.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface T_airport_luggageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(T_airport_luggage record);

    int insertSelective(T_airport_luggage record);

    T_airport_luggage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T_airport_luggage record);

    int updateByPrimaryKey(T_airport_luggage record);
    @SuppressWarnings("all")
    List findTPageCondition(PageInfo pageInfo);
    
    int findPageCount(PageInfo pageInfo);
    
    @SuppressWarnings("rawtypes")
    List findByBgcode(String bgcode);
    
    int findT_airport_luggageAll();
    
    int findT_airport_luggageAllbytime(String ti);
    
    int findT_airport_luggageAllbystutas();
    
    int findT_airport_luggageAllbystutastime(String ti);
    
    int findT_airport_luggageAllbystutasw();
    
    int findT_airport_luggageAllbystutaswtime(String ti);
}
