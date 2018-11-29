package com.net.metadata.service.impl;

import com.net.metadata.entity.User;
import com.net.metadata.entity.UserRole;
import com.net.metadata.mapper.UserMapper;
import com.net.metadata.mapper.UserRoleMapper;
import com.net.metadata.service.UserService;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.UserVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public User findUserByLoginName(String username) {
		return userMapper.findUserByLoginName(username);
	}

	@Override
	public User findUserById(Long id) {
		return userMapper.findUserById(id);
	}

	@Override
	public void findDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(userMapper.findUserPageCondition(pageInfo));
		pageInfo.setTotal(userMapper.findUserPageCount(pageInfo));
	}

	@Override
	public void addUser(UserVo userVo) {
		User user = new User();
		try {
			PropertyUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			LOGGER.error("类转换异常：{}", e);
			throw new RuntimeException("类型转换异常：{}", e);
		}
		userMapper.insert(user);
		User userByLoginName = userMapper.findUserByLoginName(user.getName());//查询已添加的用户ID
		Long id = userByLoginName.getId();
		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();

		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
	}

	@Override
	public void updateUserPwdById(Long userId, String pwd) {
		userMapper.updateUserPwdById(userId, pwd);
	}

	@Override
	public UserVo findUserVoById(Long id) {
		return userMapper.findUserVoById(id);
	}

	@Override
	public void updateUser(UserVo userVo) {
		User user = new User();
		try {
			PropertyUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			LOGGER.error("类转换异常：{}", e);
			throw new RuntimeException("类型转换异常：{}", e);
		}
		userMapper.updateUser(user);
		Long id = userVo.getId();
		List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
		if (userRoles != null && (!userRoles.isEmpty())) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}

		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}

	}

	@Override
	public void deleteUserById(Long id) {
		userMapper.deleteById(id);
		List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
		if (userRoles != null && (!userRoles.isEmpty())) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAllUser() {
		return userMapper.findAll();
	}

}
