package com.net.metadata.mapper;

import com.net.metadata.entity.User;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User findUserByLoginName(String username);

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 用户列表
     *
     * @param pageInfo
     * @return
     */
    @SuppressWarnings("rawtypes")
	List findUserPageCondition(PageInfo pageInfo);

    /**
     * 统计用户
     *
     * @param pageInfo
     * @return
     */
    int findUserPageCount(PageInfo pageInfo);

    /**
     * 修改用户密码
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(@Param("userId") Long userId, @Param("pwd") String pwd);

    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */
    UserVo findUserVoById(Long id);
    
    @SuppressWarnings("rawtypes")
	List findAll();
}