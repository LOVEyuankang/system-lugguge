package com.net.metadata.mapper;


import com.net.metadata.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {

    int insert(UserRole userRole);

    int updateByPrimaryKeySelective(UserRole userRole);

    List<UserRole> findUserRoleByUserId(Long userId);

    int deleteById(Long id);

    List<Long> findRoleIdListByUserId(Long userId);
}