package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_warning_threshold;
import com.net.metadata.redis.RedisService;
import com.net.metadata.service.LogService;
import com.net.metadata.service.T_airport_warning_thresholdService;
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
 * 预警阀值管理 Controller
 */
@Controller
@RequestMapping(value = "/t_airport_warning_threshold")
public class T_airport_warning_thresholdController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(T_airport_warning_thresholdController.class);

    @Autowired
    private T_airport_warning_thresholdService t_airport_warning_thresholdService;
    @Autowired
    private LogService logService;
    @Autowired
    private RedisService redisService;

    @GetMapping(value = {"/","/view","/manager"})
    public String manager(){
        return "/basic/t_airport_warning_threshold";
    }

    @PostMapping(value = "/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(T_airport_warning_threshold entity, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = Maps.newHashMap();
        if (StringUtils.isNoneBlank(entity.getRemarks())) {
            condition.put("REMARKS", entity.getRemarks());
        }
        pageInfo.setCondition(condition);
        t_airport_warning_thresholdService.findT_airport_warning_thresholdDataGrid(pageInfo);
        String dataGrid2 = redisService.getList("dataGrid2");
        if(dataGrid2==null){
            redisService.setList("dataGrid2",pageInfo.getRows().toString());
            System.out.println("从数据库查询");
        }else{
            System.out.println("redis缓存："+dataGrid2);
        }
        return pageInfo;
    }

    @GetMapping(value = "/addPage")
    public String addPage(){
        return "/basic/t_airport_warning_thresholdAdd";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(T_airport_warning_threshold entity) {
        Result result = new Result();
        try{
            t_airport_warning_thresholdService.addT_airport_warning_threshold(entity);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("添加预警阀值信息");
            sysLog.setOperationobj("预警阀值类型:"+entity.getWarningthresholdtype());
            sysLog.setOptContent("成功添加预警阀值类型:"+entity.getWarningthresholdtype()+",预警阀值时间设置:"+entity.getUnitssetup()+" 分钟,备注:"+entity.getRemarks()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e){
            logger.error("添加预警阀值信息失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @GetMapping(value = "/editPage")
    public String editPage(HttpSession session, HttpServletRequest request, Long id){
        T_airport_warning_threshold entity = t_airport_warning_thresholdService.findT_airport_warning_thresholdById(id);
        request.setAttribute("t_airport_warning_threshold", entity);
        session.setAttribute("warningthresholdtype",entity.getWarningthresholdtype());
        session.setAttribute("unitssetup",entity.getUnitssetup());
        session.setAttribute("remarks",entity.getRemarks());
        return "/basic/t_airport_warning_thresholdEdit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result edit(HttpSession session, T_airport_warning_threshold entity) {
        Result result = new Result();
        try{
            t_airport_warning_thresholdService.updateT_airport_warning_threshold(entity);
            result.setSuccess(true);
            result.setMsg("修改成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("修改预警阀值信息");
            sysLog.setOperationobj("预警阀值类型:"+entity.getWarningthresholdtype());
            sysLog.setOptContent("修改前的预警阀值类型:"+session.getAttribute("warningthresholdtype")+",预警阀值时间设置:"+session.getAttribute("unitssetup")+" 分钟,备注:"+session.getAttribute("remarks")+
                    "----成功修改预警阀值类型:"+entity.getWarningthresholdtype()+",预警阀值时间设置:"+entity.getUnitssetup()+" 分钟,备注:"+entity.getRemarks()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        }  catch (RuntimeException | UnknownHostException e){
            logger.error("修改预警阀值信息失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @PostMapping(value = {"/del","/delete"})
    @ResponseBody
    public Result del(Long id) {
        Result result = new Result();
        try {
            T_airport_warning_threshold entity = t_airport_warning_thresholdService.findT_airport_warning_thresholdById(id);
            t_airport_warning_thresholdService.delT_airport_warning_threshold(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("删除预警阀值信息");
            sysLog.setOperationobj("预警阀值类型:"+entity.getWarningthresholdtype());
            sysLog.setOptContent("成功删除预警阀值类型:"+entity.getWarningthresholdtype()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("删除预警阀值信息失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }


}
