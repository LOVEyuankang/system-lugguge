package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_device;
import com.net.metadata.service.LogService;
import com.net.metadata.service.T_airport_deviceService;
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
@RequestMapping("/t_airport_device")
public class T_airport_deviceController extends BaseController {

	  private static Logger logger = LoggerFactory.getLogger(T_airport_chuteController.class);

	    @Autowired
	    private T_airport_deviceService t_airport_deviceService;
	    
	    @Autowired
		private LogService logService;

	    /**
	     * 设备管理页
	     *
	     * @return
	     */
	    @RequestMapping(value = "/manager", method = RequestMethod.GET)
	    public String manager() {
	        return "/basic/t_airport_device";
	    }

	    /**
	     * 设备列表
	     *
	     * @param t_airport_device
	     * @param page
	     * @param rows
	     * @param sort
	     * @param order
	     * @return
	     */
	    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	    @ResponseBody
	    public PageInfo dataGrid(T_airport_device t_airport_device, Integer page, Integer rows, String sort, String order) {
	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	        Map<String, Object> condition = Maps.newHashMap();
	        if(StringUtils.isNoneBlank(t_airport_device.getCode())){
	            condition.put("CODE", t_airport_device.getCode());
	        }
	        pageInfo.setCondition(condition);
	        t_airport_deviceService.findT_airport_deviceDataGrid(pageInfo);
	        return pageInfo;
	    }

	    /**
	     * 设备树
	     *
	     * @return
	     */
	    @RequestMapping(value = "/tree", method = RequestMethod.POST)
	    @ResponseBody
	    public List<Tree> tree() {
	        return t_airport_deviceService.findTree();
	    }

	    /**
	     * 添加设备页
	     *
	     * @return
	     */
	    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
	    public String addPage() {
	        return "/basic/t_airport_deviceAdd";
	    }

	    /**
	     * 添加设备
	     *
	     * @param t_airport_device
	     * @return
	     */
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    @ResponseBody
	    public Result add(T_airport_device t_airport_device) {
	        Result result = new Result();
	        T_airport_device cf=t_airport_deviceService.findT_airport_deviceByCODE(t_airport_device.getCode());
	       	if(cf!=null&&!cf.getId().equals(t_airport_device.getId()))
	       	{
	               result.setMsg("设备编号已存在!");
	               return result;
	       	}
	        try {
	        	t_airport_deviceService.addT_airport_device(t_airport_device);
	            result.setSuccess(true);
	            result.setMsg("添加成功！");
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("添加设备");
	            sysLog.setOperationobj("设备:"+t_airport_device.getCode());
	            sysLog.setOptContent("成功添加设备编号:"+t_airport_device.getCode()+",设备类型:"+t_airport_device.getType()+",设备状态:"+t_airport_device.getStatus()+",设备位置:"+t_airport_device.getPosition()+",工控机IP:"+t_airport_device.getIp()+",SSID:"+t_airport_device.getSsid()+",WIFIpwd:"+t_airport_device.getWifipwd()+",是否支持WIFI:"+t_airport_device.getIs_wifi()+",是否支持移动网络:"+t_airport_device.getIs_mobile()+",备注:"+t_airport_device.getRemark()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            logger.error("添加设备失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }

	    /**
	     * 删除设备
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping("/delete")
	    @ResponseBody
	    public Result delete(Long id) {
	        Result result = new Result();
	        try {
	        	T_airport_device t_airport_device=t_airport_deviceService.findT_airport_deviceById(id);
	        	t_airport_deviceService.deleteT_airport_deviceById(id);
	            result.setMsg("删除成功！");
	            result.setSuccess(true);
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("删除设备");
	            sysLog.setOperationobj("设备:"+t_airport_device.getCode());
	            sysLog.setOptContent("成功删除设备:"+t_airport_device.getCode()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            logger.error("删除滑槽与设备对应失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }

	    /**
	     * 编辑设备页
	     *
	     * @param request
	     * @param id
	     * @return
	     */
	    @RequestMapping("/editPage")
	    public String editPage(HttpSession session, HttpServletRequest request, Long id) {
	    	T_airport_device t_airport_device=t_airport_deviceService.findT_airport_deviceById(id);
	        request.setAttribute("t_airport_device", t_airport_device);
	        session.setAttribute("CODE", t_airport_device.getCode());
	        session.setAttribute("TYPE", t_airport_device.getType());
	        session.setAttribute("STATUS", t_airport_device.getStatus());
	        session.setAttribute("POSITION", t_airport_device.getPosition());
	        session.setAttribute("IP", t_airport_device.getIp());
	        session.setAttribute("SSID", t_airport_device.getSsid());
	        session.setAttribute("WIFIpwd", t_airport_device.getWifipwd());
	        session.setAttribute("IS_WIFI", t_airport_device.getIs_wifi());
	        session.setAttribute("IS_MOBILE", t_airport_device.getIs_mobile());
	        session.setAttribute("REMARK", t_airport_device.getRemark());
	        return "/basic/t_airport_deviceEdit";
	    }

	    /**
	     * 编辑设备
	     *
	     * @param t_airport_device
	     * @return
	     */
	    @RequestMapping("/edit")
	    @ResponseBody
	    public Result edit(HttpSession session, T_airport_device t_airport_device) {
	        Result result = new Result();
	        T_airport_device cf=t_airport_deviceService.findT_airport_deviceByCODE(t_airport_device.getCode());
	       	if(cf!=null&&!cf.getId().equals(t_airport_device.getId()))
	       	{
	               result.setMsg("设备编号已存在!");
	               return result;
	       	}
	        try {
	        	t_airport_deviceService.updateT_airport_device(t_airport_device);
	            result.setSuccess(true);
	            result.setMsg("修改成功！");
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("修改设备");
	            sysLog.setOperationobj("设备:"+t_airport_device.getCode());
	            sysLog.setOptContent("修改前的设备编号:"+session.getAttribute("CODE")+",设备类型:"+session.getAttribute("TYPE")+",设备状态:"+session.getAttribute("STATUS")+",设备位置:"+session.getAttribute("POSITION")+",工控机IP:"+session.getAttribute("IP")+",SSID:"+session.getAttribute("SSID")+",WIFIpwd:"+session.getAttribute("WIFIpwd")+",是否支持WIFI:"+session.getAttribute("IS_WIFI")+",是否支持移动网络:"+session.getAttribute("IS_MOBILE")+",备注:"+session.getAttribute("REMARK")+
	            "----成功修改设备编号:"+t_airport_device.getCode()+",设备类型:"+t_airport_device.getType()+",设备状态:"+t_airport_device.getStatus()+",设备位置:"+t_airport_device.getPosition()+",工控机IP:"+t_airport_device.getIp()+",SSID:"+t_airport_device.getSsid()+",WIFIpwd:"+t_airport_device.getWifipwd()+",是否支持WIFI:"+t_airport_device.getIs_wifi()+",是否支持移动网络:"+t_airport_device.getIs_mobile()+",备注:"+t_airport_device.getRemark()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            logger.error("编辑设备失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }
}
