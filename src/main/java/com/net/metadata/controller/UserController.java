package com.net.metadata.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.Role;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.User;
import com.net.metadata.service.LogService;
import com.net.metadata.service.UserService;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * @description：用户管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    

	@Autowired
	private LogService logService;

    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/user";
    }

    /**
     * 用户管理列表
     *
     * @param userVo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = Maps.newHashMap();

        if (StringUtils.isNoneBlank(userVo.getName())) {
            condition.put("name", userVo.getName());
        }
        if (userVo.getOrganizationId() != null) {
            condition.put("organizationId", userVo.getOrganizationId());
        }
        if (userVo.getCreatedateStart() != null) {
            condition.put("startTime", userVo.getCreatedateStart());
        }
        if (userVo.getCreatedateEnd() != null) {
            condition.put("endTime", userVo.getCreatedateEnd());
        }
        pageInfo.setCondition(condition);
        userService.findDataGrid(pageInfo);
        return pageInfo;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){
    	@SuppressWarnings("unchecked")
		List<User> list=userService.findAllUser();
    	return list;
    }
    
    /**
     * 添加用户页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "/admin/userAdd";
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(UserVo userVo) {
        Result result = new Result();
        User u = userService.findUserByLoginName(userVo.getLoginname());
        if (u != null) {
            result.setMsg("用户名已存在!");
            return result;
        }
        try {
            userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
            userService.addUser(userVo);
            result.setSuccess(true);
            result.setMsg("添加成功");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("添加用户");
            sysLog.setOperationobj("用户:"+userVo.getName());
            sysLog.setOptContent("成功添加用户:"+userVo.getName()+",登录名:"+userVo.getLoginname()+",密码:"+userVo.getPassword()+",性别:"+userVo.getSex()+",年龄:"+userVo.getAge()+",用户类型:"+userVo.getUsertype()+",部门:"+userVo.getOrganizationName()+",角色:"+userVo.getRoleIds()+",电话:"+userVo.getPhone()+",用户状态:"+userVo.getStatus()+"!");
            sysLog.setClientIp(this.getIp());
            LOGGER.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            LOGGER.error("添加用户失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 编辑用户页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpSession session, Long id, Model model) {
        UserVo userVo = userService.findUserVoById(id);
        List<Role> rolesList = userVo.getRolesList();
        List<Long> ids = Lists.newArrayList();
        for (Role role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        session.setAttribute("loginname", userVo.getLoginname());
        session.setAttribute("name", userVo.getName());
        session.setAttribute("password", userVo.getPassword());
        session.setAttribute("organizationName", userVo.getOrganizationId());
        session.setAttribute("createdate", userVo.getCreatedate());
        session.setAttribute("sex", userVo.getSex());
        session.setAttribute("age", userVo.getAge());
        session.setAttribute("phone", userVo.getPhone());
        session.setAttribute("roleIds", ids);
        session.setAttribute("usertype", userVo.getUsertype());
        session.setAttribute("status", userVo.getStatus());
        return "/admin/userEdit";
    }

    /**
     * 编辑用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(HttpSession session, UserVo userVo) {
        Result result = new Result();
        User user = userService.findUserByLoginName(userVo.getLoginname());
        if (user != null && !user.getId().equals(userVo.getId())) {
            result.setMsg("用户名已存在!");
            return result;
        }
        try {
            userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
            userService.updateUser(userVo);
            result.setSuccess(true);
            result.setMsg("修改成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("修改用户");
            sysLog.setOperationobj("用户:"+userVo.getName());
            sysLog.setOptContent("修改前的用户:"+session.getAttribute("name")+",登录名:"+session.getAttribute("loginname")+",密码:"+session.getAttribute("password")+",性别:"+session.getAttribute("sex")+",年龄:"+session.getAttribute("age")+",用户类型:"+session.getAttribute("usertype")+",部门:"+session.getAttribute("organizationName")+",角色:"+session.getAttribute("roleIds")+",电话:"+session.getAttribute("phone")+",用户状态:"+session.getAttribute("status")+
            "----成功修改用户:"+userVo.getName()+",登录名:"+userVo.getLoginname()+",密码:"+userVo.getPassword()+",性别:"+userVo.getSex()+",年龄:"+userVo.getAge()+",用户类型:"+userVo.getUsertype()+",部门:"+userVo.getOrganizationId()+",角色:"+userVo.getRoleIds()+",电话:"+userVo.getPhone()+",用户状态:"+userVo.getStatus()+"!");
            sysLog.setClientIp(this.getIp());
            LOGGER.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            LOGGER.error("修改用户失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 修改密码页
     *
     * @return
     */
    @RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
    public String editPwdPage() {
        return "/admin/userEditPwd";
    }

    /**
     * 修改密码
     *
     * @param request
     * @param oldPwd
     * @param pwd
     * @return
     */
    @RequestMapping("/editUserPwd")
    @ResponseBody
    public Result editUserPwd(HttpServletRequest request, String oldPwd, String pwd) {
        Result result = new Result();
        if (!getCurrentUser().getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
            result.setMsg("老密码不正确!");
            return result;
        }

        try {
            userService.updateUserPwdById(getUserId(), DigestUtils.md5Hex(pwd));
            result.setSuccess(true);
            result.setMsg("密码修改成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("修改密码");
            sysLog.setOperationobj("修改"+this.getStaffName()+"的密码");
            sysLog.setOptContent("成功修改老密码:"+DigestUtils.md5Hex(oldPwd)+"新密码:"+pwd+"!");
            sysLog.setClientIp(this.getIp());
            LOGGER.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (Exception e) {
            LOGGER.error("修改密码失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
        	UserVo userVo=userService.findUserVoById(id);
            userService.deleteUserById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("删除用户");
            sysLog.setOperationobj("用户:"+userVo.getName());
            sysLog.setOptContent("成功删除用户:"+userVo.getName()+"!");
            sysLog.setClientIp(this.getIp());
            LOGGER.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            LOGGER.error("删除用户失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
}
