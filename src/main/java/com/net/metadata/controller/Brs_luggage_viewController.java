package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.entity.Brs_luggage_view;
import com.net.metadata.entity.PrintSearch;
import com.net.metadata.service.Brs_luggage_viewService;
import com.net.metadata.service.LogService;
import com.net.metadata.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/brs_luggage_view")
public class Brs_luggage_viewController {

	private static Logger logger = LoggerFactory.getLogger(Brs_luggage_viewController.class);
	@Autowired
	private Brs_luggage_viewService brs_luggage_viewService;
	@Autowired
	private LogService logService;

	/*
      * 
      */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/luggage/brs_luggage_view";
	}

	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(Brs_luggage_view brs_luggage_view, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();
		// 正常查询
		if (StringUtils.isNoneBlank(brs_luggage_view.getRemartk())) {
			condition.put("remark", brs_luggage_view.getRemartk());
		}
		if (StringUtils.isNoneBlank(brs_luggage_view.getPlandate())) {
			condition.put("plandate", brs_luggage_view.getPlandate());
		}
		if (StringUtils.isNoneBlank(brs_luggage_view.getFlightno())) {
			condition.put("flightno", brs_luggage_view.getFlightno());
		}
		if (StringUtils.isNoneBlank(brs_luggage_view.getLkname())) {
			condition.put("lkname", brs_luggage_view.getLkname());
		}
		if (StringUtils.isNoneBlank(brs_luggage_view.getPercode())) {
			condition.put("percode", brs_luggage_view.getPercode());
		}
		if (StringUtils.isNoneBlank(brs_luggage_view.getBgcode())) {
			condition.put("bgcode", brs_luggage_view.getBgcode());
		}
		pageInfo.setCondition(condition);
		brs_luggage_viewService.findBrs_luggage_ViewDataGrid(pageInfo);
		return pageInfo;
	}

	@RequestMapping("/printSearch")
	@ResponseBody
	public PrintSearch printSearch(String plandate, String flightno) {
		PrintSearch ps = new PrintSearch();
		@SuppressWarnings("unchecked")
		List<Brs_luggage_view> list = brs_luggage_viewService.printSearch(plandate, flightno);
		Map<String, List<Brs_luggage_view>> map = new HashMap<String, List<Brs_luggage_view>>();
		if (list.size() > 0) {
			for (Brs_luggage_view b : list) {
				String key = "空";
				if (StringUtils.isNoneBlank(b.getTrailercode())) {
					key = b.getTrailercode();
				}
				if (map.containsKey(key)) {
					map.get(key).add(b);
				} else {
					List<Brs_luggage_view> l = new ArrayList<Brs_luggage_view>();
					l.add(b);
					map.put(key, l);
				}
			}
		}
		ps.setFlightno(list.get(0).getFlightno());
		ps.setXl_total(list.size());
		ps.setCl_total(map.size());
		ps.setTime("");
		ps.setMap(map);
		return ps;
	}
}
