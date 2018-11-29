package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.entity.SysLog;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description：日志管理
 * @author：zhixuan.wang
 * @date：2015/10/30 18:06
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    private LogService logService;


    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/syslog";
    }

    
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(SysLog sysLog, Integer page, Integer rows) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = Maps.newHashMap();
        if(StringUtils.isNoneBlank(sysLog.getLoginName())){
            condition.put("loginName", sysLog.getLoginName());
        }
        pageInfo.setCondition(condition);
        logService.findDataGrid(pageInfo);
        return pageInfo;
    }
    
    /**
     * 编辑页
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
    	SysLog sysLog = logService.selectByPrimaryKey(id);
        request.setAttribute("sysLog", sysLog);
        return "/admin/syslogEdit";
    }
}
