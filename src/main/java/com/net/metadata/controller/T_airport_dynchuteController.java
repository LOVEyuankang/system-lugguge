package com.net.metadata.controller;

import com.google.common.collect.Maps;
import com.net.metadata.entity.InitFltBags;
import com.net.metadata.entity.T_airport_dynchute;
import com.net.metadata.service.InitFltBagsService;
import com.net.metadata.service.T_airport_dynchuteService;
import com.net.metadata.utils.PageInfo;
import com.net.metadata.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/t_airport_dynchute")
public class T_airport_dynchuteController {

	 @Autowired
	    private T_airport_dynchuteService t_airport_dynchuteService;
	 @Autowired
	    private InitFltBagsService initFltBagsService;

	    /**
	     * 滑槽分配管理页
	     *
	     * @return
	     */
	    @RequestMapping(value = "/manager", method = RequestMethod.GET)
	    public String manager() {
	        return "/basic/t_airport_dynchute";
	    }

	    /**
	     * 滑槽分配列表
	     *
	     * @param page
	     * @param rows
	     * @param sort
	     * @param order
	     * @return
	     */
	    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	    @ResponseBody
	    public PageInfo dataGrid(T_airport_dynchute t_airport_dynchute, Integer page, Integer rows, String sort, String order) {
	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	        Map<String, Object> condition = Maps.newHashMap();
	        if(StringUtils.isNoneBlank(t_airport_dynchute.getPLANDATE())){
	            condition.put("PLANDATE", t_airport_dynchute.getPLANDATE());
	        }
	        if(StringUtils.isNoneBlank(t_airport_dynchute.getFLTNO())){
	            condition.put("FLTNO", t_airport_dynchute.getFLTNO());
	        }
	        if(StringUtils.isNoneBlank(t_airport_dynchute.getCODE())){
	            condition.put("CODE", t_airport_dynchute.getCODE());
	        }
	        pageInfo.setCondition(condition);
	        t_airport_dynchuteService.findT_airport_dynchuteDataGrid(pageInfo);
	        return pageInfo;
	    }
	    
	    @RequestMapping(value ="/endloading", method = RequestMethod.POST)
	    @ResponseBody
	    public String update(String plandate,String aircorp,String fltno,String attr){
	    	@SuppressWarnings("unchecked")
			//List<T_airport_dynchute> list = t_airport_dynchuteService.findT_airport_dynchute(plandate, aircorp, fltno, attr);
			List<T_airport_dynchute> list = t_airport_dynchuteService.findT_airport_dynchute(plandate, aircorp, fltno, "");
	    	if(list.size()>0){
	    		for(int i=0;i<list.size();i++){
	    			t_airport_dynchuteService.EndLoading(list.get(i).getID());
	    			List<InitFltBags> ilist=initFltBagsService.findCounts(plandate, aircorp, fltno, list.get(i).getCODE());
	    			if(ilist.size()>0){
	    				String message="{\"msgtype\":\"BG_CLOSE\",\"plandate\":\""+plandate.replaceAll("-", "")+"\","+
							"\"fltno\":\""+aircorp+"@"+fltno+"\",\"chutecode\":\""+list.get(i).getCODE()+"\","+
							"\"bgip\":\""+ilist.get(0).getIp()+"\",\"arrp\":\"\",\"stm\":\""+list.get(i).getSTM()+"\","+
							"\"etm\":\""+list.get(i).getETM()+"\",\"zjs\":"+ilist.get(0).getTotal()+",\"yzcjs\":"+ilist.get(0).getZcbags()+","+
							"\"wsbjs\":"+ilist.get(0).getWsbbags()+",\"gbtjs\":"+ilist.get(0).getVipbags()+","+
							"\"sjajjs\":"+ilist.get(0).getSjajbags()+",\"djs\":"+ilist.get(0).getBigbags()+"}";
	    				SpringUtil.sendMessage(message);
	    			}
	    		}
	    	}
	    	return "1";
	    }

}
