package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_trailer;
import com.net.metadata.service.LogService;
import com.net.metadata.service.T_airport_trailerService;
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
@RequestMapping("/t_airport_trailer")
public class T_airport_trailerController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(T_airport_trailerController.class);

	@Autowired
	private T_airport_trailerService t_airport_trailerService;

	@Autowired
	private LogService logService;

	/**
	 * 拖车管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/basic/t_airport_trailer";
	}

	/**
	 * 拖车列表
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(T_airport_trailer t_airport_trailer, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();
		if (StringUtils.isNoneBlank(t_airport_trailer.getCode())) {
			condition.put("CODE", t_airport_trailer.getCode());
		}
		pageInfo.setCondition(condition);
		t_airport_trailerService.findT_airport_trailerDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 拖车树
	 * 
	 * @return
	 */
	// @RequestMapping(value = "/tree", method = RequestMethod.POST)
	// @ResponseBody
	// public List<Tree> tree() {
	// return roleService.findTree();
	// }

	/**
	 * 添加拖车页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "/basic/t_airport_trailerAdd";
	}

	/**
	 * 添加拖车
	 * 
	 * @param t_airport_trailer
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(T_airport_trailer t_airport_trailer) {
		Result result = new Result();
		T_airport_trailer cf = t_airport_trailerService.findT_airport_trailerByCODE(t_airport_trailer.getCode());
		if (cf != null && !cf.getId().equals(t_airport_trailer.getId())) {
			result.setMsg("拖车编号已存在!");
			return result;
		}
		try {
			t_airport_trailerService.addT_airport_trailer(t_airport_trailer);
			result.setSuccess(true);
			result.setMsg("添加成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("添加拖车");
			sysLog.setOperationobj("拖车:" + t_airport_trailer.getCode());
			sysLog.setOptContent("成功添加拖车编号:" + t_airport_trailer.getCode()
					+ ",拖车类型:" + t_airport_trailer.getType() + ",拖车状态:"
					+ t_airport_trailer.getStatus() + ",备注:"
					+ t_airport_trailer.getRemark() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("添加拖车失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 删除拖车
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			T_airport_trailer t_airport_trailer = t_airport_trailerService.findT_airport_trailerById(id);
			t_airport_trailerService.deleteT_airport_trailerById(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("删除拖车");
			sysLog.setOperationobj("拖车:" + t_airport_trailer.getCode());
			sysLog.setOptContent("成功删除拖车:" + t_airport_trailer.getCode() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("删除拖车失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 编辑拖车页
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpSession session, HttpServletRequest request, Long id) {
		T_airport_trailer t_airport_trailer = t_airport_trailerService .findT_airport_trailerById(id);
		request.setAttribute("t_airport_trailer", t_airport_trailer);
		session.setAttribute("CODE", t_airport_trailer.getCode());
		session.setAttribute("TYPE", t_airport_trailer.getType());
		session.setAttribute("STATUS", t_airport_trailer.getStatus());
		session.setAttribute("REMARK", t_airport_trailer.getRemark());
		return "/basic/t_airport_trailerEdit";
	}

	/**
	 * 编辑拖车
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(HttpSession session, T_airport_trailer t_airport_trailer) {
		Result result = new Result();
		T_airport_trailer cf = t_airport_trailerService.findT_airport_trailerByCODE(t_airport_trailer.getCode());
		if (cf != null && !cf.getId().equals(t_airport_trailer.getId())) {
			result.setMsg("拖车编号已存在!");
			return result;
		}
		try {
			t_airport_trailerService.updateT_airport_trailer(t_airport_trailer);
			result.setSuccess(true);
			result.setMsg("修改成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("修改拖车");
			sysLog.setOperationobj("拖车:" + t_airport_trailer.getCode());
			sysLog.setOptContent("修改前的拖车编号:" + session.getAttribute("CODE")
					+ ",排序号:" + session.getAttribute("TYPE") + ",状态:"
					+ session.getAttribute("STATUS") + ",备注:"
					+ session.getAttribute("REMARK") + "----成功修改拖车编号:"
					+ t_airport_trailer.getCode() + ",拖车类型:"
					+ t_airport_trailer.getType() + ",拖车状态:"
					+ t_airport_trailer.getStatus() + ",备注:"
					+ t_airport_trailer.getRemark() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("编辑拖车失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public List<T_airport_trailer> findAll() {
		return t_airport_trailerService.findT_airport_trailerAll();
	}
}
