package com.net.metadata.controller;

import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_chute;
import com.net.metadata.entity.T_airport_chute_device;
import com.net.metadata.service.LogService;
import com.net.metadata.service.T_airport_chuteService;
import com.net.metadata.service.T_airport_chute_deviceService;
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

@Controller
@RequestMapping("/t_airport_chute_device")
public class T_airport_chute_deviceController extends BaseController {
	  private static Logger logger = LoggerFactory.getLogger(T_airport_chute_deviceController.class);

	    @Autowired
	    private T_airport_chute_deviceService t_airport_chute_deviceService;
	    
	    @Autowired
	    private T_airport_chuteService t_airport_chuteService ;
	    
	    @Autowired
		private LogService logService;

	    /**
	     * 滑槽对应设备管理页
	     *
	     * @return
	     */
	    @RequestMapping(value = "/manager", method = RequestMethod.GET)
	    public String manager() {
	        return "/basic/t_airport_chute_device";
	    }

	    /**
	     * 滑槽对应设备列表
	     */
	    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	    @ResponseBody
	    public List<T_airport_chute_device> dataGrid(String CHUTECODE) {   
	    	return t_airport_chute_deviceService.findT_airport_chute_deviceByCODE(CHUTECODE);
	    }

	    /**
	     * 滑槽对应设备树
	     */
//	    @RequestMapping(value = "/tree", method = RequestMethod.POST)
//	    @ResponseBody
//	    public List<Tree> tree() {
//	        return roleService.findTree();
//	    }

	    /**
	     * 添加滑槽对应设备页
	     */
	    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
	    public String addPage(HttpServletRequest request, Long id) {
	    	T_airport_chute t_airport_chute=t_airport_chuteService.findT_airport_chuteById(id);
	    	request.setAttribute("t_airport_chute", t_airport_chute);
	        return "/basic/t_airport_chute_deviceAdd";
	    }

	    /**
	     * 添加滑槽对应设备
	     *
	     * @param t_airport_chute_device
	     * @return
	     */
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    @ResponseBody
	    public Result add(T_airport_chute_device t_airport_chute_device) {
	        Result result = new Result();
	        try {
	        	t_airport_chute_deviceService.addT_airport_chute_device(t_airport_chute_device);
	            result.setSuccess(true);
	            result.setMsg("添加成功！");
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("添加滑槽对应设备");
	            sysLog.setOperationobj("滑槽对应设备:"+t_airport_chute_device.getChutecode());
	            sysLog.setOptContent("成功添加滑槽对应设备:"+t_airport_chute_device.getChutecode()+",设备编号:"+t_airport_chute_device.getDevicecode()+",设备类型:"+t_airport_chute_device.getDevicetype()+",备注:"+t_airport_chute_device.getRemark()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            result.setSuccess(false);
				result.setMsg("该滑槽已对应航显工控机！");
	            return result;
	        }
	    }

	    /**
	     * 删除滑槽对应设备
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping("/delete")
	    @ResponseBody
	    public Result delete(Long id) {
	        Result result = new Result();
	        try {
	        	T_airport_chute_device t_airport_chute_device=t_airport_chute_deviceService.findT_airport_chute_deviceById(id);
	        	t_airport_chute_deviceService.deleteT_airport_chute_deviceById(id);
	            result.setMsg("删除成功！");
	            result.setSuccess(true);
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("删除滑槽对应设备");
	            sysLog.setOperationobj("滑槽对应设备:"+t_airport_chute_device.getChutecode());
	            sysLog.setOptContent("成功删除滑槽对应设备:"+t_airport_chute_device.getChutecode()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            logger.error("删除滑槽对应设备失败：{}", e);
	            result.setMsg(e.getMessage());
	            return result;
	        }
	    }

	    /**
	     * 编辑滑槽对应设备页
	     *
	     * @param request
	     * @param id
	     * @return
	     */
	    @RequestMapping("/editPage")
	    public String editPage(HttpSession session, HttpServletRequest request, Long id) {
	    	T_airport_chute_device t_airport_chute_device=t_airport_chute_deviceService.findT_airport_chute_deviceById(id);
	        request.setAttribute("t_airport_chute_device", t_airport_chute_device);
	        session.setAttribute("CHUTECODE", t_airport_chute_device.getChutecode());
	        session.setAttribute("DEVICECODE", t_airport_chute_device.getChutecode());
	        session.setAttribute("DEVICETYPE", t_airport_chute_device.getDevicetype());
	        session.setAttribute("REMARK", t_airport_chute_device.getRemark());
	        return "/basic/t_airport_chute_deviceEdit";
	    }

	    /**
	     * 编辑滑槽对应设备
	     *
	     * @param t_airport_chute_device
	     * @return
	     */
	    @RequestMapping("/edit")
	    @ResponseBody
	    public Result edit(HttpSession session, T_airport_chute_device t_airport_chute_device) {
	        Result result = new Result();
	        try {
	        	t_airport_chute_deviceService.updateT_airport_chute_device(t_airport_chute_device);
	            result.setSuccess(true);
	            result.setMsg("修改成功！");
	            SysLog sysLog = new SysLog();
	            sysLog.setLoginName(this.getStaffName());
	            sysLog.setCreateTime(this.gettime());
	            sysLog.setCommand("修改滑槽对应设备");
	            sysLog.setOperationobj("滑槽对应设备:"+t_airport_chute_device.getChutecode());
	            sysLog.setOptContent("修改前的滑槽对应设备:"+session.getAttribute("CHUTECODE")+",设备编号:"+session.getAttribute("DEVICECODE")+",设备类型:"+session.getAttribute("DEVICETYPE")+",备注:"+session.getAttribute("REMARK")+
	            "----成功修改滑槽对应设备:"+t_airport_chute_device.getChutecode()+",设备编号:"+t_airport_chute_device.getDevicecode()+",设备类型:"+t_airport_chute_device.getDevicetype()+",备注:"+t_airport_chute_device.getRemark()+"!");
	            sysLog.setClientIp(this.getIp());
	            logger.info(sysLog.toString());
	            logService.insertLog(sysLog);//添加
	            return result;
	        } catch (RuntimeException | UnknownHostException e) {
	            result.setSuccess(false);
	        	result.setMsg("该滑槽已对应航显工控机！");
	            return result;
	        }
	    }

}
