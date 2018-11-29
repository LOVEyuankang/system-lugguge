package com.net.metadata.controller;

import com.net.metadata.code.Result;
import com.net.metadata.entity.Postinfo;
import com.net.metadata.entity.SysLog;
import com.net.metadata.service.LogService;
import com.net.metadata.service.PostinfoService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @description：岗位管理
 * @author：ccj
 * @date：2016/05/21 14:51
 */
@Controller
@RequestMapping("/postinfo")
public class PostinfoController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PostinfoController.class);

    @Autowired
    private PostinfoService postinfoService;

	@Autowired
	private LogService logService;

    /**
     * 岗位管理列表
     *
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public List<Postinfo> dataGrid(HttpServletRequest request, String code) {
    	List<Postinfo> dataGrid = new ArrayList<Postinfo>();
    	if(code!=null){
            dataGrid = postinfoService.findPostinfoAllByDeptid(code);
            return dataGrid;
    	}
    	return dataGrid;
    }

    /**
     * 添加岗位页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request, String code) {
    	request.setAttribute("code", code);
        return "/admin/postinfoAdd";
    }

    /**
     * 添加岗位
     *
     * @param postinfo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Postinfo postinfo) {
        Result result = new Result();
        try {
            postinfoService.addPostinfo(postinfo);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("添加岗位");
            sysLog.setOperationobj("岗位:"+postinfo.getPostname());
            sysLog.setOptContent("成功添加岗位名称:"+postinfo.getPostname()+",备注:"+postinfo.getPostremark()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("添加岗位失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
    /**
     * 编辑岗位页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpSession session, HttpServletRequest request, Long id) {
        Postinfo postinfo = postinfoService.findPostinfoById(id);
        request.setAttribute("postinfo", postinfo);
        session.setAttribute("postname", postinfo.getPostname());
        session.setAttribute("postremark", postinfo.getPostremark());
        return "/admin/postinfoEdit";
    }

    /**
     * 编辑岗位
     *
     * @param postinfo
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(HttpSession session, Postinfo postinfo) {
        Result result = new Result();
        try {
            postinfoService.updatePostinfo(postinfo);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("修改岗位");
            sysLog.setOperationobj("岗位:"+postinfo.getPostname());
            sysLog.setOptContent("修改前的岗位名称:"+session.getAttribute("postname")+",备注:"+session.getAttribute("postremark")+
            "----成功修改岗位名称:"+postinfo.getPostname()+",备注:"+postinfo.getPostremark()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("编辑岗位失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 删除岗位
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
        	Postinfo postinfo=postinfoService.findPostinfoById(id);
            postinfoService.deletePostinfoById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("删除岗位");
            sysLog.setOperationobj("岗位:"+postinfo.getPostname());
            sysLog.setOptContent("成功删除岗位:"+postinfo.getPostname()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("删除岗位失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
