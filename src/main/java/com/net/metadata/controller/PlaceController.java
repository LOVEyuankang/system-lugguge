package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.Place;
import com.net.metadata.entity.SysLog;
import com.net.metadata.service.LogService;
import com.net.metadata.service.PlaceService;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.vo.Tree;
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
@RequestMapping("/place")
public class PlaceController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(PlaceController.class);

	@Autowired
	private PlaceService placeService;

	@Autowired
	private LogService logService;

	/**
	 * 地名管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/basic/place";
	}

	/**
	 * 地名列表
	 * 
	 * @param place
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(Place place, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();
		if (StringUtils.isNoneBlank(place.getESNAME())) {
			condition.put("ESNAME", place.getESNAME());
		}
		pageInfo.setCondition(condition);
		placeService.findPlaceDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 地名树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> tree() {
		return placeService.findTree();
	}

	/**
	 * 添加地名页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "/basic/placeAdd";
	}

	/**
	 * 添加地名
	 * 
	 * @param place
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Place place) {
		Result result = new Result();
		Place cf = placeService.findPlaceByplace_no(place.getPlace_no());
		if (cf != null && !cf.getPlace_no().equals(place.getPlace_no())) {
			result.setMsg("地名编号已存在!");
			return result;
		}
		try {
			placeService.addPlace(place);
			result.setSuccess(true);
			result.setMsg("添加成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("添加地名");
			sysLog.setOperationobj("地名:" + place.getCSNAME());
			sysLog.setOptContent("成功添加地名:" + place.getCSNAME() + ",三字码:"
					+ place.getESNAME() + ",四字码:" + place.getICAO()
					+ ",地名英文全称:" + place.getELNAME() + ",航管名称:"
					+ place.getCLNAME() + ",中文简称:" + place.getCSSNAME()
					+ ",国际标志:" + place.getGJBZ() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			result.setSuccess(false);
			result.setMsg("该地名三字码已存在！");
			return result;
		}
	}

	/**
	 * 删除地名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long place_no) {
		Result result = new Result();
		try {
			Place place = placeService.findPlaceById(place_no);
			placeService.deletePlaceById(place_no);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("删除地名");
			sysLog.setOperationobj("地名:" + place.getCSNAME());
			sysLog.setOptContent("成功删除地名:" + place.getCSNAME() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("删除地名失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 编辑地名页
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpSession session, HttpServletRequest request, Long place_no) {
		Place place = placeService.findPlaceById(place_no);
		request.setAttribute("place", place);
		session.setAttribute("place_no", place.getPlace_no());
		session.setAttribute("ESNAME", place.getESNAME());
		session.setAttribute("ICAO", place.getICAO());
		session.setAttribute("ELNAME", place.getELNAME());
		session.setAttribute("CSNAME", place.getCSNAME());
		session.setAttribute("CLNAME", place.getCLNAME());
		session.setAttribute("CSSNAME", place.getCSSNAME());
		session.setAttribute("GJBZ ", place.getGJBZ());
		return "/basic/placeEdit";
	}

	/**
	 * 编辑地名
	 * 
	 * @param place
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(HttpSession session, Place place) {
		Result result = new Result();
		Place cf = placeService.findPlaceByplace_no(place.getPlace_no());
		if (cf != null && !cf.getPlace_no().equals(place.getPlace_no())) {
			result.setMsg("地名编号已存在!");
			return result;
		}
		try {
			placeService.updatePlace(place);
			result.setSuccess(true);
			result.setMsg("修改成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("修改地名");
			sysLog.setOperationobj("地名:" + place.getCSNAME());
			sysLog.setOptContent("修改前的地名编号:" + session.getAttribute("place_no")
					+ ",三字码:" + session.getAttribute("ESNAME") + ",四字码:"
					+ session.getAttribute("ICAO") + ",地名英文全称:"
					+ session.getAttribute("ELNAME") + ",中文名称:"
					+ session.getAttribute("CSNAME") + ",航管名称:"
					+ session.getAttribute("CLNAME") + ",中文简称:"
					+ session.getAttribute("CSSNAME") + ",国际标志:"
					+ session.getAttribute("GJBZ") + "----成功修改地名编号:"
					+ place.getPlace_no() + ",三字码:" + place.getESNAME()
					+ ",四字码:" + place.getICAO() + ",地名英文全称::"
					+ place.getELNAME() + ",中文名称:" + place.getCSNAME()
					+ ",航管名称:" + place.getCLNAME() + ",中文简称:"
					+ place.getCSSNAME() + ",国际标志:" + place.getGJBZ() + "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			result.setSuccess(false);
			result.setMsg("该地名三字码已存在！");
			return result;
		}
	}

}
