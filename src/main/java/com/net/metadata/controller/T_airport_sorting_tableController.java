package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_sorting_table;
import com.net.metadata.service.LogService;
import com.net.metadata.service.T_airport_sorting_tableService;
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
 * 分拣转盘信息 Controller
 */
@Controller
@RequestMapping(value = "/t_airport_sorting_table")
public class T_airport_sorting_tableController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(T_airport_sorting_tableController.class);

    @Autowired
    private T_airport_sorting_tableService t_airport_sorting_tableService;
    @Autowired
    private LogService logService;

    @GetMapping(value = {"/","/view","/manager"})
    public String manager(){
        return "/basic/t_airport_sorting_table";
    }

    @PostMapping(value = "/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(T_airport_sorting_table t_airport_sorting_table, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = Maps.newHashMap();
        if (StringUtils.isNoneBlank(t_airport_sorting_table.getRemarks())) {
            condition.put("REMARKS", t_airport_sorting_table.getRemarks());
        }
        pageInfo.setCondition(condition);
        t_airport_sorting_tableService.findT_airport_sorting_tableDataGrid(pageInfo);
        return pageInfo;
    }

    @GetMapping(value = "/addPage")
    public String addPage(){
        return "/basic/t_airport_sorting_tableAdd";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(T_airport_sorting_table t_airport_sorting_table) {
        Result result = new Result();
        try{
            T_airport_sorting_table entity = t_airport_sorting_tableService.findT_airport_sorting_tableBySortingtableid(t_airport_sorting_table.getSortingtableid());
            if (entity!=null){
                result.setMsg("分拣转盘编号已存在!");
                return result;
            }
            t_airport_sorting_tableService.addT_airport_sorting_table(t_airport_sorting_table);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("添加分拣转盘信息");
            sysLog.setOperationobj("分拣转盘编号:"+t_airport_sorting_table.getSortingtableid());
            sysLog.setOptContent("成功添加分拣转盘编号:"+t_airport_sorting_table.getSortingtableid()+",分拣转盘状态:"+t_airport_sorting_table.getSortingtablestatusbar()+",分拣转盘属性:"+t_airport_sorting_table.getSortingtableproperties()+",备注:"+t_airport_sorting_table.getRemarks()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e){
            logger.error("添加分拣转盘信息失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @GetMapping(value = "/editPage")
    public String editPage(HttpSession session, HttpServletRequest request, Long id){
        T_airport_sorting_table entity = t_airport_sorting_tableService.findT_airport_sorting_tableById(id);
        request.setAttribute("t_airport_sorting_table", entity);
        session.setAttribute("sortingtableid",entity.getSortingtableid());
        session.setAttribute("sortingtablestatusbar",entity.getSortingtablestatusbar());
        session.setAttribute("sortingtableproperties",entity.getSortingtableproperties());
        session.setAttribute("remarks",entity.getRemarks());
        return "/basic/t_airport_sorting_tableEdit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result edit(HttpSession session, T_airport_sorting_table t_airport_sorting_table) {
        Result result = new Result();
        try{
            t_airport_sorting_tableService.updateT_airport_sorting_table(t_airport_sorting_table);
            result.setSuccess(true);
            result.setMsg("修改成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("修改分拣转盘信息");
            sysLog.setOperationobj("分拣转盘编号:"+t_airport_sorting_table.getSortingtableid());
            sysLog.setOptContent("修改前的分拣转盘编号:"+session.getAttribute("sortingtableid")+",分拣转盘状态:"+session.getAttribute("sortingtablestatusbar")+",分拣转盘属性:"+session.getAttribute("sortingtableproperties")+",备注:"+session.getAttribute("remarks")+
                    "----成功修改分拣转盘编号:"+t_airport_sorting_table.getSortingtableid()+",分拣转盘状态:"+t_airport_sorting_table.getSortingtablestatusbar()+",分拣转盘属性:"+t_airport_sorting_table.getSortingtableproperties()+",备注:"+t_airport_sorting_table.getRemarks()+"!");
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
            T_airport_sorting_table t_airport_sorting_tableById = t_airport_sorting_tableService.findT_airport_sorting_tableById(id);
            t_airport_sorting_tableService.delT_airport_sorting_table(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("删除分拣转盘信息");
            sysLog.setOperationobj("分拣转盘编号:"+t_airport_sorting_tableById.getSortingtableid());
            sysLog.setOptContent("成功删除分拣转盘编号:"+t_airport_sorting_tableById.getSortingtableid()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("删除分拣转盘信息失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
