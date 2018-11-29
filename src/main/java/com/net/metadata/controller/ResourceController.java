package com.net.metadata.controller;

import com.net.metadata.code.Result;
import com.net.metadata.entity.Resource;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.User;
import com.net.metadata.service.LogService;
import com.net.metadata.service.ResourceService;
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
 * @description：资源管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;
    
	@Autowired
	private LogService logService;

    /**
     * 菜单树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        User currentUser = getCurrentUser();
        List<Tree> tree = resourceService.findTree(currentUser);
        return tree;
    }

    /**
     * 资源管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/resource";
    }

    /**
     * 资源管理列表
     *
     * @return
     */
    @RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
    @ResponseBody
    public List<Resource> treeGrid() {
        List<Resource> treeGrid = resourceService.findResourceAll();
        return treeGrid;
    }

    /**
     * 添加资源页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "/admin/resourceAdd";
    }

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Resource resource) {
        Result result = new Result();
        try {
            resourceService.addResource(resource);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("添加资源");
            sysLog.setOperationobj("资源:"+resource.getName());
            sysLog.setOptContent("成功添加资源名称:"+resource.getName()+",资源路径:"+resource.getUrl()+",菜单图标:"+resource.getIcon()+",资源类型:"+resource.getResourcetype()+",排序:"+resource.getSeq()+",状态:"+resource.getStatus()+",上级资源:"+resource.getPid()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("添加资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 二级资源树
     *
     * @return
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public List<Tree> allTree() {
        return resourceService.findAllTree();
    }

    /**
     * 三级资源树
     *
     * @return
     */
    @RequestMapping(value = "/allTrees", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> allTrees() {
        return resourceService.findAllTrees();
    }

    /**
     * 编辑资源页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpSession session, HttpServletRequest request, Long id) {
        Resource resource = resourceService.findResourceById(id);
        request.setAttribute("resource", resource);
        session.setAttribute("name", resource.getName());
        session.setAttribute("url", resource.getUrl());
        session.setAttribute("icon", resource.getIcon());
        session.setAttribute("pid", resource.getPid());
        session.setAttribute("seq", resource.getSeq());
        session.setAttribute("status", resource.getStatus());
        session.setAttribute("resourcetype", resource.getResourcetype());
        return "/admin/resourceEdit";
    }

    /**
     * 编辑资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(HttpSession session, Resource resource) {
        Result result = new Result();
        try {
            resourceService.updateResource(resource);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("修改资源");
            sysLog.setOperationobj("资源:"+resource.getName());
            sysLog.setOptContent("修改前的资源名称:"+session.getAttribute("name")+",资源路径:"+session.getAttribute("url")+",菜单图标:"+session.getAttribute("icon")+",资源类型:"+session.getAttribute("resourcetype")+",排序:"+session.getAttribute("seq")+",状态:"+session.getAttribute("status")+",上级资源:"+session.getAttribute("pid")+
            "----成功修改资源名称:"+resource.getName()+",资源路径:"+resource.getUrl()+",菜单图标:"+resource.getIcon()+",资源类型:"+resource.getResourcetype()+",排序:"+resource.getSeq()+",状态:"+resource.getStatus()+",上级资源:"+resource.getPid()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("编辑资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
        	Resource resource=resourceService.findResourceById(id);
            resourceService.deleteResourceById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(this.getStaffName());
            sysLog.setCreateTime(this.gettime());
            sysLog.setCommand("删除资源");
            sysLog.setOperationobj("资源:"+resource.getName());
            sysLog.setOptContent("成功删除资源:"+resource.getName()+"资源路径:"+resource.getUrl()+"!");
            sysLog.setClientIp(this.getIp());
            logger.info(sysLog.toString());
            logService.insertLog(sysLog);//添加
            return result;
        } catch (RuntimeException | UnknownHostException e) {
            logger.error("删除资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
