package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_check_in_counters;
import com.net.metadata.service.LogService;
import com.net.metadata.service.T_airport_check_in_countersService;
import com.net.metadata.utils.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * 值机柜台信息 Controller
 */
@Controller
@RequestMapping(value = "/t_airport_check_in_counters")
public class T_airport_check_in_countersController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(T_airport_check_in_countersController.class);

    @Autowired
    private T_airport_check_in_countersService t_airport_check_in_countersService;
    @Autowired
    private LogService logService;

    @GetMapping(value = {"/","/view","/manager"})
    public String view(){
        return "/basic/t_airport_check_in_counters";
    }

    @PostMapping(value = "/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(T_airport_check_in_counters entity, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = Maps.newHashMap();
        if (StringUtils.isNoneBlank(entity.getRemarks())) {
            condition.put("REMARKS", entity.getRemarks());
        }
        pageInfo.setCondition(condition);
        t_airport_check_in_countersService.findT_airport_check_in_countersDataGrid(pageInfo);
        return pageInfo;
    }

    @GetMapping(value = "/addPage")
    public String addPage(){
        return "/basic/t_airport_check_in_countersAdd";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(T_airport_check_in_counters entity) {
        Result result = new Result();
        try{
            T_airport_check_in_counters checkinids = t_airport_check_in_countersService.findT_airport_check_in_countersByCheckinid(entity.getCheckinid());
            T_airport_check_in_counters checkincountersids = t_airport_check_in_countersService.findT_airport_check_in_countersByCheckincountersid(entity.getCheckincountersid());
            if (checkinids!=null){
                result.setMsg("值机岛编号已存在!");
                return result;
            }if (checkincountersids!=null){
                result.setMsg("值机柜台编号已存在!");
                return result;
            }
            t_airport_check_in_countersService.addT_airport_check_in_counters(entity);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("添加值机柜台信息");
            sysLog.setOperationobj("值机岛编号:"+entity.getCheckinid()+",值机柜台编号:"+entity.getCheckincountersid());
            sysLog.setOptContent("成功添加值机岛编号:"+entity.getCheckinid()+",值机柜台编号:"+entity.getCheckincountersid()+",柜台状态:"+entity.getStatusbar()+",柜台属性:"+entity.getCheckinproperties()+",备注:"+entity.getRemarks()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e){
            logger.error("添加值机柜台信息失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @GetMapping(value = "/editPage")
    public String editPage(HttpSession session, HttpServletRequest request, Long id){
        T_airport_check_in_counters entity = t_airport_check_in_countersService.findT_airport_check_in_countersById(id);
        request.setAttribute("t_airport_check_in_counters", entity);
        session.setAttribute("checkinid", entity.getCheckinid());
        session.setAttribute("checkincountersid", entity.getCheckincountersid());
        session.setAttribute("statusbar", entity.getStatusbar());
        session.setAttribute("checkinproperties", entity.getCheckinproperties());
        session.setAttribute("remarks", entity.getRemarks());
        return "/basic/t_airport_check_in_countersEdit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result edit(HttpSession session, T_airport_check_in_counters entity) {
        Result result = new Result();
        try{
            t_airport_check_in_countersService.updateT_airport_check_in_counters(entity);
            result.setSuccess(true);
            result.setMsg("修改成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("修改值机柜台信息");
            sysLog.setOperationobj("值机岛编号:"+entity.getCheckinid()+",值机柜台编号:"+entity.getCheckincountersid());
            sysLog.setOptContent("修改前的值机岛编号:"+session.getAttribute("checkinid")+",值机柜台编号:"+session.getAttribute("checkincountersid")+",柜台状态:"+session.getAttribute("statusbar")+",柜台属性:"+session.getAttribute("checkinproperties")+",备注:"+session.getAttribute("remarks")+
                    "----成功修改值机岛编号:"+entity.getCheckinid()+",值机柜台编号:"+entity.getCheckincountersid()+",柜台状态:"+entity.getStatusbar()+",柜台属性:"+entity.getCheckinproperties()+",备注:"+entity.getRemarks()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        }  catch (RuntimeException | UnknownHostException e){
            logger.error("修改分拣转盘信息失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @PostMapping(value = {"/del","/delete"})
    @ResponseBody
    public Result del(Long id) {
        Result result = new Result();
        try {
            T_airport_check_in_counters t_airport_check_in_counters = t_airport_check_in_countersService.findT_airport_check_in_countersById(id);
            t_airport_check_in_countersService.delT_airport_check_in_counters(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("删除值机柜台信息");
            sysLog.setOperationobj("值机岛编号:"+t_airport_check_in_counters.getCheckinid()+",值机柜台编号:"+t_airport_check_in_counters.getCheckincountersid());
            sysLog.setOptContent("成功删除值机岛编号:"+t_airport_check_in_counters.getCheckinid()+",值机柜台编号:"+t_airport_check_in_counters.getCheckincountersid()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("删除值机柜台信息失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
