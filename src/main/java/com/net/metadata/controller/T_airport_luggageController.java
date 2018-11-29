package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.code.Result;
import com.net.metadata.entity.SysLog;
import com.net.metadata.entity.T_airport_dynchute;
import com.net.metadata.entity.T_airport_luggage;
import com.net.metadata.service.*;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 行李管理 Controller
 */
@Controller
@RequestMapping("/t_airport_luggage")
public class T_airport_luggageController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(T_airport_luggageController.class);

	@Autowired
	private T_airport_luggageService t_airport_luggageService;

	@Autowired
	private LogService logService;

	@Autowired
	private T_airport_dynchuteService t_airport_dynchuteService;

	@Autowired
	private ChuteService chuteService;

	@Autowired
	private T_airport_dyndepfltService t_airport_dyndepfltService;

	/**
	 * 行李管理页
	 * 
	 * @return
	 */
	@GetMapping(value = "/manager")
	public String manager() {
		return "/luggage/t_airport_luggage";
	}

	/**
	 * 旅客行李列表
	 * 
	 * @param t_airport_luggage
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(T_airport_luggage t_airport_luggage, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();

		if (StringUtils.isNoneBlank(t_airport_luggage.getFltno())) {
			condition.put("fltno", t_airport_luggage.getFltno());
		}
		if (StringUtils.isNoneBlank(t_airport_luggage.getRemartk())) {
			condition.put("remark", t_airport_luggage.getRemartk());
		}

		pageInfo.setCondition(condition);
		t_airport_luggageService.findT_airport_luggageDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加行李页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "/luggage/t_airport_luggageAdd";
	}

	/**
	 * 添加行李
	 * 
	 * @param t_airport_luggage
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(T_airport_luggage t_airport_luggage) {
		Result result = new Result();
		try {
			@SuppressWarnings("rawtypes")
			List list = t_airport_luggageService.findByBgcode(t_airport_luggage.getBgcode());
			if (list.size() > 0) {
				result.setSuccess(false);
				result.setMsg("添加失败，已存在行李标签为" + t_airport_luggage.getBgcode()+ "的行李！");
			} else {
				t_airport_luggageService.addT_airport_luggage(t_airport_luggage);
				result.setSuccess(true);
				result.setMsg("添加成功！");
				SysLog sysLog = new SysLog();
				sysLog.setLoginName(this.getStaffName());
				sysLog.setCreateTime(this.gettime());
				sysLog.setCommand("添加行李");
				sysLog.setOperationobj("行李:" + t_airport_luggage.getBgcode());
				// sysLog.setOPT_CONTENT("成功添加角色:"+role.getName()+",排序号:"+role.getSeq()+",状态:"+role.getStatus()+",备注:"+role.getDescription()+"!");
				sysLog.setClientIp(this.getIp());
				logger.info(sysLog.toString());
				logService.insertLog(sysLog);// 添加

				@SuppressWarnings("unchecked")
				List<T_airport_dynchute> dynclist = t_airport_dynchuteService.findT_airport_dynchute(
								t_airport_luggage.getPlandatetime(),
								t_airport_luggage.getAircorp(),
								t_airport_luggage.getFltno(),
								t_airport_luggage.getAttr());
				String ip = "";
				if (dynclist.size() > 0) {
					ip = chuteService.findByCode(dynclist.get(0).getCODE()).getIp();
				}

				String msg = "[{\"msgtype\":\"BG_ADD\",\"bgip\":\""
						+ ip
						+ "\",\"plandate\":\""
						+ t_airport_luggage.getPlandatetime().replaceAll("-", "")
						+ "\","
						+ "\"fltno\":\""
						+ t_airport_luggage.getAircorp()
						+ "@"
						+ t_airport_luggage.getFltno()
						+ "\",\"bgcode\":\""
						+ t_airport_luggage.getBgcode()
						+ "\","
						+ "\"bgattr\":\""
						+ t_airport_luggage.getAttr()
						+ "\",\"mtype\":\""
						+ t_airport_luggage.getType()
						+ "\","
						+ "\"name\":\"\",\"personid\":\""
						+ t_airport_luggage.getPercode()
						+ "\",\"chknum\":\"\",\"chktm\":\"\","
						+ "\"bgweight\":\"\",\"phonepath\":\"\",\"bgstatus\":\""
						+ t_airport_luggage.getBgstatus()
						+ "\",\"bglevel\":\"\",\"chkdesk\":\"\",\"in_chk_time\":\"\"}]";
				SpringUtil.sendMessage(msg);
			}
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("添加行李失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 删除行李
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			T_airport_luggage t_airport_luggage = t_airport_luggageService.findT_airport_luggageById(id);
			t_airport_luggageService.deleteT_airport_luggage(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("删除行李");
			sysLog.setOperationobj("行李:" + t_airport_luggage.getBgcode());
			sysLog.setOptContent("成功删除行李:" + t_airport_luggage.getBgcode()+ "!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			@SuppressWarnings("unchecked")
			List<T_airport_dynchute> dynclist = t_airport_dynchuteService.findT_airport_dynchute(t_airport_luggage.getPlandatetime(),
							t_airport_luggage.getAircorp(),
							t_airport_luggage.getFltno(),
							t_airport_luggage.getAttr());
			String ip = "";
			if (dynclist.size() > 0) {
				ip = chuteService.findByCode(dynclist.get(0).getCODE()).getIp();
			}

			String msg = "[{\"msgtype\":\"BG_DELE\",\"bgip\":\""
					+ ip
					+ "\",\"plandate\":\""
					+ t_airport_luggage.getPlandatetime().replaceAll("-", "")
					+ "\","
					+ "\"fltno\":\""
					+ t_airport_luggage.getAircorp()
					+ "@"
					+ t_airport_luggage.getFltno()
					+ "\",\"bgcode\":\""
					+ t_airport_luggage.getBgcode()
					+ "\","
					+ "\"bgattr\":\""
					+ t_airport_luggage.getAttr()
					+ "\",\"mtype\":\""
					+ t_airport_luggage.getType()
					+ "\","
					+ "\"name\":\"\",\"personid\":\""
					+ t_airport_luggage.getPercode()
					+ "\",\"chknum\":\"\",\"chktm\":\"\","
					+ "\"bgweight\":\"\",\"phonepath\":\"\",\"bgstatus\":\""
					+ t_airport_luggage.getBgstatus()
					+ "\",\"bglevel\":\"\",\"chkdesk\":\"\",\"in_chk_time\":\"\"}]";
			SpringUtil.sendMessage(msg);
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("删除行李失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 编辑行李页
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpSession session, HttpServletRequest request, Long id) {
		T_airport_luggage t_airport_luggage = t_airport_luggageService.findT_airport_luggageById(id);
		request.setAttribute("t_airport_luggage", t_airport_luggage);
		// session.setAttribute("name", role.getName());
		// session.setAttribute("seq", role.getSeq());
		// session.setAttribute("status", role.getStatus());
		// session.setAttribute("description", role.getDescription());
		return "/luggage/t_airport_luggageEdit";
	}

	/**
	 * 编辑行李
	 * 
	 * @param t_airport_luggage
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(HttpSession session, T_airport_luggage t_airport_luggage) {
		Result result = new Result();
		try {
			t_airport_luggageService.updateT_airport_luggage(t_airport_luggage);
			result.setSuccess(true);
			result.setMsg("修改成功！");
			SysLog sysLog = new SysLog();
			sysLog.setLoginName(this.getStaffName());
			sysLog.setCreateTime(this.gettime());
			sysLog.setCommand("修改行李");
			sysLog.setOperationobj("行李:" + t_airport_luggage.getBgcode());
			// sysLog.setOPT_CONTENT("修改前的角色:"+session.getAttribute("name")+",排序号:"+session.getAttribute("seq")+",状态:"+session.getAttribute("status")+",描述:"+session.getAttribute("description")+
			// "----成功修改角色:"+role.getName()+",排序号:"+role.getSeq()+",状态:"+role.getStatus()+",描述:"+role.getDescription()+"!");
			sysLog.setClientIp(this.getIp());
			logger.info(sysLog.toString());
			logService.insertLog(sysLog);// 添加
			return result;
		} catch (RuntimeException | UnknownHostException e) {
			logger.error("编辑行李失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@RequestMapping(value = "/findT_airport_luggageAll", method = RequestMethod.POST)
	@ResponseBody
	public int findT_airport_luggageAll() {
		return t_airport_luggageService.findT_airport_luggageAll();
	}

	@RequestMapping(value = "/findT_airport_luggageAllbystutas", method = RequestMethod.POST)
	@ResponseBody
	public int findT_airport_luggageAllbystutas() {
		return t_airport_luggageService.findT_airport_luggageAllbystutas();
	}

	@RequestMapping(value = "/findT_airport_luggageAllbystutasw", method = RequestMethod.POST)
	@ResponseBody
	public int findT_airport_luggageAllbystutasw() {
		return t_airport_luggageService.findT_airport_luggageAllbystutasw();
	}

	/**
	 * 航班当前时段总数
	 */
	@RequestMapping(value = "/aa", method = RequestMethod.POST)
	@ResponseBody
	public int aa() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		String tt = String.valueOf(hour);
		return t_airport_dyndepfltService.findT_airport_dyndepfltAllbytime(tt);
	}

	/**
	 * 已保障航班当前时段总数
	 */
	@RequestMapping(value = "/bb", method = RequestMethod.POST)
	@ResponseBody
	public int bb() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		String tt = String.valueOf(hour);
		return t_airport_dyndepfltService.findT_airport_dyndepfltAllbyStatusbytime(tt);
	}

	/**
	 * 行李时段总数
	 */
	@RequestMapping(value = "/findT_airport_luggageAllbytime", method = RequestMethod.POST)
	@ResponseBody
	public List<Integer> findT_airport_luggageAllbytime() {
		List<Integer> list = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		for (int i = 0; i <= hour; i++) {
			String ii = i < 10 ? "0" + String.valueOf(i) : String.valueOf(i);
			int res = t_airport_luggageService .findT_airport_luggageAllbytime(ii);
			list.add(res);
		}
		return list;
	}

	/**
	 * 已装车行李时段总数
	 */
	@RequestMapping(value = "/findT_airport_luggageAllbystutastime", method = RequestMethod.POST)
	@ResponseBody
	public List<Integer> findT_airport_luggageAllbystutastime() {
		List<Integer> list = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		for (int i = 0; i <= hour; i++) {
			String ii = i < 10 ? "0" + String.valueOf(i) : String.valueOf(i);
			int res = t_airport_luggageService .findT_airport_luggageAllbystutastime(ii);
			list.add(res);
		}
		return list;
	}

	/**
	 * 行李当前时段总数
	 */
	@RequestMapping(value = "/cc", method = RequestMethod.POST)
	@ResponseBody
	public int cc() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		String tt = String.valueOf(hour);
		return t_airport_luggageService.findT_airport_luggageAllbytime(tt);
	}

	/**
	 * 已装车行李当前时段总数
	 */
	@RequestMapping(value = "/dd", method = RequestMethod.POST)
	@ResponseBody
	public int dd() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		String tt = String.valueOf(hour);
		return t_airport_luggageService.findT_airport_luggageAllbystutastime(tt);
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//获取年份
		int month = cal.get(Calendar.MONTH);//获取月份
		int day = cal.get(Calendar.DATE);//获取日
		int hour1 = cal.get(Calendar.HOUR);//12小时制
		int hour2 = cal.get(Calendar.HOUR_OF_DAY);//24小时制
		int minute = cal.get(Calendar.MINUTE);//分
		int second = cal.get(Calendar.SECOND);//秒
		int millisecond = cal.get(Calendar.MILLISECOND);//毫秒
		int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天

		System.out.println("现在的时间是(12小时制)：公元"+year+"年"+month+"月"+day+"日 "+hour1+"时"+minute+"分"+second+"秒"+millisecond+"毫秒 星期"+WeekOfYear);
		System.out.println("现在的时间是(24小时制)：公元"+year+"年"+month+"月"+day+"日 "+hour2+"时"+minute+"分"+second+"秒"+millisecond+"毫秒 星期"+WeekOfYear);

		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		System.out.println(dateFormater.format(cal.getTime()));
		System.out.println(dateFormater.format(date));
	}
}
