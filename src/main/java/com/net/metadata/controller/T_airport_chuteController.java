package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_chute;
import com.net.metadata.service.LogService;
import com.net.metadata.service.T_airport_chuteService;
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
@RequestMapping("/t_airport_chute")
public class T_airport_chuteController extends BaseController{

	  private static Logger logger = LoggerFactory.getLogger(T_airport_chuteController.class);

	    @Autowired
	    private T_airport_chuteService t_airport_chuteService;
	    
	    @Autowired
		private LogService logService;

	    /**
	     * 滑槽管理页
	     *
	     * @return
	     */
	    @RequestMapping(value = "/manager", method = RequestMethod.GET)
	    public String manager() {
	        return "/basic/t_airport_chute";
	    }

	    /**
	     * 滑槽列表
	     *
	     * @param t_airport_chute
	     * @param page
	     * @param rows
	     * @param sort
	     * @param order
	     * @return
	     */
	    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	    @ResponseBody
	    public PageInfo dataGrid(T_airport_chute t_airport_chute, Integer page, Integer rows, String sort, String order) {
	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	        Map<String, Object> condition = Maps.newHashMap();
	        if(StringUtils.isNoneBlank(t_airport_chute.getCODE())){
	            condition.put("CODE", t_airport_chute.getCODE());
	        }
	        pageInfo.setCondition(condition);
	        t_airport_chuteService.findT_airport_chuteDataGrid(pageInfo);
	        return pageInfo;
	    }

	    /**
	     * 滑槽树
	     *
	     * @return
	     */
	    @RequestMapping(value = "/tree", method = RequestMethod.POST)
	    @ResponseBody
	    public List<Tree> tree() {
	        return t_airport_chuteService.findTree();
	    }

	    /**
	     * 添加滑槽页
	     *
	     * @return
	     */
	    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
	    public String addPage() {
	        return "/basic/t_airport_chuteAdd";
	    }

	    /**
	     * 添加滑槽
	     *
	     * @param t_airport_chute
	     * @return
	     */
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    @ResponseBody
	    public Result add(T_airport_chute t_airport_chute) {
	        Result result = new Result();
	        T_airport_chute cf=t_airport_chuteService.findT_airport_chuteByCODE(t_airport_chute.getCODE());
	       	if(cf!=null&&!cf.getID().equals(t_airport_chute.getID()))
	       	{
	               result.setMsg("滑槽编号已存在!");
	               return result;
	       	}
	        try {
	        	t_airport_chuteService.addT_airport_chute(t_airport_chute);
	            result.setSuccess(true);
	            result.setMsg("添加成功！");
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("添加滑槽");
	            sysLog.setOperationobj("滑槽:"+t_airport_chute.getCODE());
	            sysLog.setOptContent("成功添加滑槽:"+t_airport_chute.getCODE()+",滑槽类型:"+t_airport_chute.getTYPE()+",滑槽状态:"+t_airport_chute.getSTATUS()+",滑槽属性:"+t_airport_chute.getATTR()+",RFID标签号:"+t_airport_chute.getRFID()+",备注:"+t_airport_chute.getREMARK()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            logger.error("添加滑槽失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }

	    /**
	     * 删除滑槽
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping("/delete")
	    @ResponseBody
	    public Result delete(Long id) {
	        Result result = new Result();
	        try {
	        	T_airport_chute t_airport_chute=t_airport_chuteService.findT_airport_chuteById(id);
	        	t_airport_chuteService.deleteT_airport_chuteById(id);
	            result.setMsg("删除成功！");
	            result.setSuccess(true);
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("删除滑槽");
	            sysLog.setOperationobj("滑槽:"+t_airport_chute.getCODE());
	            sysLog.setOptContent("成功删除滑槽:"+t_airport_chute.getCODE()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            logger.error("删除滑槽失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }

	    /**
	     * 编辑滑槽页
	     *
	     * @param request
	     * @param id
	     * @return
	     */
	    @RequestMapping("/editPage")
	    public String editPage(HttpSession session, HttpServletRequest request, Long id) {
	    	T_airport_chute t_airport_chute=t_airport_chuteService.findT_airport_chuteById(id);
	        request.setAttribute("t_airport_chute", t_airport_chute);
	        session.setAttribute("CODE", t_airport_chute.getCODE());
	        session.setAttribute("TYPE", t_airport_chute.getTYPE());
	        session.setAttribute("STATUS", t_airport_chute.getSTATUS());
	        session.setAttribute("ATTR", t_airport_chute.getATTR());
	        session.setAttribute("RFID", t_airport_chute.getRFID());
	        session.setAttribute("REMARK", t_airport_chute.getREMARK());
	        return "/basic/t_airport_chuteEdit";
	    }

	    /**
	     * 编辑滑槽
	     *
	     * @param t_airport_chute
	     * @return
	     */
	    @RequestMapping("/edit")
	    @ResponseBody
	    public Result edit(HttpSession session, T_airport_chute t_airport_chute) {
	        Result result = new Result();
	        T_airport_chute cf=t_airport_chuteService.findT_airport_chuteByCODE(t_airport_chute.getCODE());
	       	if(cf!=null&&!cf.getID().equals(t_airport_chute.getID()))
	       	{
	               result.setMsg("滑槽编号已存在!");
	               return result;
	       	}
	        try {
	        	t_airport_chuteService.updateT_airport_chute(t_airport_chute);
	            result.setSuccess(true);
	            result.setMsg("修改成功！");
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("修改滑槽");
	            sysLog.setOperationobj("滑槽:"+t_airport_chute.getCODE());
	            sysLog.setOptContent("修改前的滑槽编号:"+session.getAttribute("CODE")+",滑槽类型:"+session.getAttribute("TYPE")+",滑槽状态:"+session.getAttribute("STATUS")+",滑槽属性:"+session.getAttribute("ATTR")+",RFID标签号:"+session.getAttribute("RFID")+",备注:"+session.getAttribute("REMARK")+
	            "----成功修改滑槽编号:"+t_airport_chute.getCODE()+",滑槽类型:"+t_airport_chute.getTYPE()+",滑槽状态:"+t_airport_chute.getSTATUS()+",滑槽属性:"+t_airport_chute.getATTR()+",RFID标签号:"+t_airport_chute.getRFID()+",备注:"+t_airport_chute.getREMARK()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            logger.error("编辑滑槽失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }

	    @SuppressWarnings("unchecked")
		@RequestMapping("/findAll")
	    @ResponseBody
	    public List<T_airport_chute> findAll(){
	    	return t_airport_chuteService.findT_airport_chuteAll();
	    }
}
