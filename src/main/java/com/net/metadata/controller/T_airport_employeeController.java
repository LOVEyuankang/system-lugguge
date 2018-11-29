package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_employee;
import com.net.metadata.service.LogService;
import com.net.metadata.service.T_airport_employeeService;
import com.net.metadata.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/t_airport_employee")
public class T_airport_employeeController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(T_airport_employeeController.class);

	@Autowired
	private T_airport_employeeService t_airport_employeeService;

	@Autowired
	private LogService logService;

	/**
	 * 操作员管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/basic/t_airport_employee";
	}

	/**
	 * 操作员列表
	 * 
	 * @param t_airport_employee
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(T_airport_employee t_airport_employee, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();
		if (StringUtils.isNoneBlank(t_airport_employee.getCode())) {
			condition.put("CODE", t_airport_employee.getCode());
		}
		pageInfo.setCondition(condition);
		t_airport_employeeService.findT_airport_employeeDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 操作员树
	 * 
	 * @return
	 */
	// @RequestMapping(value = "/tree", method = RequestMethod.POST)
	// @ResponseBody
	// public List<Tree> tree() {
	// return roleService.findTree();
	// }

	/**
	 * 添加操作员页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "/basic/t_airport_employeeAdd";
	}

	/**
	 * 添加操作员
	 * 
	 * @param t_airport_employee
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(T_airport_employee t_airport_employee) {
		Result result = new Result();
		T_airport_employee cf = t_airport_employeeService.findT_airport_employeeByCODE(t_airport_employee.getCode());
		if (cf != null && !cf.getId().equals(t_airport_employee.getId())) {
			result.setMsg("操作员编号已存在!");
			return result;
		}
		try {
			t_airport_employeeService.addT_airport_employee(t_airport_employee);
			result.setSuccess(true);
			result.setMsg("添加成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("添加操作员");
			sysLog.setOperationobj("操作员:" + t_airport_employee.getCode());
			sysLog.setOptContent("成功添加操作员编号:" + t_airport_employee.getCode()
					+ ",操作员姓名:" + t_airport_employee.getName() + ",所属单位:"
					+ t_airport_employee.getCorpation() + ",所属部门:"
					+ t_airport_employee.getDepartment() + ",所属岗位:"
					+ t_airport_employee.getStation() + ",状态:"
					+ t_airport_employee.getStatus() + ",员工指纹:"
					+ t_airport_employee.getFingerprint() + ",APP登录用户:"
					+ t_airport_employee.getLoginusr() + ",APP登录密码:"
					+ t_airport_employee.getLoginpwd() + ",权限级别:"
					+ t_airport_employee.getRightlevel() + ",备注:"
					+ t_airport_employee.getRemark() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("添加操作员失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 删除操作员
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			T_airport_employee t_airport_employee = t_airport_employeeService.findT_airport_employeeById(id);
			t_airport_employeeService.deleteT_airport_employeeById(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("删除操作员");
			sysLog.setOperationobj("操作员:" + t_airport_employee.getCode());
			sysLog.setOptContent("成功删除操作员:" + t_airport_employee.getCode() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("删除操作员失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 编辑操作员页
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpSession session, HttpServletRequest request, Long id) {
		T_airport_employee t_airport_employee = t_airport_employeeService.findT_airport_employeeById(id);
		request.setAttribute("t_airport_employee", t_airport_employee);
		session.setAttribute("CODE", t_airport_employee.getCode());
		session.setAttribute("NAME", t_airport_employee.getName());
		session.setAttribute("CORPATION", t_airport_employee.getCorpation());
		session.setAttribute("DEPARTMENT", t_airport_employee.getDepartment());
		session.setAttribute("STATION", t_airport_employee.getStation());
		session.setAttribute("STATUS", t_airport_employee.getStatus());
		session.setAttribute("FINGERPRINT", t_airport_employee.getFingerprint());
		session.setAttribute("LOGINUSR", t_airport_employee.getLoginusr());
		session.setAttribute("LOGINPWD", t_airport_employee.getLoginpwd());
		session.setAttribute("RIGHTLEVEL", t_airport_employee.getRightlevel());
		session.setAttribute("REMARK", t_airport_employee.getRemark());
		return "/basic/t_airport_employeeEdit";
	}

	/**
	 * 编辑操作员
	 * 
	 * @param t_airport_employee
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(HttpSession session, T_airport_employee t_airport_employee) {
		Result result = new Result();
		T_airport_employee cf = t_airport_employeeService.findT_airport_employeeByCODE(t_airport_employee.getCode());
		if (cf != null && !cf.getId().equals(t_airport_employee.getId())) {
			result.setMsg("操作员编号已存在!");
			return result;
		}
		try {
			t_airport_employeeService.updateT_airport_employee(t_airport_employee);
			result.setSuccess(true);
			result.setMsg("修改成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("修改操作员");
			sysLog.setOperationobj("操作员:" + t_airport_employee.getCode());
			sysLog.setOptContent("修改前的操作员编号:" + session.getAttribute("CODE")
					+ ",操作员姓名:" + session.getAttribute("NAME") + ",所属单位:"
					+ session.getAttribute("CORPATION") + ",所属部门:"
					+ session.getAttribute("DEPARTMENT") + ",所属岗位:"
					+ session.getAttribute("STATION") + ",状态:"
					+ session.getAttribute("STATUS") + ",员工指纹:"
					+ session.getAttribute("FINGERPRINT") + ",APP登录用户:"
					+ session.getAttribute("LOGINUSR") + ",APP登录密码:"
					+ session.getAttribute("LOGINPWD") + ",权限级别:"
					+ session.getAttribute("RIGHTLEVEL") + ",备注:"
					+ session.getAttribute("REMARK") + "----成功修改操作员编号:"
					+ t_airport_employee.getCode() + ",操作员姓名:"
					+ t_airport_employee.getName() + ",所属单位:"
					+ t_airport_employee.getCorpation() + ",所属部门:"
					+ t_airport_employee.getDepartment() + ",所属岗位:"
					+ t_airport_employee.getStation() + ",状态:"
					+ t_airport_employee.getStatus() + ",员工指纹:"
					+ t_airport_employee.getFingerprint() + ",APP登录用户:"
					+ t_airport_employee.getLoginusr() + ",APP登录密码:"
					+ t_airport_employee.getLoginpwd() + ",权限级别:"
					+ t_airport_employee.getRightlevel() + ",备注:"
					+ t_airport_employee.getRemark() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("编辑操作员失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public List<T_airport_employee> findAll() {
		return t_airport_employeeService.findAllT_airport_employee();
	}
}
