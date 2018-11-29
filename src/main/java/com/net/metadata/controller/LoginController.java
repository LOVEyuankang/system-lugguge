package com.net.metadata.controller;

import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.service.LogService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;


/**
 * @description：登录退出
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
public class LoginController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LogService logService;

	/**
	 * 首页
	 */
	@RequestMapping(value = {"/","/index","index"})
	public String index(Model model) {
		return "/index";
	}

	/**
	 * GET 登录
	 */
	@GetMapping(value = "/login")
	public String login(Model model, HttpServletRequest request) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index";
		}
		return "/login";
	}

	/**
	 * POST 登录 shiro 写法
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param request
	 * @param model
	 * @return
	 * @throws UnknownHostException
	 */
	@PostMapping(value = "/login")
	@ResponseBody
	public Result loginPost(String username, String password, HttpServletRequest request, Model model) throws UnknownHostException {
		Result result = new Result();
		if (StringUtils.isBlank(username)) {
			result.setMsg("用户名不能为空");
			return result;
		}
		if (StringUtils.isBlank(password)) {
			result.setMsg("密码不能为空");
			return result;
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
		token.setRememberMe(true);
		try {
			user.login(token);
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(username);
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("用户登录");
			sysLog.setOperationobj("登录的用户是:" + username);
			sysLog.setOptContent("成功登录用户:" + username + "!");
			sysLog.setClientIp(this.getIp());
			LOGGER.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
		} catch (UnknownAccountException e) {
			LOGGER.error("账号不存在：{}", e);
			result.setMsg("账号不存在");
			return result;
		} catch (DisabledAccountException e) {
			LOGGER.error("账号未启用：{}", e);
			result.setMsg("账号未启用");
			return result;
		} catch (IncorrectCredentialsException e) {
			LOGGER.error("密码错误：{}", e);
			result.setMsg("密码错误");
			return result;
		} catch (RuntimeException e) {
			LOGGER.error("未知错误,请联系管理员：{}", e);
			result.setMsg("未知错误,请联系管理员");
			return result;
		}
		result.setSuccess(true);
		return result;
	}

	/**
	 * 未授权
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/unauth")
	public String unauth(Model model) {
		if (SecurityUtils.getSubject().isAuthenticated() == false) {
			return "redirect:/login";
		}
		return "/unauth";
	}

}
