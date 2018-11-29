package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.Role;
import com.net.metadata.entity.SysLog;
import com.net.metadata.service.LogService;
import com.net.metadata.service.RoleService;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.Tree;
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
 * @description：角色管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private LogService logService;

	/**
	 * 角色管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/admin/role";
	}

	/**
	 * 角色列表
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(Role role, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();
		pageInfo.setCondition(condition);

		roleService.findDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 角色树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> tree() {
		return roleService.findTree();
	}

	/**
	 * 添加角色页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "/admin/roleAdd";
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Role role) {
		Result result = new Result();
		try {
			roleService.addRole(role);
			result.setSuccess(true);
			result.setMsg("添加成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("添加角色");
			sysLog.setOperationobj("角色:" + role.getName());
			sysLog.setOptContent("成功添加角色:" + role.getName() + ",排序号:"
					+ role.getSeq() + ",状态:" + role.getStatus() + ",备注:"
					+ role.getDescription() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("添加角色失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			Role role = roleService.findRoleById(id);
			roleService.deleteRoleById(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("删除角色");
			sysLog.setOperationobj("角色:" + role.getName());
			sysLog.setOptContent("成功删除角色:" + role.getName() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("删除角色失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 编辑角色页
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpSession session, HttpServletRequest request, Long id) {
		Role role = roleService.findRoleById(id);
		request.setAttribute("role", role);
		session.setAttribute("name", role.getName());
		session.setAttribute("seq", role.getSeq());
		session.setAttribute("status", role.getStatus());
		session.setAttribute("description", role.getDescription());
		return "/admin/roleEdit";
	}

	/**
	 * 编辑角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(HttpSession session, Role role) {
		Result result = new Result();
		try {
			roleService.updateRole(role);
			result.setSuccess(true);
			result.setMsg("编辑成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("修改角色");
			sysLog.setOperationobj("角色:" + role.getName());
			sysLog.setOptContent("修改前的角色:" + session.getAttribute("name")
					+ ",排序号:" + session.getAttribute("seq") + ",状态:"
					+ session.getAttribute("status") + ",描述:"
					+ session.getAttribute("description") + "----成功修改角色:"
					+ role.getName() + ",排序号:" + role.getSeq() + ",状态:"
					+ role.getStatus() + ",描述:" + role.getDescription() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("编辑角色失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 授权页面
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/grantPage")
	public String grantPage(HttpServletRequest request, Long id, Model model) {
		model.addAttribute("id", id);
		return "/admin/roleGrant";
	}

	/**
	 * 授权页面页面根据角色查询资源
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/findResourceIdListByRoleId")
	@ResponseBody
	public Result findResourceByRoleId(HttpServletRequest request, Long id, Model model) {
		Result result = new Result();
		try {
			List<Long> resources = roleService.findResourceIdListByRoleId(id);
			result.setSuccess(true);
			result.setObj(resources);
			return result;
		} catch (RuntimeException e) {
			logger.error("角色查询资源失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 授权
	 * 
	 * @param id
	 * @param resourceIds
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public Result grant(Long id, String resourceIds) {
		Result result = new Result();
		try {
			Role role = roleService.findRoleById(id);
			roleService.updateRoleResource(id, resourceIds);
			result.setMsg("授权成功！");
			result.setSuccess(true);
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("授权角色");
			sysLog.setOperationobj("角色:" + role.getName());
			sysLog.setOptContent("成功授权角色:" + role.getName() + "资源:"
					+ resourceIds + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("授权失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

}
