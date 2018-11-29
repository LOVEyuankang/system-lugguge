package com.net.metadata.controller;

import com.net.metadata.entity.User;
import com.net.metadata.service.UserService;
import com.net.metadata.shiro.ShiroUser;
import com.net.metadata.utils.StringEscapeEditor;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description：基础 controller
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class BaseController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

    /**
     * 获取当前登录用户对象
     * @return
     */
    public User getCurrentUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        User currentUser = userService.findUserById(user.id);
        return currentUser;
    }

    /**
     * 获取当前登录用户id
     * @return
     */
    public Long getUserId() {
        return this.getCurrentUser().getId();
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public String getStaffName() {
        return this.getCurrentUser().getLoginname();
    }
    
    /**
     * 获取当前系统时间
     * @return
     */
    public String gettime() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        return dateFormater.format(date);
    }
    
    /**
     * 获取当前ip
     * @return
     * @throws UnknownHostException 
     */
    public String getIp() throws UnknownHostException {
    	InetAddress addr = InetAddress.getLocalHost(); 
        String ip=addr.getHostAddress().toString(); //获取本机ip 
        return ip;
    }
}
