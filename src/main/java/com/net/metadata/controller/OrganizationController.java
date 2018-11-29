package com.net.metadata.controller;

import com.net.metadata.code.Result;
import com.net.metadata.entity.Organization;
import com.net.metadata.entity.SysLog;
import com.net.metadata.service.LogService;
import com.net.metadata.service.OrganizationService;
import com.net.metadata.vo.Tree;
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

/**
 * @description：部门管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;
    
	@Autowired
	private LogService logService;

    /**
     * 部门管理主页
     *
     * @return
     */
    @RequestMapping("/manager")
    public String manager() {
        return "/admin/organization";
    }

    /**
     * 部门资源树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        List<Tree> trees = organizationService.findTree();
        return trees;
    }

    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public List<Organization> treeGrid() {
        List<Organization> treeGrid = organizationService.findTreeGrid();
        return treeGrid;
    }

    /**
     * 添加部门页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "/admin/organizationAdd";
    }

    /**
     * 添加部门
     *
     * @param organization
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Organization organization) {
        Result result = new Result();
        Organization mr=organizationService.findOrganizationBycode(organization.getCode());
    	if(mr!=null){
            result.setMsg("部门编号已存在!");
            return result;
    	}
        try {
            organizationService.addOrganization(organization);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("添加部门");
            sysLog.setOperationobj("部门:"+organization.getName());
            sysLog.setOptContent("成功添加部门名称:"+organization.getName()+",部门编号:"+organization.getCode()+",菜单图标:"+organization.getIcon()+",排序:"+organization.getSeq()+",地址:"+organization.getAddress()+",上级部门:"+organization.getPid()+"!");
            sysLog.setClientIp(this.getIp());
            LOGGER.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            LOGGER.info("添加部门失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 编辑部门页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpSession session, HttpServletRequest request, Long id) {
        Organization organization = organizationService.findOrganizationById(id);
        request.setAttribute("organization", organization);
        session.setAttribute("name", organization.getName());
        session.setAttribute("address", organization.getAddress());
        session.setAttribute("icon", organization.getIcon());
        session.setAttribute("pid", organization.getPid());
        session.setAttribute("seq", organization.getSeq());
        session.setAttribute("code", organization.getCode());
        return "/admin/organizationEdit";
    }

    /**
     * 编辑部门
     *
     * @param organization
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(HttpSession session, Organization organization) {
        Result result = new Result();
        Organization mr=organizationService.findOrganizationBycode(organization.getCode());
    	if(mr!=null&&!mr.getId().equals(organization.getId())){
            result.setMsg("部门编号已存在!");
            return result;
    	}
        try {
            organizationService.updateOrganization(organization);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("修改部门");
            sysLog.setOperationobj("部门:"+organization.getName());
            sysLog.setOptContent("修改前的部门名称:"+session.getAttribute("name")+",部门编号:"+session.getAttribute("code")+",菜单图标:"+session.getAttribute("icon")+",排序:"+session.getAttribute("seq")+",地址:"+session.getAttribute("address")+",上级部门:"+session.getAttribute("pid")+
            "----成功修改部门名称:"+organization.getName()+",部门编号:"+organization.getCode()+",菜单图标:"+organization.getIcon()+",排序:"+organization.getSeq()+",地址:"+organization.getAddress()+",上级资源:"+organization.getPid()+"!");
            sysLog.setClientIp(this.getIp());
            LOGGER.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            LOGGER.info("编辑部门失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
        	Organization organization=organizationService.findOrganizationById(id);
            organizationService.deleteOrganizationById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("删除部门");
            sysLog.setOperationobj("部门:"+organization.getName());
            sysLog.setOptContent("成功删除部门:"+organization.getName()+"!");
            sysLog.setClientIp(this.getIp());
            LOGGER.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            LOGGER.info("删除部门失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
}
