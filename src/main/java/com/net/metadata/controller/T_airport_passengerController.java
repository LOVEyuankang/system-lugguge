package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.entity.T_airport_passenger;
import com.net.metadata.service.T_airport_passengerService;
import com.net.metadata.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/t_airport_passenger")
public class T_airport_passengerController {

	@Autowired
    private T_airport_passengerService t_airport_passengerService;

    /**
     * 旅客值机管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/basic/t_airport_passenger";
    }

    /**
     * 旅客值机列表
     *
     * @param t_airport_passenger
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(T_airport_passenger t_airport_passenger, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = Maps.newHashMap();
        if(StringUtils.isNoneBlank(t_airport_passenger.getCode())){
            condition.put("CODE", t_airport_passenger.getCode());
        }
        pageInfo.setCondition(condition);
        t_airport_passengerService.findT_airport_passengerDataGrid(pageInfo);
        return pageInfo;
    }
}
