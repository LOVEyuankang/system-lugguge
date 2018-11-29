package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.entity.T_airport_dyndepflt;
import com.net.metadata.service.T_airport_dyndepfltService;
import com.net.metadata.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/t_airport_dyndepflt")
public class T_airport_dyndepfltController {

	@Autowired
	private T_airport_dyndepfltService t_airport_dyndepfltService;

	/**
	 * 航班动态管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/basic/t_airport_dyndepflt";
	}

	/**
	 * 航班动态列表
	 * 
	 * @param t_airport_dyndepflt
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(T_airport_dyndepflt t_airport_dyndepflt, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();
		if (StringUtils.isNoneBlank(t_airport_dyndepflt.getFltno())) {
			condition.put("FLTNO", t_airport_dyndepflt.getFltno());
		}
		pageInfo.setCondition(condition);
		t_airport_dyndepfltService.findT_airport_dyndepfltDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 航班动态总数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/t_airport_dyndepfltAll", method = RequestMethod.POST)
	@ResponseBody
	public int t_airport_dyndepfltAll() {
		return t_airport_dyndepfltService.findT_airport_dyndepfltAll();
	}

	/**
	 * 航班动态时间段总数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findT_airport_dyndepfltAllbytime", method = RequestMethod.POST)
	@ResponseBody
	public List<Integer> findT_airport_dyndepfltAllbytime() {
		List<Integer> list = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		for (int i = 0; i <= hour; i++) {
			String ii = i < 10 ? "0" + String.valueOf(i) : String.valueOf(i);
			int res = t_airport_dyndepfltService.findT_airport_dyndepfltAllbytime(ii);
			list.add(res);
		}
		return list;
	}

	/**
	 * 已保障的航班
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findT_airport_dyndepfltAllbyStatus", method = RequestMethod.POST)
	@ResponseBody
	public int findT_airport_dyndepfltAllbyStatus() {
		return t_airport_dyndepfltService.findT_airport_dyndepfltAllbyStatus();
	}

	/**
	 * 已保障的时间段航班
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findT_airport_dyndepfltAllbyStatusbytime", method = RequestMethod.POST)
	@ResponseBody
	public List<Integer> findT_airport_dyndepfltAllbyStatusbytime() {
		List<Integer> list = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		for (int i = 0; i <= hour; i++) {
			String ii = i < 10 ? "0" + String.valueOf(i) : String.valueOf(i);
			int res = t_airport_dyndepfltService.findT_airport_dyndepfltAllbyStatusbytime(ii);
			list.add(res);
		}
		return list;
	}
}
